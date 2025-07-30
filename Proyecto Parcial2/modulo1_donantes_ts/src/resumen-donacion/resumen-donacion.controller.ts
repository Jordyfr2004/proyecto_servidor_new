import { 
  Controller, 
  Get, 
  Post, 
  Body, 
  Patch, 
  Param, 
  Delete, 
  HttpCode 
} from '@nestjs/common';
import { HttpService } from '@nestjs/axios';
import { lastValueFrom } from 'rxjs';

import { ResumenDonacionService } from './resumen-donacion.service';
import { CreateResumenDonacionDto } from './dto/create-resumen-donacion.dto';
import { UpdateResumenDonacionDto } from './dto/update-resumen-donacion.dto';

@Controller('resumen-donacion')
export class ResumenDonacionController {
  constructor(
    private readonly resumenDonacionService: ResumenDonacionService,
    private readonly httpService: HttpService
  ) {}

  @Post()
  create(@Body() createResumenDonacionDto: CreateResumenDonacionDto) {
    return this.resumenDonacionService.create(createResumenDonacionDto);
  }

  @Get()
  findAll() {
    return this.resumenDonacionService.findAll();
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.resumenDonacionService.findOne(+id);
  }

  @Patch(':id')
  update(@Param('id') id: string, @Body() updateResumenDonacionDto: UpdateResumenDonacionDto) {
    return this.resumenDonacionService.update(+id, updateResumenDonacionDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.resumenDonacionService.remove(+id);
  }

  // ✅ Endpoint que dispara el flujo de n8n
  @Post('start-agent')
  @HttpCode(200)
  async startAgent() {
    const webhookUrl = 'http://localhost:5678/webhook/produccion/iniciar-agente';

    try {
      // Enviamos solicitud al webhook de n8n para iniciar el flujo
      const response = await lastValueFrom(
        this.httpService.post(webhookUrl, {
          mensaje: 'Iniciar desde NestJS'
        })
      );

      return {
        mensaje: 'Agente activado correctamente desde NestJS',
        respuesta: response.data
      };
    } catch (error) {
      return {
        error: 'Error al activar agente',
        detalle: error.message
      };
    }
  }
}
