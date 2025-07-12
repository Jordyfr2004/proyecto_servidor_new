import { Entity, PrimaryGeneratedColumn, Column, OneToMany } from 'typeorm';
import { Donacion } from 'src/donacion/entities/donacion.entity';

@Entity()
export class EstadoDonacion {
  @PrimaryGeneratedColumn()
  id_estado: number;

  @Column()
  nombre: string;

  @Column()
  descripcion: string;

  @OneToMany(() => Donacion, (donacion) => donacion.estado)
  donaciones: Donacion[];
}
