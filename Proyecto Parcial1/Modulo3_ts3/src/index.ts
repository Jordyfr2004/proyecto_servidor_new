// src/index.ts
import "reflect-metadata";
import app from "./app";
import { initDatabase } from "./database";

const PORT = 3000;

async function main() {
  try {
    await initDatabase(); 
    app.listen(PORT, () => {
      console.log(`Servidor escuchando en http://localhost:${PORT}`);
    });
  } catch (error) {
    console.error("No se pudo iniciar el servidor:", error);
  }
}

main();

