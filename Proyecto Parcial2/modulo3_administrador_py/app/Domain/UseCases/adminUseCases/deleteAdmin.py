from app.Domain.Interfaces.adminInterfaz.adminterfaz import AdminInterface

class DeleteAdminUseCase:
    def __init__(self, admin_repository: AdminInterface):
        self.admin_repository = admin_repository

    def execute(self, admin_id:int) -> bool:
        admin = self.admin_repository.get_by_id(admin_id)
        if not admin:
            return False
        self.admin_repository.delete(admin_id)
        return True
