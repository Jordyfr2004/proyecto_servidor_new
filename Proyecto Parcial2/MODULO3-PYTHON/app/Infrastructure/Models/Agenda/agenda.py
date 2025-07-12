from app.Infrastructure.Db.DataSource import db

class Agenda(db.Model):
    __tablename__ = 'agenda'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    id_admin = db.Column(db.Integer, db.ForeignKey('admin.id'), nullable=False)
    titulo = db.Column(db.String(50), nullable=False)
    descripcion = db.Column(db.String(50), nullable=True)
    fecha = db.Column(db.Date, nullable=False)

    def __init__(self, titulo, descripcion, fecha):
        self.titulo = titulo
        self.descripcion = descripcion
        self.fecha = fecha
