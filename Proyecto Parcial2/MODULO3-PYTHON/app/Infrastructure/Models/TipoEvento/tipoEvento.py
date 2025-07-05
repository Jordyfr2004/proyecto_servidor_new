from app.Infrastructure.Db.DataSource import db

class TipoEvento(db.Model):
    __tablename__='tipoEvento'

    id =db.Column(db.Integer, primary_key=True, autoincrement= True)
    tipo = db.Column(db.String(50))

    def __init__(self,tipo):
        self.tipo = tipo