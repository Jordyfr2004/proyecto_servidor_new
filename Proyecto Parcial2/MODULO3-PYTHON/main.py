from app import create_app
from app.Infrastructure.Db.DataSource import db
from app.Infrastructure.Models.Administrador import administrador
from app.Infrastructure.Models.Agenda import agenda
from app.Infrastructure.Models.TipoEvento import tipoEvento
from app.Infrastructure.Models.Evento import evento
from app.Infrastructure.Models.RevisionSolicitud import revisionSolicitud


app = create_app()

with app.app_context():
    db.create_all()
    print("✅ Tablas creadas correctamente.")


if __name__ =='__main__':
    app.run(debug=True)