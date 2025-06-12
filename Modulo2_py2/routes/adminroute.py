# Importamos Blueprint desde Flask para organizar el código en rutas modulares
from flask import Blueprint

# Importamos los controladores que manejan la lógica para crear administrador, entrega y revisión
from controllers.crearadmin import crear_admin
from controllers.crearentrega import crear_entrega
from controllers.crearrevision import crear_revision  # Ajusta el nombre del archivo si es distinto
from controllers.veradmin import obtener_admin_con_revisiones

# Creamos una instancia de Blueprint llamada 'admin', que nos permite agrupar rutas relacionadas
admin = Blueprint('admin', __name__)

# Ruta de prueba básica para comprobar que el servidor está funcionando
@admin.route('/')
def home():
    return 'hola mundo'

# Ruta para crear un nuevo administrador (usa método POST)
@admin.route('/crearAdmin', methods=["POST"])
def crear_admin_handler():
    return crear_admin()

# Ruta para crear entregas asociadas a un administrador específico (usa su ID)
@admin.route('/admin/<int:admin_id>/crearEntregas', methods=["POST"])
def crear_entrega_handler(admin_id):
    return crear_entrega(admin_id)

# Ruta para crear revisiones asociadas a un administrador específico
@admin.route('/admin/<int:admin_id>/crearRevision', methods=["POST"])
def crear_revision_handler(admin_id):
    return crear_revision(admin_id)

# Ruta para obtener detalles de un administrador junto con sus revisiones.
@admin.route('/admin/<int:admin_id>/detalles', methods=["GET"])
def obtener_admin_handler(admin_id):
    return obtener_admin_con_revisiones(admin_id)
