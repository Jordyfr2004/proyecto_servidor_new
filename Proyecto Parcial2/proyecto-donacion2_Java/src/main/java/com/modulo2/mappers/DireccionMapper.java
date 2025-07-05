package com.modulo2.mappers;

import com.modulo2.dto.DireccionDTO;
import com.modulo2.model.Direccion;
import com.modulo2.model.Receptor;

public class DireccionMapper {

// Convierte de entidad a DTO
public static DireccionDTO toDTO(Direccion direccion) {
    if (direccion == null) return null;

    DireccionDTO dto = new DireccionDTO();
    dto.setId(direccion.getId());
    dto.setCalle(direccion.getCalle());
    dto.setCiudad(direccion.getCiudad());
    dto.setProvincia(direccion.getProvincia());
    dto.setCodigoPostal(direccion.getCodigoPostal());

    if (direccion.getReceptor() != null) {
        dto.setReceptorId(direccion.getReceptor().getId());
    }

    return dto;
}

// Convierte de DTO a entidad, con el receptor ya buscado
public static Direccion toEntity(DireccionDTO dto, Receptor receptor) {
    if (dto == null) return null;

    Direccion direccion = new Direccion();
    direccion.setId(dto.getId());
    direccion.setCalle(dto.getCalle());
    direccion.setCiudad(dto.getCiudad());
    direccion.setProvincia(dto.getProvincia());
    direccion.setCodigoPostal(dto.getCodigoPostal());
    direccion.setReceptor(receptor);

    return direccion;
}
}

