from utils.dataSource import db
from datetime import datetime

class Entrega(db.Model):
    __tablename__ = 'entrega'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    id_admin = db.Column(db.Integer, db.ForeignKey('admin.id'), nullable=False)
    id_solicitud = db.Column(db.Integer, nullable=False)
    fecha_entrega = db.Column(db.DateTime, default=datetime.now)
    observaciones_entrega = db.Column(db.Text)

    def __init__(self, id_admin, id_solicitud, fecha_entrega, observaciones_entrega):
        self.id_admin = id_admin
        self.id_solicitud = id_solicitud
        self.fecha_entrega = fecha_entrega
        self.observaciones_entrega = observaciones_entrega
