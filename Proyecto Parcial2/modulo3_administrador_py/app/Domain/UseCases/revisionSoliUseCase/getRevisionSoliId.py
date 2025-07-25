from app.Domain.Interfaces.revisionsolicitudinterfaz.revisionsolcitudinterfaz import RevisionSolicitudInterface
from app.Domain.entities.revisionSolicitud.revisionSolicitudEntitie import RevisionSolicitud


class GetRevisionByIdUseCase:
    def __init__(self, revision_repository: RevisionSolicitudInterface):
        self.revision_repository = revision_repository

    def execute(self, revision_id: int) -> RevisionSolicitud:
        return self.revision_repository.get_by_id(revision_id)