import { IsString } from 'class-validator';

export class CreateTipoProductoDto {
  @IsString()
  nombre: string;

  @IsString()
  descripcion: string;
}
