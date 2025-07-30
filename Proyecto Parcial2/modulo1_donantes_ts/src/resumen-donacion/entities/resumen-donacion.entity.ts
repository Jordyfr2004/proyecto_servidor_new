import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity('resumen_donacion')
export class ResumenDonacion {
  @PrimaryGeneratedColumn()
  identificacion: number;

  @Column('text')
  contenido: string;

  @Column()
  fecha: string;

  @Column({ nullable: true })
  origen: string;
}
