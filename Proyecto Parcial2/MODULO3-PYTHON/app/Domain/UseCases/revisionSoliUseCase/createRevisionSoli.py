from app.Domain.Dtos.createRevisionsoliDto.createRevisionSolicitudDto import CreateRevisionSolicitudDTO
from app.Domain.entities.revisionSolicitud.revisionSolicitudEntitie import RevisionSolicitud
from app.Domain.Interfaces.revisionsolicitudinterfaz.revisionsolcitudinterfaz import RevisionSolicitudInterface

class CreateRevisionUseCase:
    def __init__(self, revision_repository: RevisionSolicitudInterface):
        self.revision_repository = revision_repository

    def execute(self, dto: CreateRevisionSolicitudDTO) -> RevisionSolicitud:
        nueva_revision = RevisionSolicitud(
            estado_revision=dto.estado_revision, 
            observacion=dto.observacion, 
            admin_id=dto.admin_id)
        return self.revision_repository.create(nueva_revision)
