import { Entity, PrimaryGeneratedColumn, Column, OneToMany } from 'typeorm';
import { Donacion } from 'src/donacion/entities/donacion.entity';

@Entity()
export class Donante {
  @PrimaryGeneratedColumn()
  id_donante: number;

  @Column()
  nombre: string;

  @Column()
  tipo: string;

  @Column()
  correo: string;

  @Column()
  telefono: string;

  @Column()
  direccion: string;

  @Column({ type: 'timestamp' }) // <-- Ahora es tipo timestamp (Date en TypeScript)
  fecha_registro: Date;

  @OneToMany(() => Donacion, (donacion) => donacion.donante)
  donaciones: Donacion[];
}
