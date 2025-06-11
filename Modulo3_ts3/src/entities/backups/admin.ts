import {Column, Entity, CreateDateColumn, UpdateDateColumn, PrimaryGeneratedColumn, BaseEntity, OneToMany} from 'typeorm';
import { Donante } from '../donante';

@Entity()
export class Admin extends BaseEntity{
    @PrimaryGeneratedColumn()
    id: number

    @Column()
    nombre : string

    @Column()
    apellido : string

    @Column()
    telefono : number

    @Column()
    correo : string

    @Column()
    password : string

    @CreateDateColumn()
    createdAt: Date

    @UpdateDateColumn()
    updateAt: Date

    @OneToMany(() => Donante, donante => donante.ubicacion)
    donantes: Donante[];

}