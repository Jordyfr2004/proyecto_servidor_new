import { PartialType } from '@nestjs/mapped-types';
import { CreateResumenDonacionDto } from './create-resumen-donacion.dto';

export class UpdateResumenDonacionDto extends PartialType(CreateResumenDonacionDto) {}
