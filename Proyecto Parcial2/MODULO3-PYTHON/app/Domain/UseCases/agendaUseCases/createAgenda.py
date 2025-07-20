from app.Domain.Dtos.createAgendaDto.createAgendaDto import CreateAgendaDTO
from app.Domain.Interfaces.agendainterfaz.agendainterfaz import AgendaInterface
from app.Domain.entities.agenda.agendaEntitie import Agenda

class CreateAgendaUseCase:
    def __init__(self, agenda_repository: AgendaInterface ):
        self.agenda_repository = agenda_repository

    def execute(self, dto: CreateAgendaDTO) -> Agenda:
        nueva_agenda= Agenda(
            titulo=dto.titulo,
            descripcion=dto.descripcion,
            fecha=dto.fecha,
            admin_id =dto.admin_id
        )
        return self.agenda_repository.create(nueva_agenda)