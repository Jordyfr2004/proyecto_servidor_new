from flask import Flask
from  app.Infrastructure.Db.DataSource import db
from app.config.config import Config
from app.Presentation.Routes.adminRoutes import admin_bp


def create_app():
    app = Flask(__name__)
    app.config.from_object(Config)
    db.init_app(app)

    app.register_blueprint(admin_bp)

    return app 