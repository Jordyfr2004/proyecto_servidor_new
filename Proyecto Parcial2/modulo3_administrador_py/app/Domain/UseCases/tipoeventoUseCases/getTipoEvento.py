from app.Domain.Interfaces.tipoeventointerfaz.tipoeventointerfaz import TipoEventoInterface


class GetTipoEventoUseCase:
    def __init__(self, tipo_repository: TipoEventoInterface):
        self.tipo_repository = tipo_repository

    def execute(self):
        return self.tipo_repository.get_all()