

class UpdateEventoDTO:
    def __init__(self, id: int, descripcion: str, tipo_evento_id: int):
        self.id = id
        self.descripcion = descripcion
        self.tipo_evento_id = tipo_evento_id