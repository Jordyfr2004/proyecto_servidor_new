

class UpdateRevisionSolicitudDTO:
    def __init__(self, id: int, estado_revision: str, observacion: str, admin_id: int ):
        self.id = id
        self.estado_revision = estado_revision
        self.observacion = observacion
        self.admin_id = admin_id