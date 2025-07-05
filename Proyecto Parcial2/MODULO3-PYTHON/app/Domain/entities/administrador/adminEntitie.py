# domain/entities/admin.py

class Admin:
    def __init__(self, id: int = None, nombre: str = None, usuario: str = None, correo: str = None, password: str = None):
        self.id = id
        self.nombre = nombre
        self.usuario = usuario
        self.correo = correo
        self.password = password

