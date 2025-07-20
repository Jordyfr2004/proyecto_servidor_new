from app.Domain.Dtos.createAgendaDto.updateAgendaDto import UpdateAgendaDTO
from app.Domain.Interfaces.agendainterfaz.agendainterfaz import AgendaInterface
from app.Domain.entities.agenda.agendaEntitie import Agenda

class UpdateAgendaUseCase:
    def __init__(self, agenda_repository: AgendaInterface):
        self.agenda_repository = agenda_repository


    def execute(self, dto: UpdateAgendaDTO) -> Agenda:
        agenda_actualizada = Agenda(
            id=dto.id,
            titulo=dto.titulo,
            descripcion=dto.descripcion,
            fecha=dto.fecha,
            admin_id=dto.admin_id
        )

        return self.agenda_repository.update(agenda_actualizada)