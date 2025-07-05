package com.modulo2.mappers;

import com.modulo2.dto.EntregaDTO;
import com.modulo2.model.Entrega;
import com.modulo2.model.Donacion;
import com.modulo2.model.Solicitud;

public class EntregaMapper {

// Convertir de Entrega (Entidad) a EntregaDTO
public static EntregaDTO toDTO(Entrega entrega) {
    if (entrega == null) return null;

    EntregaDTO dto = new EntregaDTO();
    dto.setId(entrega.getId());
    dto.setFechaEntrega(entrega.getFechaEntrega());
    dto.setObservacion(entrega.getObservacion());

    if (entrega.getSolicitud() != null) {
        dto.setSolicitudId(entrega.getSolicitud().getId());
    }

    if (entrega.getDonacion() != null) {
        dto.setDonacionId(entrega.getDonacion().getId());
    }

    return dto;
}

// Convertir de EntregaDTO a Entrega (Entidad) usando los objetos ya buscados
public static Entrega toEntity(EntregaDTO dto, Solicitud solicitud, Donacion donacion) {
    if (dto == null) return null;

    Entrega entrega = new Entrega();
    entrega.setId(dto.getId());
    entrega.setFechaEntrega(dto.getFechaEntrega());
    entrega.setObservacion(dto.getObservacion());
    entrega.setSolicitud(solicitud); // aquí usamos el objeto existente
    entrega.setDonacion(donacion);   // igual acá

    return entrega;
}

}