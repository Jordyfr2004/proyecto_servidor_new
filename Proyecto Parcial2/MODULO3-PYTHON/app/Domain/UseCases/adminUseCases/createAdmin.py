import bcrypt
from app.Domain.Dtos.createAdminDto.createAdminDto import CreateAdminDTO
from app.Domain.entities.administrador.adminEntitie import Admin
from app.Domain.Interfaces.adminInterfaz.adminterfaz import AdminInterface

class CreateAdminUseCase:
    def __init__(self, admin_repository: AdminInterface):
        self.admin_repository = admin_repository


    def execute(self, dto: CreateAdminDTO) -> Admin:

        # 1. Hashear la contraseña
        hashed_password = bcrypt.hashpw(dto.password.encode('utf-8'), bcrypt.gensalt())

        # 2. Convertir el hash a string para guardarlo en la DB
        hashed_password_str = hashed_password.decode('utf-8')

        nuevo_admin= Admin(
            nombre=dto.nombre,
            usuario=dto.usuario,
            correo=dto.correo,
            password=hashed_password_str
        )
        return self.admin_repository.create(nuevo_admin)