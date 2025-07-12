from app.Domain.Interfaces.agendainterfaz.agendainterfaz import AgendaInterface
from app.Domain.entities.agenda.agendaEntitie import Agenda
from app.Infrastructure.Models.Agenda.agenda import Agenda as AgendaORM
from app.Infrastructure.mappers.agendaMapper.agendaMapper import AgendaMapper
from app.Infrastructure.Db.DataSource import db

class AgendaRepository(AgendaInterface):
    def get_all(self) -> list[Agenda]:
        agendas_orm = AgendaORM.query.all()
        return AgendaMapper.to_domain_list(agendas_orm)

    def get_by_id(self, agenda_id: int) -> Agenda:
        agenda_orm = AgendaORM.query.get(agenda_id)
        return AgendaMapper.to_domain(agenda_orm) if agenda_orm else None

    def create(self, agenda: Agenda) -> Agenda:
        agenda_orm = AgendaMapper.to_orm(agenda)
        db.session.add(agenda_orm)
        db.session.commit()
        return AgendaMapper.to_domain(agenda_orm)

    def update(self, agenda: Agenda) -> Agenda:
        agenda_orm = AgendaORM.query.get(agenda.id)
        if agenda_orm:
            agenda_orm.titulo = agenda.titulo
            agenda_orm.descripcion = agenda.descripcion
            agenda_orm.fecha = agenda.fecha
            agenda_orm.id_admin = agenda.id_admin
            db.session.commit()
        return AgendaMapper.to_domain(agenda_orm)

    def delete(self, agenda_id: int) -> bool:
        agenda_orm = AgendaORM.query.get(agenda_id)
        if agenda_orm:
            db.session.delete(agenda_orm)
            db.session.commit()
            return True
        return False
