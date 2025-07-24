# domain/entities/admin.py
from typing import List, Optional
from app.Domain.entities.agenda.agendaEntitie import Agenda
from app.Domain.entities.revisionSolicitud.revisionSolicitudEntitie import RevisionSolicitud
from app.Domain.entities.tipoEvento.tipoEventoEntitie import TipoEvento
class Admin:
    def __init__(
        self,
        id: int = None,
        nombre: str = None,
        usuario: str = None,
        correo: str = None,
        password: str = None,
        agendas: Optional[List[Agenda]] = None,
        revisiones: Optional[List[RevisionSolicitud]] = None,
        tipos_evento: Optional[List[TipoEvento]] = None
    ):
        self.id = id
        self.nombre = nombre
        self.usuario = usuario
        self.correo = correo
        self.password = password

        # relaciones
        self.agendas = agendas or []
        self.revisiones = revisiones or []
        self.tipos_evento = tipos_evento or []


