import { Injectable, HttpException, HttpStatus } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository, In } from 'typeorm';
import { Donacion } from './entities/donacion.entity';
import { CreateDonacionDto } from './dto/create-donacion.dto';
import { UpdateDonacionDto } from './dto/update-donacion.dto';
import { Donante } from 'src/donante/entities/donante.entity';
import { Producto } from 'src/producto/entities/producto.entity';
import { EstadoDonacion } from 'src/estado-donacion/entities/estado-donacion.entity';
import axios from 'axios'; // 👈 AÑADIDO

@Injectable()
export class DonacionService {
  constructor(
    @InjectRepository(Donacion)
    private donacionRepo: Repository<Donacion>,

    @InjectRepository(Donante)
    private donanteRepo: Repository<Donante>,

    @InjectRepository(Producto)
    private productoRepo: Repository<Producto>,

    @InjectRepository(EstadoDonacion)
    private estadoRepo: Repository<EstadoDonacion>,
  ) {}

  async create(dto: CreateDonacionDto): Promise<Donacion> {
    const donante = await this.donanteRepo.findOneBy({ id_donante: dto.id_donante });
    if (!donante) {
      throw new HttpException('Donante no encontrado', HttpStatus.NOT_FOUND);
    }

    if (!dto.productos_ids || dto.productos_ids.length === 0) {
      throw new HttpException('Debe proporcionar al menos un producto', HttpStatus.BAD_REQUEST);
    }

    const productos = await this.productoRepo.find({
      where: { id_producto: In(dto.productos_ids) },
      relations: { tipo: true },
    });
    if (productos.length === 0) {
      throw new HttpException('Productos no encontrados', HttpStatus.NOT_FOUND);
    }

    const estado = await this.estadoRepo.findOneBy({ id_estado: dto.id_estado });
    if (!estado) {
      throw new HttpException('Estado no encontrado', HttpStatus.NOT_FOUND);
    }

    const nueva = this.donacionRepo.create({
      fecha: dto.fecha,
      cantidad: dto.cantidad,
      descripcion: dto.descripcion,
      donante,
      productos,
      estado,
    });

    const donacionGuardada = await this.donacionRepo.save(nueva);

    // 👇 Enviar notificación al WebSocket
    try {
      await axios.post('http://localhost:3001/notificar', {
        evento: 'nueva_donacion',
        mensaje: `Donación registrada por ${donante.nombre}, cantidad: ${dto.cantidad}`,
        fecha: new Date().toISOString(),
      });
    } catch (error) {
      console.error('Error al enviar notificación WebSocket:', error.message);
    }

    return donacionGuardada;
  }

  findAll(): Promise<Donacion[]> {
    return this.donacionRepo.find({
      relations: {
        productos: {
          tipo: true,
        },
        donante: true,
        estado: true,
      },
    });
  }

  findOne(id: number): Promise<Donacion | null> {
    return this.donacionRepo.findOne({
      where: { id_donacion: id },
      relations: {
        productos: {
          tipo: true,
        },
        donante: true,
        estado: true,
      },
    });
  }

  async update(id: number, dto: UpdateDonacionDto): Promise<Donacion | null> {
    const donacion = await this.donacionRepo.findOne({
      where: { id_donacion: id },
      relations: { productos: true, donante: true, estado: true },
    });
    if (!donacion) return null;

    if (dto.id_donante) {
      const nuevoDonante = await this.donanteRepo.findOneBy({ id_donante: dto.id_donante });
      if (!nuevoDonante) {
        throw new HttpException('Donante no encontrado', HttpStatus.NOT_FOUND);
      }
      donacion.donante = nuevoDonante;
    }

    if (dto.id_estado) {
      const nuevoEstado = await this.estadoRepo.findOneBy({ id_estado: dto.id_estado });
      if (!nuevoEstado) {
        throw new HttpException('Estado no encontrado', HttpStatus.NOT_FOUND);
      }
      donacion.estado = nuevoEstado;
    }

    if (dto.productos_ids && dto.productos_ids.length > 0) {
      const nuevosProductos = await this.productoRepo.find({
        where: { id_producto: In(dto.productos_ids) },
      });
      if (nuevosProductos.length === 0) {
        throw new HttpException('Productos no encontrados', HttpStatus.NOT_FOUND);
      }
      donacion.productos = nuevosProductos;
    }

    donacion.fecha = dto.fecha ?? donacion.fecha;
    donacion.cantidad = dto.cantidad ?? donacion.cantidad;
    donacion.descripcion = dto.descripcion ?? donacion.descripcion;

    return this.donacionRepo.save(donacion);
  }

  async remove(id: number): Promise<void> {
    await this.donacionRepo.delete(id);
  }
}
