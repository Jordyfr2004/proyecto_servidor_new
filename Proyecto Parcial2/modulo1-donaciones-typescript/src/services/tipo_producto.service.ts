import supabase from '../config/db';
import { TipoProducto } from '../models/tipo_producto.model';

export async function registrarTipoProducto(tipo: TipoProducto) {
  const { data, error } = await supabase
    .from('tipo_producto')
    .insert([tipo])
    .select();
    
  if (error) throw error;
  return data?.[0];
}

export async function listarTiposProducto() {
  const { data, error } = await supabase
    .from('tipo_producto')
    .select();
    
  if (error) throw error;
  return data;
}

export async function actualizarTipoProducto(id: number, campos: Partial<TipoProducto>) {
  const { data, error } = await supabase
    .from('tipo_producto')
    .update(campos)
    .eq('id_tipo', id)
    .select();

  if (error) throw error;
  return data?.[0];
}

export async function borrarTipoProducto(id: number) {
  const { error } = await supabase
    .from('tipo_producto')
    .delete()
    .eq('id_tipo', id);

  if (error) throw error;
}
