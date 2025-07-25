import bcrypt
from app.Domain.Dtos.loginAdminDto.loginAdminDto import LoginAdminDTO
from app.Domain.Interfaces.adminInterfaz.adminterfaz import AdminInterface
from app.Domain.entities.administrador.adminEntitie import Admin
from app.Infrastructure.Repositories.adminRepositorie.adminRepositorie import AdminRepository
from app.utils.security import generate_jwt_token

class LoginAdminUseCase:
    """
    Caso de uso para iniciar sesión de un administrador.
    """

    def __init__(self, admin_repository: AdminRepository):
        self.admin_repository = admin_repository

    def execute(self, dto: LoginAdminDTO) -> Admin:
        """
        Valida credenciales de un administrador.
        :param dto: DTO con email y contraseña.
        :return: Admin si la autenticación es exitosa.
        :raises ValueError: Si el correo no existe o la contraseña es incorrecta.
        """
        # Buscar al administrador por email
        admin = self.admin_repository.get_by_email(dto.correo)
        if not admin:
            raise ValueError("Correo no registrado")

        # Validar contraseña con bcrypt
        if not bcrypt.checkpw(dto.password.encode('utf-8'), admin.password.encode('utf-8')):
            raise ValueError("Contraseña incorrecta")
        
        token = generate_jwt_token(admin.id, admin.correo)

        return{
            "admin": admin,
            "token": token
        } 