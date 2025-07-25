import { Entity, PrimaryGeneratedColumn, Column, OneToMany } from 'typeorm';
import { Producto } from 'src/producto/entities/producto.entity';

@Entity()
export class TipoProducto {
  @PrimaryGeneratedColumn()
  id_tipo: number;

  @Column()
  nombre: string;

  @Column()
  descripcion: string;

  @OneToMany(() => Producto, (producto) => producto.tipo)
  productos: Producto[];
}
