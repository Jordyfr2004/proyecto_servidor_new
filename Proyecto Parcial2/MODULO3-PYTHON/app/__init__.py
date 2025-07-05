from flask import Flask
from  app.Infrastructure.Db.DataSource import db
from app.config.config import Config


def create_app():
    app = Flask(__name__)
    app.config.from_object(Config)
    db.init_app(app)

    return app 