package com.modulo2.mappers;

import com.modulo2.dto.ReceptorDTO;
import com.modulo2.dto.SolicitudDTO;
import com.modulo2.model.Receptor;
import com.modulo2.model.Solicitud;

public class SolicitudMapper {

    public static SolicitudDTO toDTO(Solicitud solicitud) {
        if (solicitud == null) return null;

        Receptor receptor = solicitud.getReceptor();
        ReceptorDTO receptorDTO = new ReceptorDTO(
            receptor.getId(),
            receptor.getNombre(),
            receptor.getCedula(),
            receptor.getTelefono(),
            receptor.getDireccion(),
            receptor.getCorreo()
        );

        return new SolicitudDTO(
            solicitud.getId(),
            solicitud.getFechaSolicitud(),
            solicitud.getEstado(),
            solicitud.getObservacion(),
            receptorDTO
        );
    }

    public static Solicitud toEntity(SolicitudDTO dto) {
        if (dto == null) return null;

        ReceptorDTO r = dto.getReceptor();
        Receptor receptor = new Receptor();
        receptor.setId(r.getId()); // solo se setea el ID, no todos los campos

        Solicitud s = new Solicitud();
        s.setId(dto.getId());
        s.setFechaSolicitud(dto.getFechaSolicitud());
        s.setEstado(dto.getEstado());
        s.setObservacion(dto.getObservacion());
        s.setReceptor(receptor);

        return s;
    }
}
