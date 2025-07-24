from flask import request, jsonify
from app.Domain.Dtos.createTipoeventoDto.createTipoEventoDto import CreateTipoEventoDTO
from app.Domain.Dtos.createTipoeventoDto.updateTipoEventoDto import UpdateTipoEventoDTO
from app.Domain.UseCases.tipoeventoUseCases.createTipoEvento import CreateTipoEventoUseCase
from app.Domain.UseCases.tipoeventoUseCases.getTipoEvento import GetTipoEventoUseCase
from app.Domain.UseCases.tipoeventoUseCases.getTipoEventoId import GetTipoEventoByIdUseCase
from app.Domain.UseCases.tipoeventoUseCases.updateTipovento import UpdateTipoEventoUseCase
from app.Domain.UseCases.tipoeventoUseCases.deleteTipoEvento import DeleteTipoEventoUseCase
from app.Infrastructure.Repositories.tipoeventoRepositorie.tipoeventoRepository import TipoEventoRepository


class TipoEventosController:
    def __init__(self):
        self.repository = TipoEventoRepository()

    def create_tipo_evento(self):
        data = request.get_json()
        dto = CreateTipoEventoDTO(
            tipo=data.get("tipo"),
            admin_id=data.get("admin_id")
        )

        use_case = CreateTipoEventoUseCase(self.repository)
        tipo_evento = use_case.execute(dto)

        return jsonify({
            "id": tipo_evento.id,
            "tipo": tipo_evento.tipo,
            "admin_id": tipo_evento.admin_id
        }), 201

    def get_all_tipo_eventos(self):
        use_case = GetTipoEventoUseCase(self.repository)
        tipos = use_case.execute()

        return jsonify([
            {
                "id": t.id,
                "tipo": t.tipo,
                "admin_id": t.admin_id
            } for t in tipos
        ])

    def get_tipo_evento_by_id(self, tipo_id):
        use_case = GetTipoEventoByIdUseCase(self.repository)
        tipo = use_case.execute(tipo_id)

        if not tipo:
            return jsonify({"message": "Tipo de evento no encontrado"}), 404

        return jsonify({
            "id": tipo.id,
            "tipo": tipo.tipo,
            "admin_id": tipo.admin_id
        })

    def update_tipo_evento(self, tipo_id):
        data = request.get_json()
        dto = UpdateTipoEventoDTO(
            id=tipo_id,
            tipo=data.get("tipo"),
            admin_id=data.get("admin_id")
        )

        use_case = UpdateTipoEventoUseCase(self.repository)
        updated = use_case.execute(dto)

        if not updated:
            return jsonify({"message": "No se pudo actualizar el tipo de evento"}), 404

        return jsonify({
            "id": updated.id,
            "tipo": updated.tipo,
            "admin_id": updated.admin_id
        })

    def delete_tipo_evento(self, tipo_id):
        use_case = DeleteTipoEventoUseCase(self.repository)
        result = use_case.execute(tipo_id)

        if result:
            return jsonify({"message": "Tipo de evento eliminado correctamente"})
        else:
            return jsonify({"message": "Tipo de evento no encontrado"}), 404