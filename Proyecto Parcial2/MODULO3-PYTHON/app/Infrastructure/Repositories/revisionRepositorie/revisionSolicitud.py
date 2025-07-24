from app.Domain.Interfaces.revisionsolicitudinterfaz.revisionsolcitudinterfaz import RevisionSolicitudInterface
from app.Domain.entities.revisionSolicitud.revisionSolicitudEntitie import RevisionSolicitud
from app.Infrastructure.Models.RevisionSolicitud.revisionSolicitud import RevisionSolicitud as RevisionSolicitudORM
from app.Infrastructure.mappers.revisionsoliMapper.revisionSolicitudMapper import RevisionSolicitudMapper
from app.Infrastructure.Db.DataSource import db

class RevisionSolicitudRepository(RevisionSolicitudInterface):

    def get_all(self) -> list[RevisionSolicitud]:
        revisiones_orm = RevisionSolicitudORM.query.all()
        return RevisionSolicitudMapper.to_domain_list(revisiones_orm)

    def get_by_id(self, revision_id: int) -> RevisionSolicitud:
        revision_orm = RevisionSolicitudORM.query.get(revision_id)
        return RevisionSolicitudMapper.to_domain(revision_orm) if revision_orm else None

    def create(self, revision: RevisionSolicitud) -> RevisionSolicitud:
        revision_orm = RevisionSolicitudMapper.to_orm(revision)
        db.session.add(revision_orm)
        db.session.commit()
        return RevisionSolicitudMapper.to_domain(revision_orm)

    def update(self, revision: RevisionSolicitud) -> RevisionSolicitud:
        revision_orm = RevisionSolicitudORM.query.get(revision.id)
        if revision_orm:
            revision_orm.estado_revision = revision.estado_revision
            revision_orm.observacion = revision.observacion
            revision_orm.id_admin = revision.admin_id
            db.session.commit()
        return RevisionSolicitudMapper.to_domain(revision_orm)

    def delete(self, revision_id: int) -> bool:
        revision_orm = RevisionSolicitudORM.query.get(revision_id)
        if revision_orm:
            db.session.delete(revision_orm)
            db.session.commit()
            return True
        return False
