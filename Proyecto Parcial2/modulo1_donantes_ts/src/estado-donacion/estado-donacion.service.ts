import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { EstadoDonacion } from './entities/estado-donacion.entity';
import { CreateEstadoDonacionDto } from './dto/create-estado-donacion.dto';

@Injectable()
export class EstadoDonacionService {
  constructor(
    @InjectRepository(EstadoDonacion)
    private estadoRepo: Repository<EstadoDonacion>,
  ) {}

  create(dto: CreateEstadoDonacionDto): Promise<EstadoDonacion> {
    const nuevo = this.estadoRepo.create(dto);
    return this.estadoRepo.save(nuevo);
  }

  findAll(): Promise<EstadoDonacion[]> {
    return this.estadoRepo.find();
  }

  async findOne(id: number): Promise<EstadoDonacion> {
    const estado = await this.estadoRepo.findOneBy({ id_estado: id });
    if (!estado) throw new NotFoundException('Estado no encontrado');
    return estado;
  }

  async remove(id: number): Promise<void> {
    await this.estadoRepo.delete(id);
  }
}
