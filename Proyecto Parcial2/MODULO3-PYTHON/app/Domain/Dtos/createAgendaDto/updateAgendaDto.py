
class UpdateAgendaDTO:
    def __init__(self, id: int, titulo: str, descripcion: str, fecha: str, admin_id: int):
        self.id = id
        self.titulo = titulo
        self.descripcion = descripcion
        self.fecha = fecha
        self.admin_id = admin_id
