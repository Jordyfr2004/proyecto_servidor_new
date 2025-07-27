import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Donante } from './entities/donante.entity';
import { CreateDonanteDto } from './dto/create-donante.dto';
import { UpdateDonanteDto } from './dto/update-donante.dto';
import axios from 'axios'; // 👈 AÑADIDO

@Injectable()
export class DonanteService {
  constructor(
    @InjectRepository(Donante)
    private donanteRepository: Repository<Donante>,
  ) {}

  async create(dto: CreateDonanteDto): Promise<Donante> {
    const nuevo = this.donanteRepository.create({
      ...dto,
      fecha_registro: new Date(dto.fecha_registro),
    });

    const donanteGuardado = await this.donanteRepository.save(nuevo);

    // 👇 Enviar notificación al WebSocket
    try {
      await axios.post('http://localhost:3001/notificar', {
        evento: 'nuevo_donante',
        mensaje: `Nuevo donante registrado: ${donanteGuardado.nombre}`,
        fecha: new Date().toISOString(),
      });
    } catch (error) {
      console.error('Error al enviar notificación WebSocket:', error.message);
    }

    return donanteGuardado;
  }

  findAll(): Promise<Donante[]> {
    return this.donanteRepository.find();
  }

  async findOne(id: number): Promise<Donante> {
    const donante = await this.donanteRepository.findOneBy({ id_donante: id });
    if (!donante) {
      throw new NotFoundException(`Donante con id ${id} no encontrado`);
    }
    return donante;
  }

  async update(id: number, dto: UpdateDonanteDto): Promise<Donante> {
    if (dto.fecha_registro) {
      dto.fecha_registro = new Date(dto.fecha_registro);
    }
    await this.donanteRepository.update(id, dto);
    return this.findOne(id);
  }

  async remove(id: number): Promise<void> {
    await this.donanteRepository.delete(id);
  }
}
