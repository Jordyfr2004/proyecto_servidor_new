from flask import request, jsonify
from app.Domain.Dtos.createAdminDto.createAdminDto import CreateAdminDTO
from app.Domain.Dtos.createAdminDto.updateAdminDto import UpdateAdminDTO
from app.Domain.UseCases.adminUseCases.createAdmin import CreateAdminUseCase
from app.Domain.UseCases.adminUseCases.getAdmin import GetAdminUseCase
from app.Domain.UseCases.adminUseCases.getAdminId import GetAdminByIdUseCase
from app.Domain.UseCases.adminUseCases.updateAdmin import UpdateAdminUseCase
from app.Domain.UseCases.adminUseCases.deleteAdmin import DeleteAdminUseCase
from app.Infrastructure.Repositories.adminRepositorie.adminRepositorie import AdminRepository


class AdminController:
    def __init__(self):
        self.repository = AdminRepository()

    def create_admin(self):
        data = request.get_json()
        dto = CreateAdminDTO(
            nombre=data.get("nombre"),
            usuario=data.get("usuario"),
            correo=data.get("correo"),
            password=data.get("password")
        )

        use_case = CreateAdminUseCase(self.repository)
        admin = use_case.execute(dto)

        return jsonify({
            "id": admin.id,
            "nombre": admin.nombre,
            "usuario": admin.usuario,
            "correo": admin.correo
        }),201

    def get_all_admins(self):
        use_case = GetAdminUseCase(self.repository)
        admins = use_case.execute()

        return jsonify([
            {
                "id": a.id,
                "nombre": a.nombre,
                "usuario": a.usuario,
                "correo": a.correo
            } for a in admins
        ])

    def get_admin_by_id(self, admin_id):
        use_case = GetAdminByIdUseCase(self.repository)
        admin = use_case.execute(admin_id)

        if not admin:
            return jsonify({"message": "Administrador no encontrado"}), 404

        return jsonify({
            "id": admin.id,
            "nombre": admin.nombre,
            "usuario": admin.usuario,
            "correo": admin.correo
        })

    def update_admin(self, admin_id):
        data = request.get_json()
        dto = UpdateAdminDTO(
            id=admin_id,
            nombre=data.get("nombre"),
            usuario=data.get("usuario"),
            correo=data.get("correo"),
            password=data.get("password")
        )

        use_case = UpdateAdminUseCase(self.repository)
        updated_admin = use_case.execute(dto)

        if not updated_admin:
            return jsonify({"message": "No se pudo actualizar el administrador"}), 404

        return jsonify({
            "id": updated_admin.id,
            "nombre": updated_admin.nombre,
            "usuario": updated_admin.usuario,
            "correo": updated_admin.correo
        })

    def delete_admin(self, admin_id):
        use_case = DeleteAdminUseCase(self.repository)
        result = use_case.execute(admin_id)

        if result:
            return jsonify({"message": "Administrador eliminado correctamente"})
        else:
            return jsonify({"message": "Administrador no encontrado"}), 404
