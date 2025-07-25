package com.donacion.modulo2.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donacion.modulo2.dto.DireccionDTO;
import com.donacion.modulo2.entity.Direccion;
import com.donacion.modulo2.entity.Receptor;
import com.donacion.modulo2.exception.RecursoNoEncontradoException;
import com.donacion.modulo2.repository.DireccionRepository;
import com.donacion.modulo2.repository.ReceptorRepository;
import com.donacion.modulo2.service.DireccionService;

/**
 * DireccionServiceImpl:
 * Implementación del servicio de direcciones.
 */
@Service
public class DireccionServiceImpl implements DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private ReceptorRepository receptorRepository;

    @Autowired
    private ReceptorServiceImpl receptorService;

    @Override
    @Transactional
    public DireccionDTO crearDireccion(DireccionDTO dto) {
        Receptor receptor = receptorRepository.findById(dto.getIdReceptor())
                .orElseThrow(() -> new RecursoNoEncontradoException("Receptor no encontrado con ID: " + dto.getIdReceptor()));

        Direccion direccion = new Direccion();
        direccion.setCalle(dto.getCalle());
        direccion.setCiudad(dto.getCiudad());
        direccion.setProvincia(dto.getProvincia());
        direccion.setReferencia(dto.getReferencia());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        direccion.setLatitud(dto.getLatitud());
        direccion.setLongitud(dto.getLongitud());
        direccion.setEsPrincipal(Boolean.TRUE.equals(dto.getEsPrincipal()));
        direccion.setReceptor(receptor);

        Direccion guardada = direccionRepository.save(direccion);
        return mapToDTO(guardada);
    }

    @Override
    public List<DireccionDTO> obtenerTodas() {
        // TEMPORAL: Usar consulta simple para debugging
        try {
            List<Direccion> direcciones = direccionRepository.findAll();
            System.out.println("✅ Encontradas " + direcciones.size() + " direcciones");
            
            return direcciones.stream()
                    .map(this::mapToDTOSimple)  // Usar mapeo simple
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("❌ Error en obtenerTodas: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public DireccionDTO obtenerPorId(UUID id) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Dirección no encontrada con ID: " + id));
        return mapToDTO(direccion);
    }

    @Override
    @Transactional
    public DireccionDTO actualizarDireccion(UUID id, DireccionDTO dto) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Dirección no encontrada con ID: " + id));

        Receptor receptor = receptorRepository.findById(dto.getIdReceptor())
                .orElseThrow(() -> new RecursoNoEncontradoException("Receptor no encontrado con ID: " + dto.getIdReceptor()));

        direccion.setCalle(dto.getCalle());
        direccion.setCiudad(dto.getCiudad());
        direccion.setProvincia(dto.getProvincia());
        direccion.setReferencia(dto.getReferencia());
        direccion.setCodigoPostal(dto.getCodigoPostal());
        direccion.setLatitud(dto.getLatitud());
        direccion.setLongitud(dto.getLongitud());
        direccion.setEsPrincipal(Boolean.TRUE.equals(dto.getEsPrincipal()));
        direccion.setReceptor(receptor);

        Direccion actualizada = direccionRepository.save(direccion);
        return mapToDTO(actualizada);
    }

    @Override
    @Transactional
    public void eliminarDireccion(UUID id) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Dirección no encontrada con ID: " + id));
        direccionRepository.delete(direccion);
    }

    @Override
    public List<DireccionDTO> obtenerDireccionesPorReceptor(UUID receptorId) {
        List<Direccion> direcciones = direccionRepository.findByReceptorIdReceptor(receptorId);
        return direcciones.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DireccionDTO> obtenerDireccionesPorCiudad(String ciudad) {
        List<Direccion> direcciones = direccionRepository.findByCiudadIgnoreCase(ciudad);
        return direcciones.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DireccionDTO> obtenerDireccionesPorProvincia(String provincia) {
        List<Direccion> direcciones = direccionRepository.findByProvinciaIgnoreCase(provincia);
        return direcciones.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DireccionDTO> obtenerDireccionesPorCiudadYProvincia(String ciudad, String provincia) {
        List<Direccion> direcciones = direccionRepository.findByCiudadIgnoreCaseAndProvinciaIgnoreCase(ciudad, provincia);
        return direcciones.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DireccionDTO obtenerDireccionPrincipal(UUID receptorId) {
        Optional<Direccion> direccion = direccionRepository.findByReceptorIdReceptorAndEsPrincipalTrue(receptorId);
        return direccion.map(this::mapToDTO).orElse(null);
    }

    @Override
    @Transactional
    public DireccionDTO marcarComoPrincipal(UUID direccionId, UUID receptorId) {
        // Primero, desmarcar todas las direcciones principales del receptor
        List<Direccion> direcciones = direccionRepository.findByReceptorIdReceptor(receptorId);
        direcciones.forEach(d -> {
            d.setEsPrincipal(false);
            direccionRepository.save(d);
        });

        // Luego, marcar la dirección especificada como principal
        Direccion direccion = direccionRepository.findById(direccionId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Dirección no encontrada con ID: " + direccionId));
        
        if (!direccion.getReceptor().getIdReceptor().equals(receptorId)) {
            throw new IllegalArgumentException("La dirección no pertenece al receptor especificado");
        }

        direccion.marcarComoPrincipal();
        Direccion actualizada = direccionRepository.save(direccion);
        return mapToDTO(actualizada);
    }

    @Override
    public List<DireccionDTO> obtenerDireccionesConCoordenadas() {
        List<Direccion> direcciones = direccionRepository.findDireccionesConCoordenadas();
        return direcciones.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DireccionDTO> buscarDireccionesPorTexto(String texto) {
        List<Direccion> direcciones = direccionRepository.buscarPorTexto(texto);
        return direcciones.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DireccionDTO actualizarCoordenadas(UUID direccionId, Double latitud, Double longitud) {
        Direccion direccion = direccionRepository.findById(direccionId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Dirección no encontrada con ID: " + direccionId));
        
        direccion.establecerCoordenadas(latitud, longitud);
        Direccion actualizada = direccionRepository.save(direccion);
        return mapToDTO(actualizada);
    }

    @Override
    public long contarDireccionesPorReceptor(UUID receptorId) {
        return direccionRepository.countByReceptorId(receptorId);
    }

    // Método auxiliar SIMPLE para mapear entidad a DTO (SIN respuesta anidada)
    private DireccionDTO mapToDTOSimple(Direccion direccion) {
        DireccionDTO dto = new DireccionDTO();
        dto.setIdDireccion(direccion.getIdDireccion());
        dto.setCalle(direccion.getCalle());
        dto.setCiudad(direccion.getCiudad());
        dto.setProvincia(direccion.getProvincia());
        dto.setReferencia(direccion.getReferencia());
        dto.setCodigoPostal(direccion.getCodigoPostal());
        dto.setLatitud(direccion.getLatitud());
        dto.setLongitud(direccion.getLongitud());
        dto.setEsPrincipal(direccion.getEsPrincipal());
        dto.setFechaCreacion(direccion.getFechaCreacion());
        dto.setFechaActualizacion(direccion.getFechaActualizacion());
        
        // Solo incluir el ID del receptor, NO el objeto completo
        try {
            dto.setIdReceptor(direccion.getReceptor().getIdReceptor());
        } catch (Exception e) {
            System.err.println("⚠️ Error accediendo a receptor: " + e.getMessage());
            dto.setIdReceptor(null);
        }
        
        return dto;
    }

    // Método auxiliar para mapear entidad a DTO
    private DireccionDTO mapToDTO(Direccion direccion) {
        DireccionDTO dto = new DireccionDTO();
        dto.setIdDireccion(direccion.getIdDireccion());
        dto.setCalle(direccion.getCalle());
        dto.setCiudad(direccion.getCiudad());
        dto.setProvincia(direccion.getProvincia());
        dto.setReferencia(direccion.getReferencia());
        dto.setCodigoPostal(direccion.getCodigoPostal());
        dto.setLatitud(direccion.getLatitud());
        dto.setLongitud(direccion.getLongitud());
        dto.setEsPrincipal(direccion.getEsPrincipal());
        dto.setFechaCreacion(direccion.getFechaCreacion());
        dto.setFechaActualizacion(direccion.getFechaActualizacion());
        
        // Mantener el ID para compatibilidad con creación/actualización
        dto.setIdReceptor(direccion.getReceptor().getIdReceptor());
        
        // RESPUESTA ANIDADA: Incluir información completa del receptor
        // TEMPORALMENTE COMENTADO PARA DEBUGGING
        // dto.setReceptor(receptorService.mapToBasicDTO(direccion.getReceptor()));
        
        return dto;
    }
}
