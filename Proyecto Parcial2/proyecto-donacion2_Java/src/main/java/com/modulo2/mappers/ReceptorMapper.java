package com.modulo2.mappers;

import com.modulo2.dto.ReceptorDTO;
import com.modulo2.model.Receptor;

public class ReceptorMapper {

public static ReceptorDTO toDTO(Receptor receptor) {
    if (receptor == null) return null;

    return new ReceptorDTO(
        receptor.getId(),
        receptor.getNombre(),
        receptor.getCedula(),
        receptor.getTelefono(),
        receptor.getDireccion(),
        receptor.getCorreo()
    );
}

public static Receptor toEntity(ReceptorDTO dto) {
    if (dto == null) return null;

    Receptor receptor = new Receptor();
    receptor.setId(dto.getId());
    receptor.setNombre(dto.getNombre());
    receptor.setCedula(dto.getCedula());
    receptor.setTelefono(dto.getTelefono());
    receptor.setDireccion(dto.getDireccion());
    receptor.setCorreo(dto.getCorreo());

    return receptor;
}

}