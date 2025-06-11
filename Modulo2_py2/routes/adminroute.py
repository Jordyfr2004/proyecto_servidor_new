from flask import Blueprint
from controllers.crearadmin import crear_admin
from controllers.crearentrega import crear_entrega
from controllers.crearrevision import crear_revision  # Ajusta el nombre del archivo si es distinto
from controllers.veradmin import obtener_admin_con_revisiones


admin = Blueprint('admin',__name__)


@admin.route('/')
def home():
    return 'hola mundo'


@admin.route('/crearAdmin', methods=["POST"])
def crear_admin_handler():
    return crear_admin()

@admin.route('/admin/<int:admin_id>/crearEntregas', methods=["POST"])
def crear_entrega_handler(admin_id):
    return crear_entrega(admin_id)



@admin.route('/admin/<int:admin_id>/crearRevision', methods=["POST"])
def crear_revision_handler(admin_id):
    return crear_revision(admin_id)


@admin.route('/admin/<int:admin_id>/detalles', methods=["GET"])
def obtener_admin_handler(admin_id):
    return obtener_admin_con_revisiones(admin_id)