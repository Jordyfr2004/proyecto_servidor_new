class LoginAdminDTO:
    """
    DTO para manejar las credenciales del administrador en el proceso de login.
    """

    def __init__(self, correo: str, password: str):
        self.correo = correo
        self.password = password
