from app.Infrastructure.Db.DataSource import db

class Admin(db.Model):
    __tablename__='admin'

    id =db.Column(db.Integer, primary_key=True, autoincrement= True)
    nombre = db.Column(db.String(50))
    usuario = db.Column(db.String(50))
    correo = db.Column(db.String(50))
    password = db.Column(db.String(10))

    def __init__(self,nombre,usuario,correo,password):
        self.nombre = nombre
        self.usuario = usuario
        self.correo = correo
        self.password = password