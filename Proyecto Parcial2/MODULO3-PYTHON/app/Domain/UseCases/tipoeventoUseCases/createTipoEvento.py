from app.Domain.entities.tipoEvento.tipoEventoEntitie import TipoEvento
from app.Domain.Interfaces.tipoeventointerfaz.tipoeventointerfaz import TipoEventoInterface

class CreateTipoEventoUseCase:
    def __init__(self, tipo_repository: TipoEventoInterface):
        self.tipo_repository = tipo_repository

    def execute(self, tipo: str, id_admin: int) -> TipoEvento:
        nuevo_tipo = TipoEvento(tipo=tipo, id_admin=id_admin)
        return self.tipo_repository.create(nuevo_tipo)