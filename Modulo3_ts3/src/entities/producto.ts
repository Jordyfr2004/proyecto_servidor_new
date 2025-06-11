import { Entity, PrimaryGeneratedColumn, Column, BaseEntity, ManyToOne } from 'typeorm';
import { Donacion } from './donacion';


@Entity()
export class Producto extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  nombre: string;

  @Column()
  cantidad: number;

  @ManyToOne(() => Donacion, donacion => donacion.productos)
  donacion: Donacion;

}
