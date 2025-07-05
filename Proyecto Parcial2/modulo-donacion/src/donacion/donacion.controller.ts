import { Controller, Get, Post, Body, Param, Delete, Put } from '@nestjs/common';
import { DonacionService } from './donacion.service';
import { CreateDonacionDto } from './dto/create-donacion.dto';
import { UpdateDonacionDto } from './dto/update-donacion.dto';

@Controller('donacion')
export class DonacionController {
  constructor(private readonly donacionService: DonacionService) {}

  @Post()
  create(@Body() dto: CreateDonacionDto) {
    return this.donacionService.create(dto);
  }

  @Get()
  findAll() {
    return this.donacionService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.donacionService.findOne(+id);
  }

  @Put(':id')
  update(@Param('id') id: string, @Body() dto: UpdateDonacionDto) {
    return this.donacionService.update(+id, dto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.donacionService.remove(+id);
  }
}
