# 📦 API de Donaciones – Módulo Backend

Este módulo provee una API REST para gestionar donaciones de víveres. Permite registrar, consultar, actualizar y eliminar donaciones mediante Supabase como base de datos.

---

## 📌 Endpoints

### 🔹 GET `/api/donaciones`

📄 Devuelve todas las donaciones registradas.

```bash
curl http://localhost:3001/api/donaciones
```

---

### 🔹 POST `/api/donaciones`

🔟 Registra una nueva donación.

```bash
curl -Uri "http://localhost:3001/api/donaciones" `
  -Method POST `
  -Body '{"id_donante":1,"estado":"pendiente","prioridad":"alta"}' `
  -Headers @{"Content-Type"="application/json"}
```

---

### 🔹 PUT `/api/donaciones/:id`

🗘️ Actualiza el estado o prioridad de una donación.

```bash
curl -Uri "http://localhost:3001/api/donaciones/25" `
  -Method PUT `
  -Body '{"estado":"entregado","prioridad":"media"}' `
  -Headers @{"Content-Type"="application/json"}
```

---

### 🔹 DELETE `/api/donaciones/:id`

🗑️ Elimina una donación por su ID.

```bash
curl -X DELETE http://localhost:3001/api/donaciones/25
```

---

## ✅ Validaciones

* `POST` requiere todos los campos: `id_donante`, `estado`, `prioridad`.
* `PUT` requiere al menos uno: `estado` o `prioridad`.
* `DELETE` y `PUT` validan que el `:id` sea un número válido.

---

## ⚙️ Stack utilizado

* Node.js + TypeScript
* Express
* Supabase (PostgreSQL)
* Thunder Client / cURL para pruebas

---

## 🧪 Notas finales

✔ Probado en PowerShell con `curl` y también desde Thunder Client.
🔒 No incluye autenticación ni RLS por ahora (puede añadirse en siguientes módulos).

---

> Desarrollado como parte del proyecto **Donación de Víveres** – Aplicaciones para el Servidor Web 🌐
