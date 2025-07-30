import { Test, TestingModule } from '@nestjs/testing';
import { ProductoService } from './producto.service';
import { getRepositoryToken } from '@nestjs/typeorm';
import { Producto } from './entities/producto.entity';
import { TipoProducto } from 'src/tipo-producto/entities/tipo-producto.entity';
import { Repository } from 'typeorm';
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import { CreateProductoDto } from './dto/create-producto.dto';

describe('ProductoService', () => {
  let service: ProductoService;
  let mockAxios: MockAdapter;

  beforeEach(async () => {
    mockAxios = new MockAdapter(axios);

    const module: TestingModule = await Test.createTestingModule({
      providers: [
        ProductoService,
        {
          provide: getRepositoryToken(Producto),
          useValue: {
            create: jest.fn().mockImplementation(dto => dto),
            save: jest.fn().mockImplementation(dto => Promise.resolve({ ...dto, id_producto: 1 })),
          },
        },
        {
          provide: getRepositoryToken(TipoProducto),
          useValue: {
            findOneBy: jest.fn().mockResolvedValue({ id_tipo: 1, nombre: 'Granos' }),
          },
        },
      ],
    }).compile();

    service = module.get<ProductoService>(ProductoService);
  });

  afterEach(() => {
    mockAxios.reset();
  });

  it('debe enviar una notificación WebSocket al registrar un nuevo producto', async () => {
    mockAxios.onPost('http://localhost:3001/notificar').reply(200);

    const dto: CreateProductoDto = {
      nombre: 'Arroz',
      categoria: 'Granos',
      peso: 2.5,
      unidad_medida: 'kg',
      stock: 100,
      id_tipo: 1,
    };

    const result = await service.create(dto);
    expect(result).toHaveProperty('id_producto');

    expect(mockAxios.history.post.length).toBe(1);
    const payload = JSON.parse(mockAxios.history.post[0].data);
    expect(payload.evento).toBe('nuevo_producto');
    expect(payload.mensaje).toContain(dto.nombre);
  });
});
