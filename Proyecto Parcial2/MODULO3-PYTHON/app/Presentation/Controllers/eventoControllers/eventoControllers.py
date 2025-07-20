from flask import request, jsonify
from app.Domain.Dtos.createEventoDto.createEventoDto import CreateEventoDTO
from app.Domain.Dtos.createEventoDto.updateEventoDto import UpdateEventoDTO
from app.Domain.UseCases.eventoUseCases.createEvento import CreateEventoUseCase
from app.Domain.UseCases.eventoUseCases.getEvento import GetEventoUseCase
from app.Domain.UseCases.eventoUseCases.getEventoId import GetEventoByIdUseCase
from app.Domain.UseCases.eventoUseCases.updateEvento import UpdateEventoUseCase
from app.Domain.UseCases.eventoUseCases.deleteEvento import DeleteEventoUseCase
from app.Infrastructure.Repositories.eventoRepositorie.eventoRepositorie import EventoRepository




class EventoController:
    def __init__(self):
        self.repository = EventoRepository()

    def create_evento(self):
        data = request.get_json()
        dto = CreateEventoDTO(
            descripcion=data.get("descripcion"),
            tipo_evento_id=data.get("tipo_evento_id")
        )

        use_case = CreateEventoUseCase(self.repository)
        evento = use_case.execute(dto)

        return jsonify({
            "id": evento.id,
            "descripcion": evento.descripcion,
            "tipo_evento_id": evento.tipo_evento_id
        }), 201

    def get_all_eventos(self):
        use_case = GetEventoUseCase(self.repository)
        eventos = use_case.execute()

        return jsonify([
            {
                "id": e.id,
                "descripcion": e.descripcion,
                "tipo_evento_id": e.tipo_evento_id
            } for e in eventos
        ])

    def get_evento_by_id(self, evento_id):
        use_case = GetEventoByIdUseCase(self.repository)
        evento = use_case.execute(evento_id)

        if not evento:
            return jsonify({"message": "Evento no encontrado"}), 404

        return jsonify({
            "id": evento.id,
            "descripcion": evento.descripcion,
            "tipo_evento_id": evento.tipo_evento_id
        })

    def update_evento(self, evento_id):
        data = request.get_json()
        dto = UpdateEventoDTO(
            id=evento_id,
            descripcion=data.get("descripcion"),
            tipo_evento_id=data.get("tipo_evento_id")
        )

        use_case = UpdateEventoUseCase(self.repository)
        updated = use_case.execute(dto)

        if not updated:
            return jsonify({"message": "No se pudo actualizar el evento"}), 404

        return jsonify({
            "id": updated.id,
            "descripcion": updated.descripcion,
            "tipo_evento_id": updated.tipo_evento_id
        })

    def delete_evento(self, evento_id):
        use_case = DeleteEventoUseCase(self.repository)
        result = use_case.execute(evento_id)

        if result:
            return jsonify({"message": "Evento eliminado correctamente"})
        else:
            return jsonify({"message": "Evento no encontrado"}), 404

