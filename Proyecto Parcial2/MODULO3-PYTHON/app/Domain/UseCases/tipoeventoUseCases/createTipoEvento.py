from app.Domain.Dtos.createTipoeventoDto.createTipoEventoDto import CreateTipoEventoDTO
from app.Domain.entities.tipoEvento.tipoEventoEntitie import TipoEvento
from app.Domain.Interfaces.tipoeventointerfaz.tipoeventointerfaz import TipoEventoInterface

class CreateTipoEventoUseCase:
    def __init__(self, tipo_repository: TipoEventoInterface):
        self.tipo_repository = tipo_repository

    def execute(self, dto: CreateTipoEventoDTO) -> TipoEvento:
        nuevo_tipo = TipoEvento(
            tipo=dto.tipo, 
            admin_id=dto.admin_id)
        return self.tipo_repository.create(nuevo_tipo)