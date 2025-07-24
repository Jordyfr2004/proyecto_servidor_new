from flask import request, jsonify
from app.Domain.Dtos.createAgendaDto.createAgendaDto import CreateAgendaDTO
from app.Domain.Dtos.createAgendaDto.updateAgendaDto import UpdateAgendaDTO
from app.Domain.UseCases.agendaUseCases.createAgenda import CreateAgendaUseCase
from app.Domain.UseCases.agendaUseCases.getAgenda import GetAgendaUseCase
from app.Domain.UseCases.agendaUseCases.getAgendaId import GetAgendaByIdUseCase
from app.Domain.UseCases.agendaUseCases.updateAgenda import UpdateAgendaUseCase
from app.Domain.UseCases.agendaUseCases.deleteAgenda import DeleteAgendaUseCase
from app.Infrastructure.Repositories.agendaRepositorie.agendaRepositorie import AgendaRepository



class AgendaController:
    def __init__(self):
        self.repository = AgendaRepository()

    def create_agenda(self):
        data = request.get_json()
        dto = CreateAgendaDTO(
            titulo=data.get("titulo"),
            descripcion=data.get("descripcion"),
            fecha=data.get("fecha"),
            admin_id=data.get("admin_id")
        )

        use_case = CreateAgendaUseCase(self.repository)
        agenda = use_case.execute(dto)

        return jsonify({
            "id": agenda.id,
            "titulo": agenda.titulo,
            "descripcion": agenda.descripcion,
            "fecha": agenda.fecha,
            "admin_id": agenda.admin_id
        }),201

    def get_all_agendas(self):
        use_case = GetAgendaUseCase(self.repository)
        agendas = use_case.execute()

        return jsonify([
            {
                "id": a.id,
                "titulo": a.titulo,
                "descripcion": a.descripcion,
                "fecha": a.fecha,
                "admin_id": a.admin_id
            } for a in agendas
        ])

    def get_agenda_by_id(self, agenda_id):
        use_case = GetAgendaByIdUseCase(self.repository)
        agenda = use_case.execute(agenda_id)

        if not agenda:
            return jsonify({"message": "Agenda no encontrada"}), 404

        return jsonify({
            "id": agenda.id,
            "titulo": agenda.titulo,
            "descripcion": agenda.descripcion,
            "fecha": agenda.fecha,
            "admin_id": agenda.admin_id
        })

    def update_agenda(self, agenda_id):
        data = request.get_json()
        dto = UpdateAgendaDTO(
            id=agenda_id,
            titulo=data.get("titulo"),
            descripcion=data.get("descripcion"),
            fecha=data.get("fecha"),
            admin_id=data.get("admin_id")
        )

        use_case = UpdateAgendaUseCase(self.repository)
        updated_agenda = use_case.execute(dto)

        if not updated_agenda:
            return jsonify({"message": "No se pudo actualizar la agenda"}), 404

        return jsonify({
            "id": updated_agenda.id,
            "titulo": updated_agenda.titulo,
            "descripcion": updated_agenda.descripcion,
            "fecha": updated_agenda.fecha,
            "admin_id": updated_agenda.admin_id
        })

    def delete_agenda(self, agenda_id):
        use_case = DeleteAgendaUseCase(self.repository)
        result = use_case.execute(agenda_id)

        if result:
            return jsonify({"message": "Agenda eliminada correctamente"})
        else:
            return jsonify({"message": "Agenda no encontrada"}), 404