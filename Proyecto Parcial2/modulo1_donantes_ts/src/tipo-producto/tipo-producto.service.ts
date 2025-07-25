import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { TipoProducto } from './entities/tipo-producto.entity';
import { CreateTipoProductoDto } from './dto/create-tipo-producto.dto';
import { UpdateTipoProductoDto } from './dto/update-tipo-producto.dto';

@Injectable()
export class TipoProductoService {
  constructor(
    @InjectRepository(TipoProducto)
    private tipoRepo: Repository<TipoProducto>,
  ) {}

  create(dto: CreateTipoProductoDto) {
    const nuevo = this.tipoRepo.create(dto);
    return this.tipoRepo.save(nuevo);
  }

  findAll() {
    return this.tipoRepo.find();
  }

  findOne(id: number) {
    return this.tipoRepo.findOneBy({ id_tipo: id });
  }

  async update(id: number, dto: UpdateTipoProductoDto) {
    const tipo = await this.tipoRepo.findOneBy({ id_tipo: id });
    if (!tipo) return null;
    Object.assign(tipo, dto);
    return this.tipoRepo.save(tipo);
  }

  async remove(id: number) {
    await this.tipoRepo.delete(id);
  }
}
