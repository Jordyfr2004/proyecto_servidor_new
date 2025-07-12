from abc import ABC, abstractmethod
from app.Domain.entities.administrador.adminEntitie import Admin

class AdminInterface(ABC):

    @abstractmethod
    def get_all(self) -> list[Admin]:
        pass

    @abstractmethod
    def get_by_id(self, admin_id: int) -> Admin:
        pass

    @abstractmethod
    def create(self, admin: Admin) -> Admin:
        pass

    @abstractmethod
    def update(self, admin: Admin) -> Admin:
        pass

    @abstractmethod
    def delete(self, admin_id: int) -> bool:
        pass