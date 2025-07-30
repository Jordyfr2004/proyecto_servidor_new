import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';

import { CreateResumenDonacionDto } from './dto/create-resumen-donacion.dto';
import { UpdateResumenDonacionDto } from './dto/update-resumen-donacion.dto';
import { ResumenDonacion } from './entities/resumen-donacion.entity';

// Servicio que interactúa con la base de datos para resumen_donacion
@Injectable()
export class ResumenDonacionService {
  constructor(
    @InjectRepository(ResumenDonacion)
    private readonly resumenRepo: Repository<ResumenDonacion>,
  ) {}

  // Crea y guarda un nuevo resumen
  async create(createResumenDonacionDto: CreateResumenDonacionDto) {
    const resumen = this.resumenRepo.create(createResumenDonacionDto);
    return await this.resumenRepo.save(resumen);
  }

  // Devuelve todos los resúmenes almacenados
  findAll() {
    return this.resumenRepo.find();
  }

  findOne(id: number) {
    return this.resumenRepo.findOneBy({ identificacion: id });
  }

  async update(id: number, updateResumenDonacionDto: UpdateResumenDonacionDto) {
    await this.resumenRepo.update(id, updateResumenDonacionDto);
    return this.findOne(id);
  }

  remove(id: number) {
    return this.resumenRepo.delete(id);
  }
}
