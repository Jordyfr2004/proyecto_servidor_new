from flask import request, jsonify
from utils.dataSource import db
from models.admin import Admin
from models.entrega import Entrega
from datetime import datetime

def crear_entrega(admin_id):
    data = request.get_json()

    id_solicitud = data.get("id_solicitud")  # puede ser None si no está
    fecha_entrega_str = data.get("fecha_entrega")
    observaciones_entrega = data.get("observaciones_entrega")

    try:
        admin = Admin.query.get(admin_id)
        if not admin:
            return jsonify({"error": "Admin no encontrado"}), 404

        # Convertir fecha_entrega de string a datetime si viene
        if fecha_entrega_str:
            try:
                fecha_entrega = datetime.fromisoformat(fecha_entrega_str)
            except ValueError:
                return jsonify({"error": "Formato inválido para 'fecha_entrega', debe ser ISO 8601"}), 400
        else:
            fecha_entrega = datetime.now()

        nueva_entrega = Entrega(
            id_admin=admin_id,
            id_solicitud=id_solicitud,
            fecha_entrega=fecha_entrega,
            observaciones_entrega=observaciones_entrega
        )

        db.session.add(nueva_entrega)
        db.session.commit()

        return jsonify({
            "message": "Entrega creada exitosamente",
            "entrega": {
                "id": nueva_entrega.id,
                "id_admin": nueva_entrega.id_admin,
                "id_solicitud": nueva_entrega.id_solicitud,
                "fecha_entrega": nueva_entrega.fecha_entrega.isoformat(),
                "observaciones_entrega": nueva_entrega.observaciones_entrega
            }
        }), 201

    except Exception as e:
        db.session.rollback()
        return jsonify({"error": str(e)}), 500

