from abc import ABC, abstractmethod
from app.Domain.entities.revisionSolicitud.revisionSolicitudEntitie import RevisionSolicitud


class RevisionSolicitudInterface(ABC):

    @abstractmethod
    def get_all(self) -> list[RevisionSolicitud]:
        pass

    @abstractmethod
    def get_by_id(self, revision_id: int) -> RevisionSolicitud:
        pass

    @abstractmethod
    def create(self, revision: RevisionSolicitud) -> RevisionSolicitud:
        pass

    @abstractmethod
    def update(self, revision: RevisionSolicitud) -> RevisionSolicitud:
        pass
    
    @abstractmethod
    def delete(self, revision_id: int) -> bool:
        pass