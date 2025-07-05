import {
  IsNotEmpty,
  IsString,
  IsNumber,
  IsDateString,
  IsArray,
  ArrayNotEmpty,
} from 'class-validator';

export class CreateDonacionDto {
  @IsNotEmpty()
  @IsDateString()
  fecha: string;

  @IsNotEmpty()
  @IsNumber()
  cantidad: number;

  @IsNotEmpty()
  @IsString()
  descripcion: string;

  @IsNotEmpty()
  @IsNumber()
  id_donante: number;

  @IsArray()
  @ArrayNotEmpty()
  @IsNumber({}, { each: true })
  productos_ids: number[];
}
