class Evento:
    def __init__(self,
        id: int= None,
        descripcion: str= None,
        tipo_evento_id: int = None
    ):
        self.id = id
        self.descripcion = descripcion
        self.tipo_evento_id = tipo_evento_id