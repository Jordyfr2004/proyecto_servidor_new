from abc import ABC,abstractmethod
from app.Domain.entities.evento.eventoEntitie import Evento


class EventoInterface(ABC):

    @abstractmethod
    def get_all(self) -> list[Evento]:
        pass
    
    @abstractmethod
    def get_by_id(self, evento_id: int) -> Evento:
        pass

    @abstractmethod
    def create(self, evento: Evento) -> Evento:
        pass

    @abstractmethod
    def update(self, evento: Evento) -> Evento:
        pass

    @abstractmethod
    def delete(self, evento_id: int) -> bool:
        pass