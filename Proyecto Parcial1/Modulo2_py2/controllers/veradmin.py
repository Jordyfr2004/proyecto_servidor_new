from flask import jsonify
from collections import OrderedDict
from models.admin import Admin

def obtener_admin_con_revisiones(admin_id):
    admin = Admin.query.get(admin_id)
    if not admin:
        return jsonify({"error": "Administrador no encontrado"}), 404

    revisiones = []
    for rev in admin.revisiones:
        revisiones.append(OrderedDict([
            ("id", rev.id),
            ("id_solicitud", rev.id_solicitud),
            ("fecha", rev.fecha.strftime("%Y-%m-%d %H:%M:%S")),
            ("estado_revision", rev.estado_revision),
            ("observacion_revision", rev.observacion_revision),
        ]))

    admin_data = OrderedDict([
        ("id", admin.id),
        ("nombre", admin.nombre),
        ("apellido", admin.apellido),
        ("correo", admin.correo),
        ("telefono", admin.telefono),
        ("revisiones", revisiones),  # Aquí justo después de teléfono
    ])

    return jsonify({"admin": admin_data}), 200

