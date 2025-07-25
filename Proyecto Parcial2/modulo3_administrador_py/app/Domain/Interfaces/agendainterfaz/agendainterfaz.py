from abc import ABC, abstractmethod
from app.Domain.entities.agenda.agendaEntitie import Agenda

class AgendaInterface(ABC):

    @abstractmethod
    def get_all(self) -> list[Agenda]:
        pass

    @abstractmethod
    def get_by_id(self, agenda_id: int) -> Agenda:
        pass

    @abstractmethod
    def create(self, agenda: Agenda) -> Agenda:
        pass

    @abstractmethod
    def update(self, agenda: Agenda) -> Agenda:
        pass

    @abstractmethod
    def delete(self, agenda_id: int) -> bool:
        pass