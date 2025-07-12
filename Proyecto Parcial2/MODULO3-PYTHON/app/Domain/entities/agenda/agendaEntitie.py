class Agenda:
    def __init__(self,
        id: int= None,
        titulo: str= None, 
        descripcion: str= None, 
        fecha:str =None,
        admin_id: int =None
    ):
        self.id = id
        self.titulo = titulo
        self.descripcion = descripcion
        self.fecha = fecha
        self.admin_id = admin_id