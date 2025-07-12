from app.Infrastructure.Db.DataSource import db

class RevisionSolicitud(db.Model):
    __tablename__ = 'revisionSolicitud'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    id_admin = db.Column(db.Integer, db.ForeignKey('admin.id'), nullable=False)
    estado_revision = db.Column(db.String(50), nullable=False)
    observacion = db.Column(db.String(50), nullable=False)

    def __init__(self, estado_revision, observacion):
        self.estado_revision = estado_revision
        self.observacion = observacion
