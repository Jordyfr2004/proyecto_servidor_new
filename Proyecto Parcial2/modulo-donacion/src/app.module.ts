import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { DonanteModule } from './donante/donante.module';
import { DonacionModule } from './donacion/donacion.module';
import { ProductoModule } from './producto/producto.module';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'postgres',
      host: 'aws-0-us-east-2.pooler.supabase.com',
      port: 5432,
      username: 'postgres.lydoverphmjredhskzzw',
      password: 'nd#vqZUHM5_zE.X',
      database: 'postgres',
      entities: [__dirname + '/**/*.entity{.ts,.js}'],
      synchronize: true,
      ssl: {
        rejectUnauthorized: false,
      },
    }),
    DonanteModule, 
    DonacionModule, 
    ProductoModule,
  ],
})
export class AppModule {}
