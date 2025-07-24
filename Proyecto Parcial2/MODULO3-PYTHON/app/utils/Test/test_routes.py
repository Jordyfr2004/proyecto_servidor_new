import pytest
from app import create_app


# FIXTURE PARA CREAR CLIENTE DE PRUEBAS
@pytest.fixture
def client():
    app = create_app()
    app.testing = True
    return app.test_client()

# TOKEN FICTICIO PARA PRUEBAS (puedes generar uno real si tu lógica lo permite)
FAKE_TOKEN = "Bearer faketoken123"

# ---------- TEST DE LOGIN ----------
def test_login_route(client):
    response = client.post('/admin/login', json={
        "correo": "admin@test.com",
        "password": "123456"
    })
    assert response.status_code in [200, 401]  # Depende si el admin existe
    assert response.is_json

# ---------- TEST OBTENER TODOS LOS ADMIN (requiere token) ----------
def test_get_all_admins(client):
    response = client.get('/admin/all', headers={
        "Authorization": FAKE_TOKEN
    })
    # Si token_required falla, probablemente 401
    assert response.status_code in [200, 401, 403]

# ---------- TEST CREAR ADMIN ----------
def test_create_admin(client):
    response = client.post('/admin/Crear', json={
        "nombre": "Nuevo Admin",
        "correo": "nuevo@test.com",
        "password": "123456"
    }, headers={"Authorization": FAKE_TOKEN})
    assert response.status_code in [201, 401, 403]

# ---------- TEST RUTA NO EXISTENTE ----------
def test_non_existing_route(client):
    response = client.get('/admin/ruta-inexistente')
    assert response.status_code == 404