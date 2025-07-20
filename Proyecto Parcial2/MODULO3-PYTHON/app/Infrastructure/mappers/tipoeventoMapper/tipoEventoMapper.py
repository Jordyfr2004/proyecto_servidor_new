from app.Domain.entities.tipoEvento.tipoEventoEntitie import TipoEvento as TipoEventoEntitie
from app.Infrastructure.Models.TipoEvento.tipoEvento import TipoEvento as TipoEventoORM
from app.Infrastructure.mappers.eventoMapper.eventoMapper import EventoMapper
from app.Infrastructure.Models.TipoEvento.tipoEvento import TipoEvento

class TipoEventoMapper:

    @staticmethod
    def to_domain(tipo_orm: TipoEventoORM) -> TipoEventoEntitie:
        return TipoEventoEntitie(
            id=tipo_orm.id,
            tipo=tipo_orm.tipo,
            admin_id=tipo_orm.id_admin,
            eventos=[EventoMapper.to_domain(e) for e in tipo_orm.tiposs]
        )
    
    @staticmethod
    def to_orm(tipo_entitie: TipoEventoEntitie) -> TipoEventoORM:
        return TipoEventoORM(
            tipo=tipo_entitie.tipo,
            id_admin=tipo_entitie.admin_id
        )
    
    @staticmethod
    def to_domain_list(tipos_orm: list) -> list[TipoEvento]:
        return [TipoEventoMapper.to_domain(t) for t in tipos_orm]