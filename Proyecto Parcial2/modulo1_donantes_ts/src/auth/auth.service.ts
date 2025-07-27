import { Injectable, UnauthorizedException } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Usuario } from '../usuario/entities/usuario';
import * as bcrypt from 'bcryptjs';

@Injectable()
export class AuthService {
  constructor(
    @InjectRepository(Usuario)
    private usuarioRepo: Repository<Usuario>,
    private jwtService: JwtService,
  ) {}

  async register(nombre: string, email: string, password: string) {
    const existe = await this.usuarioRepo.findOne({ where: { email } });
    if (existe) throw new Error('El correo ya está en uso');

    const hashedPassword = await bcrypt.hash(password, 10);
    const nuevoUsuario = this.usuarioRepo.create({ nombre, email, password: hashedPassword });
    await this.usuarioRepo.save(nuevoUsuario);

    return { mensaje: 'Usuario registrado correctamente' };
  }

  async login(email: string, password: string) {
    const usuario = await this.usuarioRepo.findOne({ where: { email } });
    if (!usuario || !(await bcrypt.compare(password, usuario.password))) {
      throw new UnauthorizedException('Credenciales inválidas');
    }

    const payload = { sub: usuario.id, email: usuario.email };
    const token = this.jwtService.sign(payload);

    return { access_token: token };
  }
}
