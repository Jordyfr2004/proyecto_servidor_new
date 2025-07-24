from app.Domain.entities.revisionSolicitud.revisionSolicitudEntitie import RevisionSolicitud as RevisionEntitie
from app.Infrastructure.Models.RevisionSolicitud.revisionSolicitud import RevisionSolicitud as RevisionORM
from app.Infrastructure.Models.RevisionSolicitud.revisionSolicitud import RevisionSolicitud


class RevisionSolicitudMapper:

    @staticmethod
    def to_domain(revision_orm: RevisionORM) -> RevisionEntitie:
        return RevisionEntitie(
            id=revision_orm.id,
            estado_revision=revision_orm.estado_revision,
            observacion=revision_orm.observacion,
            admin_id=revision_orm.id_admin
        )
    
    @staticmethod
    def to_orm(revision_entitie: RevisionEntitie) -> RevisionORM:
        return RevisionORM(
            estado_revision=revision_entitie.estado_revision,
            observacion=revision_entitie.observacion,
            id_admin=revision_entitie.admin_id
        )
    
    @staticmethod
    def to_domain_list(revisiones_orm: list) -> list[RevisionSolicitud]:
        return [RevisionSolicitudMapper.to_domain(r) for r in revisiones_orm]