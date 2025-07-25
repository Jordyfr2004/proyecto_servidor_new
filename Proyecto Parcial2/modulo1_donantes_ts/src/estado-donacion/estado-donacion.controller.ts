import { Controller, Post, Get, Body, Param, Delete } from '@nestjs/common';
import { EstadoDonacionService } from './estado-donacion.service';
import { CreateEstadoDonacionDto } from './dto/create-estado-donacion.dto';

@Controller('estado-donacion')
export class EstadoDonacionController {
  constructor(private readonly estadoService: EstadoDonacionService) {}

  @Post()
  create(@Body() dto: CreateEstadoDonacionDto) {
    return this.estadoService.create(dto);
  }

  @Get()
  findAll() {
    return this.estadoService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.estadoService.findOne(+id);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.estadoService.remove(+id);
  }
}
