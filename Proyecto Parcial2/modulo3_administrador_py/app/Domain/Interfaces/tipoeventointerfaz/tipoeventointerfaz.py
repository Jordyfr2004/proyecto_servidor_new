from abc import ABC, abstractmethod
from app.Domain.entities.tipoEvento.tipoEventoEntitie import TipoEvento



class TipoEventoInterface(ABC):
    
    @abstractmethod
    def get_all(self) -> list[TipoEvento]:
        pass

    @abstractmethod
    def get_by_id(self, tipo_id: int) -> TipoEvento:
        pass

    @abstractmethod
    def create(self, tipo_evento: TipoEvento) -> TipoEvento:
        pass
    
    @abstractmethod
    def update(self, tipo_evento: TipoEvento) -> TipoEvento:
        pass
    
    @abstractmethod
    def delete(self, tipo_id: int) -> bool:
        pass