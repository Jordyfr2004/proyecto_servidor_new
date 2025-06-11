import { Entity, PrimaryGeneratedColumn, Column, BaseEntity, CreateDateColumn, OneToMany,ManyToOne } from 'typeorm';
import { Donante } from './donante';
import { Producto } from './producto';

@Entity()
export class Donacion extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @CreateDateColumn()
  fecha: Date;

  @Column()
  estado_donacion: string;

  @Column()
  descripcion_donacion: string;

  @OneToMany(() => Producto, producto => producto.donacion, { cascade: true })
  productos: Producto[];

  @ManyToOne(() => Donante, donante => donante.donaciones)
  donante: Donante;

}
