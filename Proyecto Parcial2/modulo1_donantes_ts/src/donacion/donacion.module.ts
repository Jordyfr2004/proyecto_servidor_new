import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { DonacionService } from './donacion.service';
import { DonacionController } from './donacion.controller';
import { Donacion } from './entities/donacion.entity';
import { Donante } from 'src/donante/entities/donante.entity';
import { Producto } from 'src/producto/entities/producto.entity';
import { EstadoDonacion } from 'src/estado-donacion/entities/estado-donacion.entity'; // ✅ AÑADE ESTA LÍNEA
import { ProductoModule } from 'src/producto/producto.module';

@Module({
  imports: [
    TypeOrmModule.forFeature([
      Donacion,
      Donante,
      Producto,
      EstadoDonacion, // ✅ AÑADE ESTA LÍNEA
    ]),
    ProductoModule,
  ],
  controllers: [DonacionController],
  providers: [DonacionService],
})
export class DonacionModule {}
