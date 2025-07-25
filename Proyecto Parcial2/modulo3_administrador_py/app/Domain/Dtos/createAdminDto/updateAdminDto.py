

class UpdateAdminDTO:
    def __init__(self, id, nombre= None, usuario= None, correo=None, password=None):
        self.id = id
        self.nombre = nombre
        self.usuario = usuario
        self.correo = correo
        self.password = password