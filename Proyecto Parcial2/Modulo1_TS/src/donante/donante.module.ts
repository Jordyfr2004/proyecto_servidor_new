import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { DonanteService } from './donante.service';
import { DonanteController } from './donante.controller';
import { Donante } from './entities/donante.entity';

@Module({
  imports: [TypeOrmModule.forFeature([Donante])],
  controllers: [DonanteController],
  providers: [DonanteService],
})
export class DonanteModule {}
