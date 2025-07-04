import { Entity, PrimaryGeneratedColumn, Column, BaseEntity, OneToMany } from 'typeorm';
import { Donante } from './donante';

@Entity()
export class UbicacionDonante extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  provincia: string;

  @Column()
  ciudad: string;

  @Column()
  direccion: string;

  @OneToMany(() => Donante, donante => donante.ubicacion)
  donantes: Donante[];
}
