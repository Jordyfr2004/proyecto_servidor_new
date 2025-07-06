import supabase from '../config/db';
import { Producto } from '../models/producto.model';

export async function registrarProducto(producto: Producto) {
  const { data, error } = await supabase
    .from('producto')
    .insert([producto])
    .select();
    
  if (error) throw error;
  return data?.[0];
}

export async function listarProductos() {
  const { data, error } = await supabase
    .from('producto')
    .select();
    
  if (error) throw error;
  return data;
}

export async function borrarProducto(id: number) {
  const { error } = await supabase
    .from('producto')
    .delete()
    .eq('id_producto', id);

  if (error) throw error;
}

// ✅ NUEVO: Actualizar producto
export async function actualizarProducto(id: number, producto: Partial<Producto>) {
  const { data, error } = await supabase
    .from('producto')
    .update(producto)
    .eq('id_producto', id)
    .select();

  if (error) throw error;
  return data?.[0];
}
