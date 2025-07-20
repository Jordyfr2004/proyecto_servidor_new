from app.Domain.Interfaces.eventointerfaz.eventointerfaz import EventoInterface
from app.Domain.entities.evento.eventoEntitie import Evento
from app.Infrastructure.Models.Evento.evento import Evento as EventoORM
from app.Infrastructure.mappers.eventoMapper.eventoMapper import EventoMapper
from app.Infrastructure.Db.DataSource import db

class EventoRepository(EventoInterface):
    def get_all(self) -> list[Evento]:
        eventos_orm = EventoORM.query.all()
        return EventoMapper.to_domain_list(eventos_orm)

    def get_by_id(self, evento_id: int) -> Evento:
        evento_orm = EventoORM.query.get(evento_id)
        return EventoMapper.to_domain(evento_orm) if evento_orm else None

    def create(self, evento: Evento) -> Evento:
        evento_orm = EventoMapper.to_orm(evento)
        db.session.add(evento_orm)
        db.session.commit()
        return EventoMapper.to_domain(evento_orm)

    def update(self, evento: Evento) -> Evento:
        evento_orm = EventoORM.query.get(evento.id)
        if evento_orm:
            evento_orm.descripcion = evento.descripcion
            evento_orm.id_tipo_evento = evento.tipo_evento_id
            db.session.commit()
        return EventoMapper.to_domain(evento_orm)

    def delete(self, evento_id: int) -> bool:
        evento_orm = EventoORM.query.get(evento_id)
        if evento_orm:
            db.session.delete(evento_orm)
            db.session.commit()
            return True
        return False
