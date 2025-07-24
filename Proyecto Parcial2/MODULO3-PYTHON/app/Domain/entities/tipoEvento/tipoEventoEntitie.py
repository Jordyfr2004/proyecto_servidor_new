from typing import Optional, List
from  app.Domain.entities.evento.eventoEntitie import Evento # Asegúrate de que exista

class TipoEvento:
    def __init__(
        self,
        id: int = None,
        tipo: str = None,
        admin_id: int = None,
        eventos: Optional[List[Evento]] = None
    ):
        self.id = id
        self.tipo = tipo
        self.admin_id = admin_id  # Clave foránea hacia Admin
        self.eventos = eventos or []  # Lista de eventos asociados
