from app.Domain.Interfaces.adminInterfaz.adminterfaz import AdminInterface
from app.Domain.entities.administrador.adminEntitie import Admin

class UpdateAdminUseCase:
    def __init__(self,admin_respository: AdminInterface):
        self.admin_respository = admin_respository

    def execute (self, admin_id: int, nombre: str, usuario: str, correo: str, password: str) -> Admin:
        admin_actualizado= Admin(
            id= admin_id,
            nombre=nombre,
            usuario=usuario,
            correo=correo,
            password=password
        )

        return self.admin_respository.update(admin_actualizado)