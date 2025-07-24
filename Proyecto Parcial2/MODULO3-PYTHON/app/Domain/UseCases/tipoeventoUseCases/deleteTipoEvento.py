from app.Domain.Interfaces.tipoeventointerfaz.tipoeventointerfaz import TipoEventoInterface

class DeleteTipoEventoUseCase:
    def __init__(self, tipo_repository: TipoEventoInterface):
        self.tipo_repository = tipo_repository

    def execute(self, tipo_id: int) -> bool:
        return self.tipo_repository.delete(tipo_id)