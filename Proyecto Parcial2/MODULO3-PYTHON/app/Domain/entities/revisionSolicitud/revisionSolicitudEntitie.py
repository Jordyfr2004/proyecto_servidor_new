class RevisionSolicitud:
    def __init__(self,id: int= None,estado_revision:str =None,observacion: str= None):
        self.id = id
        self.estado_revision = estado_revision
        self.observacion = observacion