from flask import request, jsonify
from app.Domain.Dtos.createRevisionsoliDto.createRevisionSolicitudDto import CreateRevisionSolicitudDTO
from app.Domain.Dtos.createRevisionsoliDto.updateRevisionSolicitudDto import UpdateRevisionSolicitudDTO
from app.Domain.UseCases.revisionSoliUseCase.createRevisionSoli import CreateRevisionUseCase
from app.Domain.UseCases.revisionSoliUseCase.getRevisionSoli import GetRevisionesUseCase
from app.Domain.UseCases.revisionSoliUseCase.getRevisionSoliId import GetRevisionByIdUseCase
from app.Domain.UseCases.revisionSoliUseCase.updateRevisionSoli import UpdateRevisionUseCase
from app.Domain.UseCases.revisionSoliUseCase.deleteRevisionSoli import DeleteRevisionUseCase
from app.Infrastructure.Repositories.revisionRepositorie.revisionSolicitud import RevisionSolicitudRepository



class RevisionSolicitudController:
    def __init__(self):
        self.repository = RevisionSolicitudRepository()

    def create_revision(self):
        data = request.get_json()

        dto = CreateRevisionSolicitudDTO(
            estado_revision=data.get("estado_revision"),
            observacion=data.get("observacion"),
            admin_id=data.get("admin_id")
        )

        use_case = CreateRevisionUseCase(self.repository)
        revision = use_case.execute(dto)

        return jsonify({
            "id": revision.id,
            "estado_revision": revision.estado_revision,
            "observacion": revision.observacion,
            "admin_id": revision.admin_id
        }), 201

    def get_all_revisiones(self):
        use_case = GetRevisionesUseCase(self.repository)
        revisiones = use_case.execute()

        return jsonify([
            {
                "id": r.id,
                "estado_revision": r.estado_revision,
                "observacion": r.observacion,
                "admin_id": r.admin_id
            } for r in revisiones
        ])

    def get_revision_by_id(self, revision_id):
        use_case = GetRevisionByIdUseCase(self.repository)
        revision = use_case.execute(revision_id)

        if not revision:
            return jsonify({"message": "Revision no encontrada"}), 404

        return jsonify({
            "id": revision.id,
            "estado_revision": revision.estado_revision,
            "observacion": revision.observacion,
            "admin_id": revision.admin_id
        })

    def update_revision(self, revision_id):
        data = request.get_json()

        dto = UpdateRevisionSolicitudDTO(
            id=revision_id,
            estado_revision=data.get("estado_revision"),
            observacion=data.get("observacion"),
            admin_id=data.get("admin_id")
        )

        use_case = UpdateRevisionUseCase(self.repository)
        updated = use_case.execute(dto)

        if not updated:
            return jsonify({"message": "No se pudo actualizar la revision"}), 404

        return jsonify({
            "id": updated.id,
            "estado_revision": updated.estado_revision,
            "observacion": updated.observacion,
            "admin_id": updated.admin_id
        })

    def delete_revision(self, revision_id):
        use_case = DeleteRevisionUseCase(self.repository)
        result = use_case.execute(revision_id)

        if result:
            return jsonify({"message": "Revision eliminada correctamente"})
        else:
            return jsonify({"message": "Revisión no encontrada"}), 404