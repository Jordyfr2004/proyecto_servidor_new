import { Controller, Get, Post, Body, Param, Delete, Put } from '@nestjs/common';
import { DonanteService } from './donante.service';
import { CreateDonanteDto } from './dto/create-donante.dto';
import { UpdateDonanteDto } from './dto/update-donante.dto';

@Controller('donante')
export class DonanteController {
  constructor(private readonly donanteService: DonanteService) {}

  @Post()
  create(@Body() dto: CreateDonanteDto) {
    return this.donanteService.create(dto);
  }

  @Get()
  findAll() {
    return this.donanteService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.donanteService.findOne(+id);
  }

  @Put(':id')
  update(@Param('id') id: string, @Body() dto: UpdateDonanteDto) {
    return this.donanteService.update(+id, dto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.donanteService.remove(+id);
  }
}
