import { IsNotEmpty, IsString } from 'class-validator';

export class CreateEstadoDonacionDto {
  @IsNotEmpty()
  @IsString()
  nombre: string;

  @IsNotEmpty()
  @IsString()
  descripcion: string;
}
