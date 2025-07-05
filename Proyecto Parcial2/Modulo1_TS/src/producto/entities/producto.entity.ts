import { Entity, PrimaryGeneratedColumn, Column, ManyToMany } from 'typeorm';
import { Donacion } from 'src/donacion/entities/donacion.entity';

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

  @ManyToMany(() => Donacion, (donacion) => donacion.productos)
  donaciones: Donacion[];
}
