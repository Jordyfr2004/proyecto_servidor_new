from app.Infrastructure.Db.DataSource import db

class Evento(db.Model):
    __tablename__='evento'

    id =db.Column(db.Integer, primary_key=True, autoincrement= True)
    descripcion = db.Column(db.String(50))

    def __init__(self,descripcion):
        self.descripcion = descripcion