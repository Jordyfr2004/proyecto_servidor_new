# dataSource.py

import os
from flask_sqlalchemy import SQLAlchemy
from dotenv import load_dotenv

load_dotenv()

# Variables de entorno
DB_USER = os.getenv('DB_USER')
DB_PASSWORD = os.getenv('DB_PASSWORD')
DB_HOST = os.getenv('DB_HOST')
DB_PORT = os.getenv('DB_PORT')
DB_NAME = os.getenv('DB_NAME')

# URL de conexión PostgreSQL
DATABASE_URL = f'postgresql+psycopg2://{DB_USER}:{DB_PASSWORD}@{DB_HOST}:{DB_PORT}/{DB_NAME}'

# Instancia de SQLAlchemy (NO usar app.config aquí)
db = SQLAlchemy()

