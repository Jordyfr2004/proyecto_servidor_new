

class CreateRevisionSolicitudDTO:
    def __init__(self, estado_revision: str, observacion: str, admin_id: int):
        self.estado_revision= estado_revision
        self.observacion = observacion
        self.admin_id= admin_id