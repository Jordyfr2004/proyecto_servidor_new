import { PartialType } from '@nestjs/mapped-types';
import { CreateEstadoDonacionDto } from './create-estado-donacion.dto';

export class UpdateEstadoDonacionDto extends PartialType(CreateEstadoDonacionDto) {}
