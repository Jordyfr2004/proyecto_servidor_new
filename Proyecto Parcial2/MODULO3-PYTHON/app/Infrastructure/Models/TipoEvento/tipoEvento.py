from app.Infrastructure.Db.DataSource import db

class TipoEvento(db.Model):
    __tablename__ = 'tipoEvento'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    id_admin = db.Column(db.Integer, db.ForeignKey('admin.id'), nullable=False)
    tipo = db.Column(db.String(50), nullable=False)

    tiposs = db.relationship('Evento', backref='tipoEvento', lazy=True)

    def __init__(self, tipo):
        self.tipo = tipo
