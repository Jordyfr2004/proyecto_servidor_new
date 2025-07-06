import supabase from '../config/db';
import { Donacion } from '../models/donacion.model';

export const listarDonaciones = async (): Promise<Donacion[]> => {
  const { data, error } = await supabase
    .from('donaciones')
    .select('*');

  if (error) throw error;
  return data as Donacion[];
};

export const registrarDonacion = async (
  datos: Omit<Donacion, 'id'>
): Promise<Donacion> => {
  const { data, error } = await supabase
    .from('donaciones')
    .insert([datos])
    .select();

  if (error) throw error;
  return data![0];
};

export const borrarDonacion = async (id: number): Promise<void> => {
  const { error } = await supabase
    .from('donaciones')
    .delete()
    .eq('id', id);

  if (error) throw error;
};
