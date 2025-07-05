from app.Infrastructure.Db.DataSource import db

class RevisionSolicitud(db.Model):
    __tablename__='revisionSolicitud'

    id =db.Column(db.Integer, primary_key=True, autoincrement= True)
    estado_revision = db.Column(db.String(50))
    observacion = db.Column(db.String(50))

    def __init__(self,estado_revision,observacion):
        self.estado_revision = estado_revision
        self.observacion = observacion