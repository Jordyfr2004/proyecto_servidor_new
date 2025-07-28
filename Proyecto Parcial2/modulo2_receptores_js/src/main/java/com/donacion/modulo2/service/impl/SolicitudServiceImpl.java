package com.donacion.modulo2.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donacion.modulo2.dto.SolicitudDTO;
import com.donacion.modulo2.entity.Receptor;
import com.donacion.modulo2.entity.Solicitud;
import com.donacion.modulo2.exception.RecursoNoEncontradoException;
import com.donacion.modulo2.repository.ReceptorRepository;
import com.donacion.modulo2.repository.SolicitudRepository;
import com.donacion.modulo2.service.SolicitudService;

import jakarta.transaction.Transactional;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private ReceptorRepository receptorRepository;


    @Autowired
    private ReceptorServiceImpl receptorService;

    @Autowired
    private com.donacion.modulo2.util.NotificacionUtil notificacionUtil;

    @Override
    @Transactional
    public SolicitudDTO crearSolicitud(SolicitudDTO dto) {
        Receptor receptor = receptorRepository.findById(dto.getIdReceptor())
                .orElseThrow(() -> new RecursoNoEncontradoException("Receptor no encontrado con ID: " + dto.getIdReceptor()));

        Solicitud solicitud = new Solicitud();
        solicitud.setTipoDonacion(dto.getTipoDonacion());
        solicitud.setDescripcion(dto.getDescripcion());
        solicitud.setFechaSolicitud(dto.getFechaSolicitud());
        solicitud.setEstado(dto.getEstado());
        solicitud.setReceptor(receptor);

        Solicitud guardada = solicitudRepository.save(solicitud);

        // Notificación al Gateway
        notificacionUtil.enviarNotificacion("Nueva solicitud creada con ID: " + guardada.getIdSolicitud());

        return mapToDTO(guardada);
    }

    @Override
    public List<SolicitudDTO> obtenerTodas() {
        // TEMPORAL: Usar consulta simple para debugging
        try {
            List<Solicitud> solicitudes = solicitudRepository.findAll();
            System.out.println("✅ Encontradas " + solicitudes.size() + " solicitudes");
            
            return solicitudes.stream()
                    .map(this::mapToDTOSimple)  // Usar mapeo simple
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("❌ Error en obtenerTodas: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public SolicitudDTO obtenerPorId(UUID id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Solicitud no encontrada con ID: " + id));
        return mapToDTO(solicitud);
    }

    @Override
    @Transactional
    public SolicitudDTO actualizarSolicitud(UUID id, SolicitudDTO dto) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Solicitud no encontrada con ID: " + id));

        Receptor receptor = receptorRepository.findById(dto.getIdReceptor())
                .orElseThrow(() -> new RecursoNoEncontradoException("Receptor no encontrado con ID: " + dto.getIdReceptor()));

        solicitud.setTipoDonacion(dto.getTipoDonacion());
        solicitud.setDescripcion(dto.getDescripcion());
        solicitud.setFechaSolicitud(dto.getFechaSolicitud());
        solicitud.setEstado(dto.getEstado());
        solicitud.setReceptor(receptor);

        Solicitud actualizada = solicitudRepository.save(solicitud);
        return mapToDTO(actualizada);
    }

    @Override
    @Transactional
    public void eliminarSolicitud(UUID id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Solicitud no encontrada con ID: " + id));
        solicitudRepository.delete(solicitud);
    }

    @Override
    public List<SolicitudDTO> obtenerPorEstado(String estado) {
        return solicitudRepository.findByEstado(estado)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SolicitudDTO> obtenerPorReceptor(UUID receptorId) {
        return solicitudRepository.findByReceptor_IdReceptor(receptorId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SolicitudDTO> obtenerPorTipoDonacion(String tipoDonacion) {
        return solicitudRepository.findByTipoDonacion(tipoDonacion)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SolicitudDTO> obtenerPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return solicitudRepository.findByFechaSolicitudBetween(fechaInicio, fechaFin)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SolicitudDTO cambiarEstado(UUID id, String nuevoEstado) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Solicitud no encontrada con ID: " + id));
        
        solicitud.setEstado(nuevoEstado);
        Solicitud actualizada = solicitudRepository.save(solicitud);
        return mapToDTO(actualizada);
    }

    @Override
    public long contarPorEstado(String estado) {
        return solicitudRepository.countByEstado(estado);
    }

    @Override
    public List<SolicitudDTO> obtenerPorReceptorYEstado(UUID receptorId, String estado) {
        return solicitudRepository.findByReceptor_IdReceptorAndEstado(receptorId, estado)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Método auxiliar SIMPLE para mapear entidad a DTO (SIN respuesta anidada)
    private SolicitudDTO mapToDTOSimple(Solicitud solicitud) {
        SolicitudDTO dto = new SolicitudDTO();
        dto.setIdSolicitud(solicitud.getIdSolicitud());
        dto.setTipoDonacion(solicitud.getTipoDonacion());
        dto.setDescripcion(solicitud.getDescripcion());
        dto.setFechaSolicitud(solicitud.getFechaSolicitud());
        dto.setEstado(solicitud.getEstado());
        
        // Solo incluir el ID del receptor, NO el objeto completo
        try {
            dto.setIdReceptor(solicitud.getReceptor().getIdReceptor());
        } catch (Exception e) {
            System.err.println("⚠️ Error accediendo a receptor: " + e.getMessage());
            dto.setIdReceptor(null);
        }
        
        return dto;
    }

    // Método auxiliar para convertir una entidad en DTO
    private SolicitudDTO mapToDTO(Solicitud solicitud) {
        SolicitudDTO dto = new SolicitudDTO();
        dto.setIdSolicitud(solicitud.getIdSolicitud());
        dto.setTipoDonacion(solicitud.getTipoDonacion());
        dto.setDescripcion(solicitud.getDescripcion());
        dto.setFechaSolicitud(solicitud.getFechaSolicitud());
        dto.setEstado(solicitud.getEstado());
        
        // Mantener el ID para compatibilidad con creación/actualización
        dto.setIdReceptor(solicitud.getReceptor().getIdReceptor());
        
        // RESPUESTA ANIDADA: Incluir información completa del receptor
        // TEMPORALMENTE COMENTADO PARA DEBUGGING
        // dto.setReceptor(receptorService.mapToBasicDTO(solicitud.getReceptor()));
        
        return dto;
    }

    /**
     * Método público para crear un DTO con respuesta anidada (para otras entidades)
     */
    public SolicitudDTO mapToBasicDTO(Solicitud solicitud) {
        SolicitudDTO dto = new SolicitudDTO();
        dto.setIdSolicitud(solicitud.getIdSolicitud());
        dto.setTipoDonacion(solicitud.getTipoDonacion());
        dto.setDescripcion(solicitud.getDescripcion());
        dto.setFechaSolicitud(solicitud.getFechaSolicitud());
        dto.setEstado(solicitud.getEstado());
        
        // Mantener el ID para compatibilidad
        dto.setIdReceptor(solicitud.getReceptor().getIdReceptor());
        
        // RESPUESTA ANIDADA: Incluir información completa del receptor
        dto.setReceptor(receptorService.mapToBasicDTO(solicitud.getReceptor()));
        
        return dto;
    }
}
