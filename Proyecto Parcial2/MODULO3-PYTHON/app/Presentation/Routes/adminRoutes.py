from flask import Blueprint
from app.Presentation.Controllers.adminControllers.adminController import AdminController

admin_bp = Blueprint("admin", __name__, url_prefix="/admin")
controller = AdminController()

# Rutas
admin_bp.route("/all", methods=["GET"])(controller.get_all_admins)
admin_bp.route("/<int:admin_id>", methods=["GET"])(controller.get_admin_by_id)
