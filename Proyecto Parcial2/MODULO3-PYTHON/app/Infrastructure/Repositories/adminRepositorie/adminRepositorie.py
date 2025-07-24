from app.Domain.Interfaces.adminInterfaz.adminterfaz import AdminInterface
from app.Domain.entities.administrador.adminEntitie import Admin
from app.Infrastructure.Models.Administrador.administrador import Admin as AdminORM
from app.Infrastructure.mappers.admininMapper.adminMapper import AdminMapper
from app.Infrastructure.Db.DataSource import db

class AdminRepository(AdminInterface):
    def get_all(self) -> list[Admin]:
        admins_orm = AdminORM.query.all()
        return AdminMapper.to_domain_list(admins_orm)

    def get_by_id(self, admin_id: int) -> Admin:
        admin_orm = AdminORM.query.get(admin_id)
        return AdminMapper.to_domain(admin_orm) if admin_orm else None

    def create(self, admin: Admin) -> Admin:
        admin_orm = AdminMapper.to_orm(admin)
        db.session.add(admin_orm)
        db.session.commit()
        return AdminMapper.to_domain(admin_orm)

    def update(self, admin: Admin) -> Admin:
        admin_orm = AdminORM.query.get(admin.id)
        if admin_orm:
            admin_orm.nombre = admin.nombre
            admin_orm.usuario = admin.usuario
            admin_orm.correo = admin.correo
            admin_orm.password = admin.password
            db.session.commit()
        return AdminMapper.to_domain(admin_orm)

    def delete(self, admin_id: int) -> bool:
        admin_orm = AdminORM.query.get(admin_id)
        if admin_orm:
            db.session.delete(admin_orm)
            db.session.commit()
            return True
        return False
    
    def get_by_email(self, correo: str) -> Admin:
        admin_orm = AdminORM.query.filter_by(correo=correo).first()
        return AdminMapper.to_domain(admin_orm) if admin_orm else None


    
    