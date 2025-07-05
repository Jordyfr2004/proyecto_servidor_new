import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Donacion } from './entities/donacion.entity';
import { CreateDonacionDto } from './dto/create-donacion.dto';
import { UpdateDonacionDto } from './dto/update-donacion.dto';
import { Donante } from 'src/donante/entities/donante.entity';
import { Producto } from 'src/producto/entities/producto.entity';

@Injectable()
export class DonacionService {
  constructor(
    @InjectRepository(Donacion)
    private donacionRepo: Repository<Donacion>,

    @InjectRepository(Donante)
    private donanteRepo: Repository<Donante>,

    @InjectRepository(Producto)
    private productoRepo: Repository<Producto>,
  ) {}

  async create(dto: CreateDonacionDto): Promise<Donacion> {
    const donante = await this.donanteRepo.findOneBy({ id_donante: dto.id_donante });
    if (!donante) throw new Error('Donante no encontrado');

    const productos = await this.productoRepo.findByIds(dto.productos_ids ?? []);
    if (productos.length === 0) throw new Error('Productos no encontrados');

    const nueva = this.donacionRepo.create({
      fecha: dto.fecha,
      cantidad: dto.cantidad,
      descripcion: dto.descripcion,
      donante,
      productos,
    });

    return this.donacionRepo.save(nueva);
  }

  findAll(): Promise<Donacion[]> {
    return this.donacionRepo.find();
  }

  findOne(id: number): Promise<Donacion | null> {
    return this.donacionRepo.findOneBy({ id_donacion: id });
  }

  async update(id: number, dto: UpdateDonacionDto): Promise<Donacion | null> {
    const donacion = await this.donacionRepo.findOneBy({ id_donacion: id });
    if (!donacion) return null;

    Object.assign(donacion, dto);
    return this.donacionRepo.save(donacion);
  }

  async remove(id: number): Promise<void> {
    await this.donacionRepo.delete(id);
  }
}
