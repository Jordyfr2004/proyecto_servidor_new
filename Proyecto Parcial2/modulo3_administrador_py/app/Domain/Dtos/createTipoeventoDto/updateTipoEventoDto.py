
class UpdateTipoEventoDTO:
    def __init__(self, id: int, tipo: str, admin_id: int):
        self.id = id
        self.tipo = tipo
        self.admin_id = admin_id
