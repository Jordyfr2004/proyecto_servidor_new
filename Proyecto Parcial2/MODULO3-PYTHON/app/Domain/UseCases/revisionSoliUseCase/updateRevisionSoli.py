from app.Domain.Dtos.createRevisionsoliDto.updateRevisionSolicitudDto import UpdateRevisionSolicitudDTO
from app.Domain.entities.revisionSolicitud.revisionSolicitudEntitie import RevisionSolicitud
from app.Domain.Interfaces.revisionsolicitudinterfaz.revisionsolcitudinterfaz import RevisionSolicitudInterface

class UpdateRevisionUseCase:
    def __init__(self, revision_repository: RevisionSolicitudInterface):
        self.revision_repository = revision_repository

    def execute(self, dto: UpdateRevisionSolicitudDTO) -> RevisionSolicitud:
        actualizado = RevisionSolicitud(
            id=dto.id, 
            estado_revision=dto.estado_revision, 
            observacion=dto.observacion, 
            admin_id=dto.admin_id)
        return self.revision_repository.update(actualizado)