from app.Domain.Dtos.createAdminDto.createAdminDto import CreateAdminDTO
from app.Domain.entities.administrador.adminEntitie import Admin
from app.Domain.Interfaces.adminInterfaz.adminterfaz import AdminInterface

class CreateAdminUseCase:
    def __init__(self, admin_repository: AdminInterface):
        self.admin_repository = admin_repository


    def execute(self, dto: CreateAdminDTO) -> Admin:
        nuevo_admin= Admin(
            nombre=dto.nombre,
            usuario=dto.usuario,
            correo=dto.correo,
            password=dto.password
        )
        return self.admin_repository.create(nuevo_admin)