import {Column, Entity, CreateDateColumn, UpdateDateColumn, PrimaryGeneratedColumn, BaseEntity, OneToMany} from 'typeorm';


@Entity()
export class Tipodonacion extends BaseEntity {
    @PrimaryGeneratedColumn()
    id: number;

    @Column()
    descripcion_tipo:string

    @Column()
    Descripcion: string


}