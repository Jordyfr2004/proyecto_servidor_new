import {
  Entity, PrimaryGeneratedColumn, Column, BaseEntity,
  CreateDateColumn, UpdateDateColumn, ManyToOne, OneToMany
} from 'typeorm';
import { UbicacionDonante } from './Ubicacion';
import { EmpresaInfo } from './empresaInfo';
import { Donacion } from './donacion';

@Entity()
export class Donante extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  nombre: string;

  @Column()
  apellido: string;

  @Column()
  tipo_donante: string;

  @Column()
  telefono: string;

  @Column()
  correo: string;

  @Column()
  password: string;

  @CreateDateColumn()
  createdAt: Date;

  @UpdateDateColumn()
  updateAt: Date;

  @ManyToOne(() => UbicacionDonante, ubicacion => ubicacion.donantes, { nullable: false })
  ubicacion: UbicacionDonante;

  @ManyToOne(() => EmpresaInfo, empresaInfo => empresaInfo.donantes, { nullable: true })
  empresaInfo?: EmpresaInfo;

   @OneToMany(() => Donacion, donacion => donacion.donante)
   donaciones: Donacion[];
}
