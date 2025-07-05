import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { DonacionService } from './donacion.service';
import { DonacionController } from './donacion.controller';
import { Donacion } from './entities/donacion.entity';
import { Donante } from 'src/donante/entities/donante.entity';
import { Producto } from 'src/producto/entities/producto.entity'; // 👈
import { ProductoModule } from 'src/producto/producto.module'; // 👈

@Module({
  imports: [
    TypeOrmModule.forFeature([Donacion, Donante, Producto]), // 👈 incluye Producto
    ProductoModule, // 👈 importa el módulo que lo expone
  ],
  controllers: [DonacionController],
  providers: [DonacionService],
})
export class DonacionModule {}
