from app.Domain.Interfaces.agendainterfaz.agendainterfaz import AgendaInterface
from app.Domain.entities.agenda.agendaEntitie import Agenda

class CreateAgendaUseCase:
    def __init__(self, agenda_repository: AgendaInterface ):
        self.agenda_repository = agenda_repository

    def execute(self, titulo: str, descripcion: str, fecha: str, id_admin: int) -> Agenda:
        nueva_agenda= Agenda(
            titulo=titulo,
            descripcion=descripcion,
            fecha=fecha,
            id_admin=id_admin
        )
        return self.agenda_repository.create(nueva_agenda)