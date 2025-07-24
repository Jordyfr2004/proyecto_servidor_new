from app.Domain.entities.agenda.agendaEntitie import Agenda as AgendaEntitie
from app.Infrastructure.Models.Agenda.agenda import Agenda as AgendaOrm
from app.Domain.entities.agenda.agendaEntitie import Agenda
from datetime import datetime
class AgendaMapper:

    @staticmethod
    def to_domain(agenda_orm: AgendaOrm) -> AgendaEntitie:
        return AgendaEntitie(
            id= agenda_orm.id,
            titulo= agenda_orm.titulo,
            descripcion= agenda_orm.descripcion,
            fecha=  str(agenda_orm.fecha),
            admin_id= agenda_orm.id_admin
        )
    
    @staticmethod
    def to_orm(agenda_entitie: AgendaEntitie) -> AgendaOrm:
        return AgendaOrm(
            titulo= agenda_entitie.titulo,
            descripcion= agenda_entitie.descripcion,
            fecha=datetime.strptime(agenda_entitie.fecha, "%Y-%m-%d").date(),
            id_admin= agenda_entitie.admin_id
        )
    
    @staticmethod
    def to_domain_list(agendas_orm: list) -> list[Agenda]:
        return [AgendaMapper.to_domain(a) for a in agendas_orm]