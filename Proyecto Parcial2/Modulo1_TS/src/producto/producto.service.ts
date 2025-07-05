import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Producto } from './entities/producto.entity';
import { CreateProductoDto } from './dto/create-producto.dto';
import { UpdateProductoDto } from './dto/update-producto.dto';

@Injectable()
export class ProductoService {
  constructor(
    @InjectRepository(Producto)
    private readonly productoRepo: Repository<Producto>,
  ) {}

  create(dto: CreateProductoDto): Promise<Producto> {
    const nuevo = this.productoRepo.create(dto);
    return this.productoRepo.save(nuevo);
  }

  findAll(): Promise<Producto[]> {
    return this.productoRepo.find();
  }

  async findOne(id: number): Promise<Producto> {
    const producto = await this.productoRepo.findOneBy({ id_producto: id });
    if (!producto) {
      throw new NotFoundException(`Producto con id ${id} no encontrado`);
    }
    return producto;
  }

  async update(id: number, dto: UpdateProductoDto): Promise<Producto> {
    await this.productoRepo.update(id, dto);
    return this.findOne(id);
  }

  async remove(id: number): Promise<void> {
    await this.productoRepo.delete(id);
  }
}
