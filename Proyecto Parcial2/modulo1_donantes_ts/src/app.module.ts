import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { HttpModule } from '@nestjs/axios'; // 👈 Importante

import { DonanteModule } from './donante/donante.module';
import { DonacionModule } from './donacion/donacion.module';
import { ProductoModule } from './producto/producto.module';
import { TipoProductoModule } from './tipo-producto/tipo-producto.module';
import { EstadoDonacionModule } from './estado-donacion/estado-donacion.module';
import { AuthModule } from './auth/auth.module';
import { UsuarioModule } from './usuario/usuario.module';
import { Usuario } from './usuario/entities/usuario';
import { ResumenDonacionModule } from './resumen-donacion/resumen-donacion.module';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'postgres',
      host: 'aws-0-us-east-2.pooler.supabase.com',
      port: 5432,
      username: 'postgres.lydoverphmjredhskzzw',
      password: 'nd#vqZUHM5_zE.X',
      database: 'postgres',
      entities: [Usuario, __dirname + '/**/*.entity{.ts,.js}'],
      synchronize: true,
      ssl: {
        rejectUnauthorized: false,
      },
    }),
    HttpModule, // 👈 Asegúrate de que esté aquí
    DonanteModule,
    DonacionModule,
    ProductoModule,
    TipoProductoModule,
    EstadoDonacionModule,
    AuthModule,
    UsuarioModule,
    ResumenDonacionModule,
  ],
})
export class AppModule {}