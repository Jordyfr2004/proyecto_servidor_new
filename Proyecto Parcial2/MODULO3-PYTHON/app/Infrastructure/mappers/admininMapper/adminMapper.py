
from app.Infrastructure.Models.Administrador import administrador as AdminORM
from app.Infrastructure.mappers.agendaMapper.agendaMapper import AgendaMapper
from app.Infrastructure.mappers.revisionsoliMapper.revisionSolicitudMapper import RevisionSolicitudMapper
from app.Infrastructure.mappers.tipoeventoMapper.tipoEventoMapper import TipoEventoMapper
from app.Domain.entities.administrador.adminEntitie import Admin
from app.Domain.entities.administrador.adminEntitie import Admin as AdminEntitie

class AdminMapper:

    @staticmethod
    def to_domain(admin_orm: AdminORM) -> AdminEntitie:
        return AdminEntitie(
            id=admin_orm.id,
            nombre= admin_orm.nombre,
            usuario= admin_orm.usuario,
            correo= admin_orm.correo,
            password= admin_orm.password,
            agendas=[AgendaMapper.to_domain(a) for a in admin_orm.agendas],
            revisiones= [RevisionSolicitudMapper.to_domain(r) for r in admin_orm.revisiones],
            tipos_evento= [TipoEventoMapper.to_domain(t) for t in admin_orm.tipos_r]
        )
    
    @staticmethod
    def to_orm(admin_entitie: AdminEntitie) -> AdminORM:
        return AdminORM(
            nombre = admin_entitie.nombre,
            usuario = admin_entitie.usuario,
            correo = admin_entitie.correo,
            password = admin_entitie.password
        )
    
    @staticmethod
    def to_domain_list(admins_orm: list) -> list[AdminEntitie]:
        return [AdminMapper.to_domain(a) for a in admins_orm]