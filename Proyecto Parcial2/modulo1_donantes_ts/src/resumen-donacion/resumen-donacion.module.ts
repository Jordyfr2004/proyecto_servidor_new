import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ResumenDonacion } from './entities/resumen-donacion.entity';
import { ResumenDonacionService } from './resumen-donacion.service';
import { ResumenDonacionController } from './resumen-donacion.controller';
import { HttpModule } from '@nestjs/axios'; // 👈 IMPORTANTE

@Module({
  imports: [
    TypeOrmModule.forFeature([ResumenDonacion]),
    HttpModule, // 👈 IMPORTA HttpModule AQUÍ
  ],
  controllers: [ResumenDonacionController],
  providers: [ResumenDonacionService],
})
export class ResumenDonacionModule {}
