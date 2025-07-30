# Proyecto - Agente Inteligente de Resumen de Donaciones

Este módulo forma parte del sistema de gestión de donaciones. Integra un **agente inteligente** basado en **IA (GPT-3.5-turbo)** que genera resúmenes automáticos de las donaciones registradas. El flujo es activado mediante un endpoint HTTP y automatizado usando **n8n**.

---

## 📅 Contexto del Agente

El agente inteligente tiene como objetivo:

- Leer todas las donaciones recientes
- Generar un resumen descriptivo utilizando lenguaje natural
- Guardar ese resumen en una tabla específica (`resumen_donacion`) para futura referencia o visualización

---

## 📂 Tecnologías Utilizadas

- **NestJS** (API REST)
- **PostgreSQL** via Supabase
- **n8n** (Automatización del flujo)
- **OpenRouter.ai** con modelo `openai/gpt-3.5-turbo`

---

## ⚙️ Instalación y Configuración

### 1. Clonar y preparar entorno

```bash
git clone <repositorio>
cd modulo1_donantes_ts
npm install
```

### 2. Ejecutar el backend

```bash
npm run start:dev
```

### 3. Levantar n8n localmente con Docker

```bash
docker run -it --rm -p 5678:5678 -v %userprofile%\.n8n:/home/node/.n8n n8nio/n8n
```

---

## 🔗 Endpoint de Activación (NestJS)

Este endpoint se encarga de iniciar el flujo de automatización:

### URL:

```
POST http://localhost:3000/api/resumen-donacion/start-agent
```

### Ejemplo con Postman:

- Tipo: **POST**
- Body: *(no necesita)*
- Resultado: El flujo se activa y se guarda un resumen generado por IA en Supabase

---

## 📊 Estructura del Flujo n8n

1. **Webhook**: recibe la petición desde `/start-agent`
2. **HTTP GET**: obtiene todas las donaciones desde `GET /api/donacion`
3. **HTTP POST a IA**: envía las donaciones al modelo LLM para que genere un resumen
4. **HTTP POST final**: guarda el resumen en `POST /api/resumen-donacion`

---

## 📄 Ejemplo de Resultado Guardado

En Supabase, la tabla `resumen_donacion` se llena con registros como:

```json
{
  "contenido": "El 25 de julio de 2025, Juan Modificado realizó una donación completa...",
  "fecha": "2025-07-30",
  "origen": "Agente IA"
}
```

---

## 📄 Video
- URL: https://drive.google.com/file/d/1c35rSrk-oAJP-Lo7SNXsMfdSQqDdT1XT/view?usp=sharing

---

## 💡 Comentarios Finales

- El código está organizado por módulos
- Los archivos relevantes están comentados para comprensión
- La solución cumple con todos los requerimientos del entregable final

---

## 🎓 Autor

Andrés García

---

🚀 Proyecto desarrollado como parte del módulo "Aplicaciones para el Servidor Web" - Segundo Parcial 2025

