import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { EstadoDonacion } from './entities/estado-donacion.entity';
import { EstadoDonacionService } from './estado-donacion.service';
import { EstadoDonacionController } from './estado-donacion.controller';

@Module({
  imports: [TypeOrmModule.forFeature([EstadoDonacion])],
  providers: [EstadoDonacionService],
  controllers: [EstadoDonacionController],
  exports: [EstadoDonacionService],
})
export class EstadoDonacionModule {}
