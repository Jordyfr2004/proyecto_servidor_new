from app.Domain.entities.revisionSolicitud.revisionSolicitudEntitie import RevisionSolicitud
from app.Domain.Interfaces.revisionsolicitudinterfaz.revisionsolcitudinterfaz import RevisionSolicitudInterface

class CreateRevisionUseCase:
    def __init__(self, revision_repository: RevisionSolicitudInterface):
        self.revision_repository = revision_repository

    def execute(self, estado_revision: str, observacion: str, id_admin: int) -> RevisionSolicitud:
        nueva = RevisionSolicitud(estado_revision=estado_revision, observacion=observacion, id_admin=id_admin)
        return self.revision_repository.create(nueva)
