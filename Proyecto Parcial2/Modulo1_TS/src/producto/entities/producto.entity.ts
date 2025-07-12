import { Entity, PrimaryGeneratedColumn, Column, ManyToMany, ManyToOne } from 'typeorm';
import { Donacion } from 'src/donacion/entities/donacion.entity';
import { TipoProducto } from 'src/tipo-producto/entities/tipo-producto.entity';

@Entity()
export class Producto {
  @PrimaryGeneratedColumn()
  id_producto: number;

  @Column()
  nombre: string;

  @Column()
  categoria: string;

  @Column('float')
  peso: number;

  @Column()
  unidad_medida: string;

  @Column()
  stock: number;

  @ManyToOne(() => TipoProducto, (tipo) => tipo.productos, { eager: true })
  tipo: TipoProducto;

  @ManyToMany(() => Donacion, (donacion) => donacion.productos)
  donaciones: Donacion[];
}
