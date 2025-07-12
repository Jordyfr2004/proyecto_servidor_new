from app.Infrastructure.Db.DataSource import db

class Evento(db.Model):
    __tablename__ = 'evento'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    id_tipo_evento = db.Column(db.Integer, db.ForeignKey('tipoEvento.id'), nullable=False)  # referencia a tipoEvento
    descripcion = db.Column(db.String(50), nullable=False)

    def __init__(self, descripcion):
        self.descripcion = descripcion
