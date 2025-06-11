from flask import request, jsonify
from utils.dataSource import db
from models.revision_solicitud import RevisionSolicitud  # Ajusta si está en otro archivo
from models.admin import Admin  # Solo si necesitas verificar que el admin existe

def crear_revision(id_admin):
    data = request.get_json()

    try:
        # Validar que el admin exista (opcional pero recomendable)
        admin = Admin.query.get(id_admin)
        if not admin:
            return jsonify({"error": "Administrador no encontrado"}), 404

        nueva_revision = RevisionSolicitud(
            id_solicitud=data.get("id_solicitud"),
            id_admin=id_admin,  # lo tomamos de la URL o contexto
            estado_revision=data.get("estado_revision"),
            observacion_revision=data.get("observacion_revision")
        )

        db.session.add(nueva_revision)
        db.session.commit()

        return jsonify({
            "message": "Revisión creada exitosamente",
            "revision": {
                "id": nueva_revision.id,
                "id_solicitud": nueva_revision.id_solicitud,
                "id_admin": nueva_revision.id_admin,
                "fecha": nueva_revision.fecha.strftime("%Y-%m-%d %H:%M:%S"),
                "estado_revision": nueva_revision.estado_revision,
                "observacion_revision": nueva_revision.observacion_revision
            }
        }), 201

    except Exception as e:
        db.session.rollback()
        return jsonify({"error": str(e)}), 500
