from app import create_app
from app.Infrastructure.Db.DataSource import db
from app.Infrastructure.Models.Administrador.administrador import Admin
from app.Infrastructure.Models.Agenda.agenda import Agenda
from app.Infrastructure.Models.TipoEvento.tipoEvento import TipoEvento
from app.Infrastructure.Models.Evento.evento import Evento
from app.Infrastructure.Models.RevisionSolicitud.revisionSolicitud import RevisionSolicitud


app = create_app()

with app.app_context():
    db.create_all()
    print("✅ Tablas creadas correctamente.")


if __name__ =='__main__':
    app.run(debug=True)