import jwt
from flask import request, jsonify
from app.config.config import Config
from datetime import datetime, timezone,timedelta
from functools import wraps
from flask import current_app

def generate_jwt_token(admin_id: str, correo: str) -> str:
    """
    Genera un token JWT para un administrador autenticado.
    :param admin_id: ID del administrador.
    :param correo: Correo del administrador.
    :return: Token JWT como string.
    """
    payload = {
        "sub": str(admin_id),
        "correo": correo,
        "exp": datetime.now(timezone.utc) + timedelta(hours=2),  # Expira en 2 horas
        "iat": datetime.now(timezone.utc)  # Fecha de creación
    }

    token = jwt.encode(payload, Config.SECRET_KEY, algorithm="HS256")
    return token


def verify_jwt_token(token: str) -> dict:
    try:
        secret_key = current_app.config['SECRET_KEY']
        #print("DEBUG >>> Token recibido:", repr(token))
        #print("DEBUG >>> SECRET_KEY usada:", secret_key)
        payload = jwt.decode(token.strip(), secret_key, algorithms=["HS256"])
        #print("DEBUG >>> Payload decodificado:", payload)
        return payload
    except jwt.ExpiredSignatureError:
        #print("DEBUG >>> Token expirado")
        raise ValueError("Token expirado")
    except jwt.InvalidTokenError as e:
        #print("DEBUG >>> Token inválido:", e)
        raise ValueError("Token inválido")

    

def token_required(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        token = None

        # Obtener token del header Authorization
        if "Authorization" in request.headers:
            auth_header = request.headers["Authorization"]
            if auth_header.startswith("Bearer "):
                token = auth_header.split(" ")[1]

        if not token:
            return jsonify({"error": "UNAUTHORIZED. Token requerido"}), 401

        try:
            payload = verify_jwt_token(token)
            request.admin_id = payload.get("sub")     # ID del admin
            request.admin_email = payload.get("correo")  # Correo del admin
        except ValueError as e:
            return jsonify({"error": str(e)}), 401

        return f(*args, **kwargs)

    return decorated




    


