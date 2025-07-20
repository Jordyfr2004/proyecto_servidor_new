from flask import Blueprint
from app.Presentation.Controllers.adminControllers.adminController import AdminController
from app.Presentation.Controllers.agendaControllers.agendaControllers import AgendaController
from app.Presentation.Controllers.tipoEventoControllers.tipoEventosControllers import TipoEventosController
from app.Presentation.Controllers.eventoControllers.eventoControllers import EventoController
from app.Presentation.Controllers.revisionsoliControllers.revisionSolicitudControllers import RevisionSolicitudController

admin_bp = Blueprint("admin", __name__, url_prefix="/admin")
admin_controller = AdminController()
agenda_controller = AgendaController()
tipo_eventosContr = TipoEventosController()
eventos_control = EventoController()
revision_control = RevisionSolicitudController()

# Rutas
admin_bp.route("/all", methods=["GET"])(admin_controller.get_all_admins)
admin_bp.route("/ver/<int:admin_id>", methods=["GET"])(admin_controller.get_admin_by_id)
admin_bp.route("/Crear", methods=["POST"])(admin_controller.create_admin)
admin_bp.route("/<int:admin_id>",methods=["PUT"])(admin_controller.update_admin)
admin_bp.route("/<int:admin_id>", methods=["DELETE"])(admin_controller.delete_admin)

admin_bp.route("/agenda", methods=["GET"])(agenda_controller.get_all_agendas)
admin_bp.route("/agenda/<int:agenda_id>", methods=["GET"])(agenda_controller.get_agenda_by_id)
admin_bp.route("/agenda/Crear/", methods=["POST"])(agenda_controller.create_agenda)
admin_bp.route("/<int:agenda_id>", methods=["PUT"])(agenda_controller.update_agenda)
admin_bp.route("/agenda/delete/<int:agenda_id>", methods=["DELETE"])(agenda_controller.delete_agenda)


admin_bp.route("/tipos", methods =["GET"])(tipo_eventosContr.get_all_tipo_eventos)
admin_bp.route("/tipos/<int:tipo_id>", methods=["GET"])(tipo_eventosContr.get_tipo_evento_by_id)
admin_bp.route("/tipos/crear", methods = ["POST"])(tipo_eventosContr.create_tipo_evento)
admin_bp.route("/tipos/<int:tipo_id>", methods =["PUT"])(tipo_eventosContr.update_tipo_evento)
admin_bp.route("/tipos/delete/<int:tipo_id>", methods =["Delete"])(tipo_eventosContr.delete_tipo_evento)


admin_bp.route("/eventos", methods= ["GET"])(eventos_control.get_all_eventos)
admin_bp.route("/eventos/<int:evento_id>", methods= ["GET"])(eventos_control.get_evento_by_id)
admin_bp.route("/eventos/crear", methods =["POST"])(eventos_control.create_evento)
admin_bp.route("/eventos/<int:evento_id>", methods=["PUT"])(eventos_control.update_evento)
admin_bp.route("/eventos/delete/<int:evento_id>", methods =["DELETE"])(eventos_control.delete_evento)


admin_bp.route("/revisiones", methods=["GET"])(revision_control.get_all_revisiones)
admin_bp.route("/revisiones/<int:revision_id>", methods=["GET"])(revision_control.get_revision_by_id)
admin_bp.route("/revision/crear", methods=["POST"])(revision_control.create_revision)
admin_bp.route("/revision/<int:revision_id>", methods=["PUT"])(revision_control.update_revision)
admin_bp.route("/revision/delete/<int:revision_id>", methods =["DELETE"])(revision_control.delete_revision)