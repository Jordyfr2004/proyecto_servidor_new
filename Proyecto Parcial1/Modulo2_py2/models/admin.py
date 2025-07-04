from utils.dataSource import db

class Admin(db.Model):
    __tablename__ = 'admin'

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    nombre = db.Column(db.String(50))
    apellido = db.Column(db.String(50))
    telefono = db.Column(db.String(15))
    correo = db.Column(db.String(80))
    password = db.Column(db.String(50))

    entregas = db.relationship('Entrega', backref='admin', lazy=True)
    revisiones = db.relationship('RevisionSolicitud', backref='admin', lazy=True)

    def __init__(self, nombre, apellido, telefono, correo, password):
        self.nombre = nombre
        self.apellido = apellido
        self.telefono = telefono
        self.correo = correo
        self.password = password
