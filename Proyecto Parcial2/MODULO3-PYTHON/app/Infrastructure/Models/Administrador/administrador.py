from app.Infrastructure.Db.DataSource import db

class Admin(db.Model):
    __tablename__='admin'

    id =db.Column(db.Integer, primary_key=True, autoincrement= True)
    nombre = db.Column(db.String(50), nullable=False)
    usuario = db.Column(db.String(50), nullable=False)
    correo = db.Column(db.String(50), nullable=False)
    password = db.Column(db.String(10), nullable=False)

    agendas = db.relationship('Agenda', backref='admin', lazy= True)
    revisiones = db.relationship('RevisionSolicitud', backref='admin', lazy=True)
    tipos_r = db.relationship('tipoEvento', backref='admin', lazy= True)

    def __init__(self,nombre,usuario,correo,password):
        self.nombre = nombre
        self.usuario = usuario
        self.correo = correo
        self.password = password