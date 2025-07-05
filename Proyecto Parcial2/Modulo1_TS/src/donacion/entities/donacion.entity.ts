import { Entity, PrimaryGeneratedColumn, Column, ManyToOne, ManyToMany, JoinTable } from 'typeorm';
import { Donante } from 'src/donante/entities/donante.entity';
import { Producto } from 'src/producto/entities/producto.entity';

@Entity()
export class Donacion {
  @PrimaryGeneratedColumn()
  id_donacion: number;

  @Column({ type: 'date' })
  fecha: string;

  @Column('int')
  cantidad: number;

  @Column()
  descripcion: string;

  @ManyToOne(() => Donante, (donante) => donante.donaciones, { eager: true })
  donante: Donante;

  @ManyToMany(() => Producto, { eager: true })
  @JoinTable()
  productos: Producto[];
}
