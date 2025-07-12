from app.Domain.entities.administrador.adminEntitie import Admin
from app.Domain.Interfaces.adminInterfaz.adminterfaz import AdminInterface

class CreateAdminUseCase:
    def __init__(self, admin_repository: AdminInterface):
        self.admin_repository = admin_repository


    def execute(self, nombre: str, usuario: str, correo: str, password: str) -> Admin:
        nuevo_admin= Admin(nombre= nombre, usuario=usuario, correo=correo, password=password )
        return self.admin_repository.create(nuevo_admin)