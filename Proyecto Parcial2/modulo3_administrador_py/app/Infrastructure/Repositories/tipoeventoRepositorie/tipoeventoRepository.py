from app.Domain.Interfaces.tipoeventointerfaz.tipoeventointerfaz import TipoEventoInterface
from app.Domain.entities.tipoEvento.tipoEventoEntitie import TipoEvento
from app.Infrastructure.Models.TipoEvento.tipoEvento import TipoEvento as TipoEventoORM
from app.Infrastructure.mappers.tipoeventoMapper.tipoEventoMapper import TipoEventoMapper
from app.Infrastructure.Db.DataSource import db

class TipoEventoRepository(TipoEventoInterface):

    def get_all(self) -> list[TipoEvento]:
        tipos_orm = TipoEventoORM.query.all()
        return TipoEventoMapper.to_domain_list(tipos_orm)

    def get_by_id(self, tipo_id: int) -> TipoEvento:
        tipo_orm = TipoEventoORM.query.get(tipo_id)
        return TipoEventoMapper.to_domain(tipo_orm) if tipo_orm else None

    def create(self, tipo: TipoEvento) -> TipoEvento:
        tipo_orm = TipoEventoMapper.to_orm(tipo)
        db.session.add(tipo_orm)
        db.session.commit()
        return TipoEventoMapper.to_domain(tipo_orm)

    def update(self, tipo: TipoEvento) -> TipoEvento:
        tipo_orm = TipoEventoORM.query.get(tipo.id)
        if tipo_orm:
            tipo_orm.tipo = tipo.tipo
            tipo_orm.id_admin = tipo.admin_id
            db.session.commit()
        return TipoEventoMapper.to_domain(tipo_orm)

    def delete(self, tipo_id: int) -> bool:
        tipo_orm = TipoEventoORM.query.get(tipo_id)
        if tipo_orm:
            db.session.delete(tipo_orm)
            db.session.commit()
            return True
        return False
