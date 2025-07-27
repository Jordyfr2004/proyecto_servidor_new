import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Producto } from './entities/producto.entity';
import { CreateProductoDto } from './dto/create-producto.dto';
import { UpdateProductoDto } from './dto/update-producto.dto';
import { TipoProducto } from 'src/tipo-producto/entities/tipo-producto.entity';
import axios from 'axios'; // 👈 AÑADIDO

@Injectable()
export class ProductoService {
  constructor(
    @InjectRepository(Producto)
    private readonly productoRepo: Repository<Producto>,

    @InjectRepository(TipoProducto)
    private readonly tipoProductoRepo: Repository<TipoProducto>,
  ) {}

  async create(dto: CreateProductoDto): Promise<Producto> {
    const tipo = await this.tipoProductoRepo.findOneBy({ id_tipo: dto.id_tipo });
    if (!tipo) throw new NotFoundException('Tipo de producto no encontrado');

    const nuevo = this.productoRepo.create({ ...dto, tipo });
    const productoGuardado = await this.productoRepo.save(nuevo);

    // 👇 Enviar notificación al WebSocket
    try {
      await axios.post('http://localhost:3001/notificar', {
        evento: 'nuevo_producto',
        mensaje: `Nuevo producto registrado: ${productoGuardado.nombre}, stock: ${productoGuardado.stock}`,
        fecha: new Date().toISOString(),
      });
    } catch (error) {
      console.error('Error al enviar notificación WebSocket:', error.message);
    }

    return productoGuardado;
  }

  findAll(): Promise<Producto[]> {
    return this.productoRepo.find({ relations: ['tipo'] });
  }

  async findOne(id: number): Promise<Producto> {
    const producto = await this.productoRepo.findOne({
      where: { id_producto: id },
      relations: ['tipo'],
    });
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
