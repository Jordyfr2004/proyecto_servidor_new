import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { TipoProducto } from './entities/tipo-producto.entity';
import { TipoProductoService } from './tipo-producto.service';
import { TipoProductoController } from './tipo-producto.controller';

@Module({
  imports: [TypeOrmModule.forFeature([TipoProducto])],
  controllers: [TipoProductoController],
  providers: [TipoProductoService],
  exports: [TypeOrmModule],
})
export class TipoProductoModule {}
