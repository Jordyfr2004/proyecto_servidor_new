from flask import Flask
from utils.dataSource import db, DATABASE_URL
from models.admin import Admin
from models.entrega import Entrega
from models.revision_solicitud import RevisionSolicitud
from routes.adminroute import admin

app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = DATABASE_URL
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db.init_app(app)  # ← ¡IMPORTANTE!

app.register_blueprint(admin)
#app.register_blueprint(recepcion)


#ihiciar entorno virtual
#venv\Scripts\activate
#salir
#deactivate

#iniciar servidor
#python index.py

