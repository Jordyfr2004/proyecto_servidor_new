from app.Domain.Interfaces.agendainterfaz.agendainterfaz import AgendaInterface
from app.Domain.entities.agenda.agendaEntitie import Agenda

class UpdateAgendaUseCase:
    def __init__(self, agenda_repository: AgendaInterface):
        self.agenda_repository = agenda_repository


    def execute(self, agenda_id: int, titulo: str, descripcion: str, fecha: str, id_admin: int) -> Agenda:
        agenda_actualizada = Agenda(
            id=agenda_id,
            titulo=titulo,
            descripcion=descripcion,
            fecha=fecha,
            id_admin=id_admin
        )

        return self.agenda_repository.update(agenda_actualizada)