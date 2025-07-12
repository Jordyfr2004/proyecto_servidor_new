from app.Domain.entities.tipoEvento.tipoEventoEntitie import TipoEvento
from app.Domain.Interfaces.tipoeventointerfaz.tipoeventointerfaz import TipoEventoInterface


class UpdateTipoEventoUseCase:
    def __init__(self, tipo_repository: TipoEventoInterface):
        self.tipo_repository = tipo_repository

    def execute(self, tipo_id: int, tipo: str, id_admin: int) -> TipoEvento:
        actualizado = TipoEvento(id=tipo_id, tipo=tipo, id_admin=id_admin)
        return self.tipo_repository.update(actualizado)