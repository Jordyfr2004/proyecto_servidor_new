from app.Infrastructure.Db.DataSource import db
class Agenda(db.Model):
    __tablename__='agenda'

    id =db.Column(db.Integer, primary_key=True, autoincrement= True)
    titulo = db.Column(db.String(50))
    descripcion = db.Column(db.String(50))
    fecha = db.Column(db.String(50))

    def __init__(self,titulo,descripcion,fecha):
        self.titulo = titulo
        self.descripcion = descripcion
        self.fecha = fecha