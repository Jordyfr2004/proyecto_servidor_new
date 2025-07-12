from app.Domain.entities.revisionSolicitud.revisionSolicitudEntitie import RevisionSolicitud
from app.Domain.Interfaces.revisionsolicitudinterfaz.revisionsolcitudinterfaz import RevisionSolicitudInterface

class UpdateRevisionUseCase:
    def __init__(self, revision_repository: RevisionSolicitudInterface):
        self.revision_repository = revision_repository

    def execute(self, revision_id: int, estado_revision: str, observacion: str, id_admin: int) -> RevisionSolicitud:
        actualizado = RevisionSolicitud(id=revision_id, estado_revision=estado_revision, observacion=observacion, id_admin=id_admin)
        return self.revision_repository.update(actualizado)