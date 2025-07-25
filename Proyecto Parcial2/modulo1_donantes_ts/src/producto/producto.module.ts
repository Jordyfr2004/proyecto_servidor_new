import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ProductoService } from './producto.service';
import { ProductoController } from './producto.controller';
import { Producto } from './entities/producto.entity';
import { TipoProducto } from 'src/tipo-producto/entities/tipo-producto.entity'; // 👈 importa la entidad TipoProducto

@Module({
  imports: [
    TypeOrmModule.forFeature([Producto, TipoProducto]) // 👈 incluye ambas entidades
  ],
  controllers: [ProductoController],
  providers: [ProductoService],
})
export class ProductoModule {}
