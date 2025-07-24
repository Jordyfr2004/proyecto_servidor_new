class RevisionSolicitud:
    def __init__(self,
        id: int= None,
        estado_revision:str =None,
        observacion: str= None,
        admin_id: int = None
        ):
        self.id = id
        self.estado_revision = estado_revision
        self.observacion = observacion
        self.admin_id = admin_id