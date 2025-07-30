import { IsNotEmpty, IsString, IsOptional } from 'class-validator';

// DTO para crear un resumen de donación con validaciones
export class CreateResumenDonacionDto {
  @IsNotEmpty()
  @IsString()
  contenido: string;

  @IsNotEmpty()
  @IsString()
  fecha: string;

  @IsOptional()
  @IsString()
  origen?: string;
}
