from app.Domain.Interfaces.adminInterfaz.adminterfaz import AdminInterface
from app.Domain.entities.administrador.adminEntitie import Admin

class GetAdminByIdUseCase:
    def __init__(self,admin_repository: AdminInterface):
        self.admin_repository = admin_repository

    def execute( self, admin_id: int) -> Admin:
        return self.admin_repository.get_by_id(admin_id)