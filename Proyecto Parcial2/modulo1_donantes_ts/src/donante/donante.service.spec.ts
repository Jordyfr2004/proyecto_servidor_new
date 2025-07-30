import { Test, TestingModule } from '@nestjs/testing';
import { DonanteService } from './donante.service';
import { getRepositoryToken } from '@nestjs/typeorm';
import { Donante } from './entities/donante.entity';
import { Repository } from 'typeorm';
import { CreateDonanteDto } from './dto/create-donante.dto';
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';

describe('DonanteService', () => {
  let service: DonanteService;
  let donanteRepo: Repository<Donante>;
  let mockAxios: MockAdapter;

  beforeEach(async () => {
    mockAxios = new MockAdapter(axios);

    const module: TestingModule = await Test.createTestingModule({
      providers: [
        DonanteService,
        {
          provide: getRepositoryToken(Donante),
          useValue: {
            create: jest.fn().mockImplementation(dto => dto),
            save: jest.fn().mockImplementation(dto => Promise.resolve({ ...dto, id_donante: 1 })),
          },
        },
      ],
    }).compile();

    service = module.get<DonanteService>(DonanteService);
    donanteRepo = module.get<Repository<Donante>>(getRepositoryToken(Donante));
  });

  afterEach(() => {
    mockAxios.reset();
  });

  it('debe enviar una notificación WebSocket al registrar un nuevo donante', async () => {
    mockAxios.onPost('http://localhost:3001/notificar').reply(200);

    const dto: CreateDonanteDto = {
      nombre: 'Luis Sánchez',
      tipo: 'Natural',
      correo: 'luis@mail.com',
      telefono: '0991234567',
      direccion: 'Av. Los Cipreses',
      fecha_registro: new Date('2025-07-27'),
    };

    const result = await service.create(dto);
    expect(result).toHaveProperty('id_donante');

    expect(mockAxios.history.post.length).toBe(1);
    const payload = JSON.parse(mockAxios.history.post[0].data);

    expect(payload.evento).toBe('nuevo_donante');
    expect(payload.mensaje).toContain(dto.nombre);
  });
});
