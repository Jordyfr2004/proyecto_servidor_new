import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Donante } from './entities/donante.entity';
import { CreateDonanteDto } from './dto/create-donante.dto';
import { UpdateDonanteDto } from './dto/update-donante.dto';

@Injectable()
export class DonanteService {
  constructor(
    @InjectRepository(Donante)
    private donanteRepository: Repository<Donante>,
  ) {}

  create(dto: CreateDonanteDto): Promise<Donante> {
    const nuevo = this.donanteRepository.create({
      ...dto,
      fecha_registro: new Date(dto.fecha_registro),
    });
    return this.donanteRepository.save(nuevo);
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
