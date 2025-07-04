from utils.dataSource import db
from datetime import datetime

class RevisionSolicitud(db.Model):
    __tablename__ = 'revisionsolicitud'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    id_solicitud = db.Column(db.Integer, nullable=False)
    id_admin = db.Column(db.Integer, db.ForeignKey('admin.id'), nullable=False)
    fecha = db.Column(db.DateTime, default=datetime.now)
    estado_revision = db.Column(db.String(50))
    observacion_revision = db.Column(db.Text)

    def __init__(self, id_solicitud, id_admin, estado_revision, observacion_revision, fecha=None):
        self.id_solicitud = id_solicitud
        self.id_admin = id_admin
        self.estado_revision = estado_revision
        self.observacion_revision = observacion_revision
        self.fecha = fecha if fecha else datetime.now()
