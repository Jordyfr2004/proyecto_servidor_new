import pytest
from app import create_app

# FIXTURE PARA CREAR CLIENTE DE PRUEBA
@pytest.fixture
def client():
    app = create_app()
    app.testing = True
    return app.test_client()

# Puedes usar un token válido real en lugar de este
FAKE_TOKEN = "Bearer faketoken123"

# ---------- TEST DE LOGIN ----------
def test_login_route(client):
    response = client.post('/admin/login', json={
        "correo": "Maria@gmail.com",
        "password": "maria123"
    })
    assert response.status_code in [200, 401]
    assert response.is_json
    if response.status_code == 200:
        json_data = response.get_json()
        assert "token" in json_data

# ---------- TEST OBTENER TODOS LOS ADMIN ----------
def test_get_all_admins(client):
    response = client.get('/admin/all', headers={
        "Authorization": FAKE_TOKEN
    })
    assert response.status_code in [200, 401, 403]
    if response.status_code == 200:
        json_data = response.get_json()
        assert isinstance(json_data, list)

# ---------- TEST CREAR ADMIN ----------
def test_create_admin(client):
    response = client.post('/admin/Crear', json={
        "nombre": "Admin Test",
        "usuario": "Demo",
        "correo": "adminnuevo@test.com",
        "password": "123456"
    })
    assert response.status_code in [201, 400, 401]
    assert response.is_json

# ---------- TEST COMPLETO DE VERIFICACIÓN DE LOGIN ----------
def test_login_verification_complete(client):
    """
    Test completo para verificar el proceso de login:
    1. Intenta login con credenciales incorrectas
    2. Verifica que retorne error 401
    3. Intenta login con credenciales válidas (si existen)
    4. Verifica estructura de respuesta exitosa
    """
    
    # Test 1: Login con credenciales incorrectas
    response_fail = client.post('/admin/login', json={
        "correo": "usuario_inexistente@test.com",
        "password": "password_incorrecto"
    })
    assert response_fail.status_code == 401
    assert response_fail.is_json
    
    # Test 2: Login con formato de email inválido
    response_invalid_email = client.post('/admin/login', json={
        "correo": "email_invalido",
        "password": "123456"
    })
    assert response_invalid_email.status_code in [400, 401]
    assert response_invalid_email.is_json
    
    # Test 3: Login sin proporcionar campos requeridos
    response_missing_fields = client.post('/admin/login', json={
        "correo": "admin@test.com"
        # Falta el campo password
    })
    assert response_missing_fields.status_code == 400
    assert response_missing_fields.is_json
    
    # Test 4: Login con campos vacíos
    response_empty_fields = client.post('/admin/login', json={
        "correo": "",
        "password": ""
    })
    assert response_empty_fields.status_code in [400, 401]
    assert response_empty_fields.is_json
    
    # Test 5: Verificar estructura de respuesta exitosa (si existe admin válido)
    response_success = client.post('/admin/login', json={
        "correo": "Maria@gmail.com",
        "password": "123"
    })
    
    if response_success.status_code == 200:
        json_data = response_success.get_json()
        # Verificar que contiene los campos esperados
        assert "token" in json_data
        assert "id" in json_data or "admin_id" in json_data
        assert "correo" in json_data or "email" in json_data
        
        # Verificar que el token no esté vacío
        assert json_data["token"] is not None
        assert len(json_data["token"]) > 0
        
        # Verificar formato básico del token JWT (opcional)
        token = json_data["token"]
        assert isinstance(token, str)
        # Un JWT básico tiene 3 partes separadas por puntos
        if "." in token:
            parts = token.split(".")
            assert len(parts) >= 2  # Header y Payload mínimo
    
    else:
        # Si no hay admin válido en la DB, debe retornar 401
        assert response_success.status_code == 401
