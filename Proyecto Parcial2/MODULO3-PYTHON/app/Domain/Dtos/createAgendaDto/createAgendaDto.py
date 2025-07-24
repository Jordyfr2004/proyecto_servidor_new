
class CreateAgendaDTO:
    def __init__(self, titulo: str, descripcion: str, fecha: str, admin_id:int):
        self.titulo= titulo
        self.descripcion= descripcion
        self.fecha = fecha
        self.admin_id= admin_id