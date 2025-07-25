

class CreateEventoDTO:
    def __init__(self, descripcion: str, tipo_evento_id: int):
        self.descripcion= descripcion
        self.tipo_evento_id = tipo_evento_id