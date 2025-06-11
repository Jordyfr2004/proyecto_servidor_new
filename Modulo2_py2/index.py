from app import app
from utils.dataSource import db
from models.admin import Admin
from models.entrega import Entrega
from models.revision_solicitud import RevisionSolicitud


with app.app_context():
    db.create_all()

if __name__ == "__main__":
    app.run(debug=True)