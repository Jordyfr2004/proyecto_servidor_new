import supabase from '../config/db';
import { Donante } from '../models/donante.model';

export const listarDonantes = async (): Promise<Donante[]> => {
  const { data, error } = await supabase.from('donantes').select();
  if (error) throw error;
  return data as Donante[];
};

export const registrarDonante = async (
  donante: Omit<Donante, 'id'>
): Promise<Donante> => {
  const { data, error } = await supabase
    .from('donantes')
    .insert([donante])
    .select();

  if (error) throw error;
  return data![0];
};

export const actualizarDonante = async (
  id: number,
  donante: Partial<Omit<Donante, 'id'>>
): Promise<Donante> => {
  const { data, error } = await supabase
    .from('donantes')
    .update(donante)
    .eq('id', id)
    .select();

  if (error) throw error;
  return data![0];
};

export const borrarDonante = async (id: number): Promise<void> => {
  const { error } = await supabase.from('donantes').delete().eq('id', id);
  if (error) throw error;
};
