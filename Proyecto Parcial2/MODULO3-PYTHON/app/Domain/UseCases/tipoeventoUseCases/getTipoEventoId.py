from app.Domain.Interfaces.tipoeventointerfaz.tipoeventointerfaz import TipoEventoInterface
from app.Domain.entities.tipoEvento.tipoEventoEntitie import TipoEvento


class GetTipoEventoByIdUseCase:
    def __init__(self, tipo_repository: TipoEventoInterface):
        self.tipo_repository = tipo_repository

    def execute(self, tipo_id: int) -> TipoEvento:
        return self.tipo_repository.get_by_id(tipo_id)