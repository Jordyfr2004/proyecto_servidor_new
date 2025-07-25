from app.Domain.Interfaces.revisionsolicitudinterfaz.revisionsolcitudinterfaz import RevisionSolicitudInterface

class DeleteRevisionUseCase:
    def __init__(self, revision_repository: RevisionSolicitudInterface):
        self.revision_repository = revision_repository

    def execute(self, revision_id: int) -> bool:
        return self.revision_repository.delete(revision_id)