from app.Domain.entities.evento import eventoEntitie as EventoEntitie
from app.Infrastructure.Models.Evento import evento as EventoORM
from app.Infrastructure.Models.Evento.evento import Evento

class EventoMapper:

    @staticmethod
    def to_domain(evento_orm: EventoORM) -> EventoEntitie:
        return EventoEntitie(
            id=evento_orm.id,
            descripcion=evento_orm.descripcion,
            tipo_evento_id=evento_orm.id_evento
        )
    
    @staticmethod
    def to_orm(evento_entitie: EventoEntitie) -> EventoORM:
        return EventoORM(
            descripcion=evento_entitie.descripcion,
            id_evento=evento_entitie.tipo_evento_id
        )
    
    @staticmethod
    def to_domain_list(eventos_orm: list) -> list[Evento]:
        return [EventoMapper.to_domain(e) for e in eventos_orm]