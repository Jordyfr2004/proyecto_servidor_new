import { Test, TestingModule } from '@nestjs/testing';
import { DonacionService } from './donacion.service';
import { getRepositoryToken } from '@nestjs/typeorm';
import { Donacion } from './entities/donacion.entity';
import { Donante } from 'src/donante/entities/donante.entity';
import { Producto } from 'src/producto/entities/producto.entity';
import { EstadoDonacion } from 'src/estado-donacion/entities/estado-donacion.entity';
import { CreateDonacionDto } from './dto/create-donacion.dto';
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';

describe('DonacionService', () => {
  let service: DonacionService;
  let mockAxios: MockAdapter;

  beforeEach(async () => {
    mockAxios = new MockAdapter(axios);

    const module: TestingModule = await Test.createTestingModule({
      providers: [
        DonacionService,
        { provide: getRepositoryToken(Donacion), useValue: { create: jest.fn(), save: jest.fn() } },
        { provide: getRepositoryToken(Donante), useValue: { findOneBy: jest.fn().mockResolvedValue({ id_donante: 1, nombre: 'Test' }) } },
        { provide: getRepositoryToken(Producto), useValue: { find: jest.fn().mockResolvedValue([{ id_producto: 1 }]) } },
        { provide: getRepositoryToken(EstadoDonacion), useValue: { findOneBy: jest.fn().mockResolvedValue({ id_estado: 1 }) } },
      ],
    }).compile();

    service = module.get<DonacionService>(DonacionService);
  });

  afterEach(() => {
    mockAxios.reset();
  });

  it('debe enviar una notificación WebSocket al crear una donación', async () => {
    mockAxios.onPost('http://localhost:3001/notificar').reply(200);

    const dto: CreateDonacionDto = {
      fecha: '2025-07-27',
      cantidad: 5,
      descripcion: 'Donación de prueba',
      id_donante: 1,
      productos_ids: [1],
      id_estado: 1,
    };

    const result = await service.create(dto);
    expect(mockAxios.history.post.length).toBe(1);

    const payload = JSON.parse(mockAxios.history.post[0].data);
    expect(payload.evento).toBe('nueva_donacion');
    expect(payload.mensaje).toContain('Donación registrada por');
  });
});
