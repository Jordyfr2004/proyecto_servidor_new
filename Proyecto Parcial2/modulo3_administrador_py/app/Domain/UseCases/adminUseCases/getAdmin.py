from app.Domain.Interfaces.adminInterfaz.adminterfaz import AdminInterface


class GetAdminUseCase:
    def __init__(self,admin_repository: AdminInterface):
        self.admin_repository = admin_repository

    def execute(self):
        return self.admin_repository.get_all()