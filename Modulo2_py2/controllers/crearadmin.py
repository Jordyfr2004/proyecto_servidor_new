from flask import request, jsonify
from utils.dataSource import db
from models.admin import Admin  # Ajusta la ruta si está en otra carpeta

def crear_admin():
    data = request.get_json()

    try:
        nuevo_admin = Admin(
            nombre=data.get("nombre"),
            apellido=data.get("apellido"),
            telefono=data.get("telefono"),
            correo=data.get("correo"),
            password=data.get("password")
        )

        db.session.add(nuevo_admin)
        db.session.commit()

        return jsonify({
            "message": "Admin creado exitosamente",
            "admin": {
                "id": nuevo_admin.id,
                "nombre": nuevo_admin.nombre,
                "apellido": nuevo_admin.apellido,
                "telefono": nuevo_admin.telefono,
                "correo": nuevo_admin.correo
                # no se devuelve la contraseña por seguridad
            }
        }), 201

    except Exception as e:
        db.session.rollback()
        return jsonify({"error": str(e)}), 500
