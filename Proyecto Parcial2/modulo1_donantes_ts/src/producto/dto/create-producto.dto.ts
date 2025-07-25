import {
  IsNotEmpty,
  IsString,
  IsNumber,
  Min,
} from 'class-validator';

export class CreateProductoDto {
  @IsNotEmpty()
  @IsString()
  nombre: string;

  @IsNotEmpty()
  @IsString()
  categoria: string;

  @IsNotEmpty()
  @IsNumber()
  @Min(0)
  peso: number;

  @IsNotEmpty()
  @IsString()
  unidad_medida: string;

  @IsNotEmpty()
  @IsNumber()
  @Min(0)
  stock: number;

  @IsNotEmpty()
  @IsNumber()
  id_tipo: number; // Relación con TipoProducto
}
