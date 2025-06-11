import { Entity, PrimaryGeneratedColumn, Column, BaseEntity, OneToMany } from 'typeorm';
import { Donante } from './donante';

@Entity()
export class EmpresaInfo extends BaseEntity {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  nombre_empresa: string;

  @Column()
  ruc: string;

  @Column()
  representante_legal: string;

  @OneToMany(() => Donante, donante => donante.empresaInfo)
  donantes: Donante[];
}

