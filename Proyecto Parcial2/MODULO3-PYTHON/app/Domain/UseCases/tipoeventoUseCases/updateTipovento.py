from app.Domain.Dtos.createTipoeventoDto.updateTipoEventoDto import UpdateTipoEventoDTO
from app.Domain.entities.tipoEvento.tipoEventoEntitie import TipoEvento
from app.Domain.Interfaces.tipoeventointerfaz.tipoeventointerfaz import TipoEventoInterface


class UpdateTipoEventoUseCase:
    def __init__(self, tipo_repository: TipoEventoInterface):
        self.tipo_repository = tipo_repository

    def execute(self, dto: UpdateTipoEventoDTO) -> TipoEvento:
        actualizado = TipoEvento(
            id=dto.id, 
            tipo=dto.tipo, 
            admin_id=dto.admin_id
        )
        return self.tipo_repository.update(actualizado)