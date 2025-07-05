package com.modulo2.mappers;

import com.modulo2.dto.HistorialReceptorDTO;
import com.modulo2.model.HistorialReceptor;
import com.modulo2.model.Receptor;

public class HistorialReceptorMapper {

public static HistorialReceptorDTO toDTO(HistorialReceptor historial) {
    if (historial == null) return null;

    HistorialReceptorDTO dto = new HistorialReceptorDTO();
    dto.setId(historial.getId());
    dto.setFechaRegistro(historial.getFechaRegistro());
    dto.setDescripcion(historial.getDescripcion());

    if (historial.getReceptor() != null) {
        dto.setReceptorId(historial.getReceptor().getId());
    }

    return dto;
}

public static HistorialReceptor toEntity(HistorialReceptorDTO dto, Receptor receptor) {
    if (dto == null) return null;

    HistorialReceptor historial = new HistorialReceptor();
    historial.setId(dto.getId());
    historial.setFechaRegistro(dto.getFechaRegistro());
    historial.setDescripcion(dto.getDescripcion());
    historial.setReceptor(receptor);

    return historial;
}

}
