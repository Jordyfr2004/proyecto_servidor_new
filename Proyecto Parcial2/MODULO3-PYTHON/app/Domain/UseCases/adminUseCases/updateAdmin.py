from app.Domain.Dtos.createAdminDto.updateAdminDto import UpdateAdminDTO
from app.Domain.Interfaces.adminInterfaz.adminterfaz import AdminInterface
from app.Domain.entities.administrador.adminEntitie import Admin

class UpdateAdminUseCase:
    def __init__(self,admin_respository: AdminInterface):
        self.admin_respository = admin_respository

    def execute (self, dto: UpdateAdminDTO) -> Admin:
        admin_actualizado= Admin(
            id= dto.id,
            nombre=dto.nombre,
            usuario=dto.usuario,
            correo=dto.correo,
            password=dto.password
        )

        return self.admin_respository.update(admin_actualizado)