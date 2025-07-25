package com.donacion.modulo2.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donacion.modulo2.dto.EntregaDTO;
import com.donacion.modulo2.entity.Entrega;
import com.donacion.modulo2.entity.Solicitud;
import com.donacion.modulo2.exception.RecursoNoEncontradoException;
import com.donacion.modulo2.repository.EntregaRepository;
import com.donacion.modulo2.repository.SolicitudRepository;
import com.donacion.modulo2.service.EntregaService;

import jakarta.transaction.Transactional;

/**
 * EntregaServiceImpl:
 * Implementación del servicio de entrega de víveres.
 */
@Service
public class EntregaServiceImpl implements EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private SolicitudRepository solicitudRepository;

    // TEMPORAL: NotificacionService comentado según instrucciones del docente
    // @Autowired
    // private NotificacionService notificacionService;

    @Autowired
    private SolicitudServiceImpl solicitudService;

    @Override
    @Transactional
    public EntregaDTO crearEntrega(EntregaDTO dto) {
        Solicitud solicitud = solicitudRepository.findById(dto.getIdSolicitud())
                .orElseThrow(() -> new RecursoNoEncontradoException("Solicitud no encontrada con ID: " + dto.getIdSolicitud()));

        Entrega entrega = new Entrega();
        entrega.setFechaEntrega(dto.getFechaEntrega());
        entrega.setObservacion(dto.getObservacion());
        entrega.setCantidadEntregada(dto.getCantidadEntregada());
        entrega.setUnidad(dto.getUnidad());
        entrega.setEstadoEntrega(dto.getEstadoEntrega());
        entrega.setSolicitud(solicitud);
        
        // ========== ADAPTADOR PARA INTEGRACIÓN CON MÓDULO DE DONACIÓN ==========
        // DESCRIPCIÓN: Convierte IDs numéricos del módulo de donación (ej: "1", "2", "123")
        // a UUIDs compatibles con nuestra base de datos, manteniendo la arquitectura UUID
        if (dto.getIdDonacion() != null) {
            String donacionId = dto.getIdDonacion().trim(); // Limpiar espacios
            
            if (donacionId.matches("\\d+")) {
                // ✅ ES UN NÚMERO: Convertir a UUID con formato específico
                // Ejemplo: "1" → "00000000-0000-0000-0000-000000000001"
                long numeroId = Long.parseLong(donacionId);
                String uuidString = String.format("00000000-0000-0000-0000-%012d", numeroId);
                entrega.setIdDonacion(UUID.fromString(uuidString));
                System.out.println("🔄 ADAPTADOR: ID donación convertido: " + donacionId + " → " + uuidString);
                
            } else if (donacionId.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
                // ✅ YA ES UN UUID: Usar directamente
                entrega.setIdDonacion(UUID.fromString(donacionId));
                System.out.println("✅ ADAPTADOR: UUID donación usado directamente: " + donacionId);
                
            } else {
                // ❌ FORMATO NO VÁLIDO: Usar UUID temporal y loggear error
                entrega.setIdDonacion(UUID.fromString("11111111-1111-1111-1111-111111111111"));
                System.err.println("⚠️ ADAPTADOR: Formato de ID donación no válido: " + donacionId + " - Usando UUID temporal");
            }
        } else {
            // 🔧 TESTING: UUID temporal cuando no viene idDonacion
            entrega.setIdDonacion(UUID.fromString("11111111-1111-1111-1111-111111111111"));
            System.out.println("🧪 ADAPTADOR: Usando UUID temporal para testing (sin idDonacion)");
        }
        // ====================================================================

        Entrega guardada = entregaRepository.save(entrega);

        // ----------- NOTIFICACIÓN WEBSOCKET -----------
        // TEMPORAL: Comentado para testing - el servidor de notificaciones no está disponible
        // notificacionService.notificarWebSocket(
        //     "Nueva entrega registrada con ID: " + guardada.getIdEntrega()
        // );

        return mapToDTO(guardada);
    }

    @Override
    public List<EntregaDTO> obtenerTodas() {
        // TEMPORAL: Usar consulta simple para debugging
        try {
            List<Entrega> entregas = entregaRepository.findAll();
            System.out.println("✅ Encontradas " + entregas.size() + " entregas");
            
            return entregas.stream()
                    .map(this::mapToDTOSimple)  // Usar mapeo simple
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("❌ Error en obtenerTodas: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public EntregaDTO obtenerPorId(UUID id) {
        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Entrega no encontrada con ID: " + id));
        return mapToDTO(entrega);
    }

    @Override
    @Transactional
    public EntregaDTO actualizarEntrega(UUID id, EntregaDTO dto) {
        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Entrega no encontrada con ID: " + id));

        Solicitud solicitud = solicitudRepository.findById(dto.getIdSolicitud())
                .orElseThrow(() -> new RecursoNoEncontradoException("Solicitud no encontrada con ID: " + dto.getIdSolicitud()));

        entrega.setFechaEntrega(dto.getFechaEntrega());
        entrega.setObservacion(dto.getObservacion());
        entrega.setCantidadEntregada(dto.getCantidadEntregada());
        entrega.setUnidad(dto.getUnidad());
        entrega.setEstadoEntrega(dto.getEstadoEntrega());
        entrega.setSolicitud(solicitud);
        
        // ========== ADAPTADOR PARA INTEGRACIÓN CON MÓDULO DE DONACIÓN (UPDATE) ==========
        // Aplicar la misma lógica de conversión para actualizaciones
        if (dto.getIdDonacion() != null) {
            String donacionId = dto.getIdDonacion().trim();
            
            if (donacionId.matches("\\d+")) {
                // ✅ ES UN NÚMERO: Convertir a UUID
                long numeroId = Long.parseLong(donacionId);
                String uuidString = String.format("00000000-0000-0000-0000-%012d", numeroId);
                entrega.setIdDonacion(UUID.fromString(uuidString));
                System.out.println("🔄 ADAPTADOR (UPDATE): ID donación convertido: " + donacionId + " → " + uuidString);
                
            } else if (donacionId.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
                // ✅ YA ES UN UUID: Usar directamente
                entrega.setIdDonacion(UUID.fromString(donacionId));
                System.out.println("✅ ADAPTADOR (UPDATE): UUID donación usado directamente: " + donacionId);
            } else {
                // ❌ FORMATO NO VÁLIDO: Mantener el valor actual
                System.err.println("⚠️ ADAPTADOR (UPDATE): Formato inválido, manteniendo valor actual: " + donacionId);
            }
        }
        // ===============================================================================

        Entrega actualizada = entregaRepository.save(entrega);
        return mapToDTO(actualizada);
    }

    @Override
    @Transactional
    public void eliminarEntrega(UUID id) {
        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Entrega no encontrada con ID: " + id));
        entregaRepository.delete(entrega);
    }

    /**
     * Método auxiliar SIMPLE para mapear entidad a DTO (SIN respuesta anidada)
     */
    private EntregaDTO mapToDTOSimple(Entrega entrega) {
        EntregaDTO dto = new EntregaDTO();
        dto.setIdEntrega(entrega.getIdEntrega());
        dto.setFechaEntrega(entrega.getFechaEntrega());
        dto.setObservacion(entrega.getObservacion());
        dto.setCantidadEntregada(entrega.getCantidadEntregada());
        dto.setUnidad(entrega.getUnidad());
        dto.setEstadoEntrega(entrega.getEstadoEntrega());
        
        // 🔄 ADAPTADOR: Convertir UUID a String para compatibilidad con Apollo
        if (entrega.getIdDonacion() != null) {
            dto.setIdDonacion(entrega.getIdDonacion().toString());
        }
        
        // Solo incluir el ID de la solicitud, NO el objeto completo
        try {
            dto.setIdSolicitud(entrega.getSolicitud().getIdSolicitud());
        } catch (Exception e) {
            System.err.println("⚠️ Error accediendo a solicitud: " + e.getMessage());
            dto.setIdSolicitud(null);
        }
        
        return dto;
    }

    /**
     * Convierte la entidad Entrega a su DTO.
     */
    private EntregaDTO mapToDTO(Entrega entrega) {
        EntregaDTO dto = new EntregaDTO();
        dto.setIdEntrega(entrega.getIdEntrega());
        dto.setFechaEntrega(entrega.getFechaEntrega());
        dto.setObservacion(entrega.getObservacion());
        dto.setCantidadEntregada(entrega.getCantidadEntregada());
        dto.setUnidad(entrega.getUnidad());
        dto.setEstadoEntrega(entrega.getEstadoEntrega());
        
        // 🔄 ADAPTADOR: Convertir UUID a String para compatibilidad con Apollo
        if (entrega.getIdDonacion() != null) {
            dto.setIdDonacion(entrega.getIdDonacion().toString());
        }
        
        // Mantener el ID para compatibilidad con creación/actualización
        dto.setIdSolicitud(entrega.getSolicitud().getIdSolicitud());
        
        // RESPUESTA ANIDADA EN CADENA: Solicitud → Receptor
        // TEMPORALMENTE COMENTADO PARA DEBUGGING
        // dto.setSolicitud(solicitudService.mapToBasicDTO(entrega.getSolicitud()));
        
        // Mapear la relación OneToMany con HistorialReceptor
        if (entrega.getHistorialEntregas() != null) {
            dto.setIdsHistorialEntregas(entrega.getHistorialEntregas().stream()
                    .map(historial -> historial.getIdHistorial())
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }

    /**
     * Método público para crear un DTO básico sin historial (para evitar dependencia circular)
     */
    public EntregaDTO mapToBasicDTO(Entrega entrega) {
        EntregaDTO dto = new EntregaDTO();
        dto.setIdEntrega(entrega.getIdEntrega());
        dto.setFechaEntrega(entrega.getFechaEntrega());
        dto.setObservacion(entrega.getObservacion());
        dto.setCantidadEntregada(entrega.getCantidadEntregada());
        dto.setUnidad(entrega.getUnidad());
        dto.setEstadoEntrega(entrega.getEstadoEntrega());
        
        // 🔄 ADAPTADOR: Convertir UUID a String para compatibilidad con Apollo
        if (entrega.getIdDonacion() != null) {
            dto.setIdDonacion(entrega.getIdDonacion().toString());
        }
        
        // Mantener el ID para compatibilidad
        dto.setIdSolicitud(entrega.getSolicitud().getIdSolicitud());
        
        // RESPUESTA ANIDADA EN CADENA: Solicitud → Receptor
        dto.setSolicitud(solicitudService.mapToBasicDTO(entrega.getSolicitud()));
        
        // No incluir historial para evitar dependencia circular
        dto.setIdsHistorialEntregas(null);
        
        return dto;
    }

    @Override
    public List<EntregaDTO> obtenerPorSolicitud(UUID solicitudId) {
        return entregaRepository.findBySolicitud_IdSolicitud(solicitudId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntregaDTO> obtenerPorDonacion(UUID idDonacion) {
        return entregaRepository.findByIdDonacion(idDonacion)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntregaDTO> obtenerPorEstado(String estadoEntrega) {
        return entregaRepository.findByEstadoEntrega(estadoEntrega)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntregaDTO> obtenerPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return entregaRepository.findByFechaEntregaBetween(fechaInicio, fechaFin)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EntregaDTO> obtenerPorReceptor(UUID receptorId) {
        return entregaRepository.findByReceptorId(receptorId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EntregaDTO obtenerUltimaEntregaDeSolicitud(UUID solicitudId) {
        Entrega entrega = entregaRepository.findUltimaEntregaBySolicitud(solicitudId)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontraron entregas para la solicitud: " + solicitudId));
        return mapToDTO(entrega);
    }

    @Override
    @Transactional
    public EntregaDTO cambiarEstado(UUID id, String nuevoEstado) {
        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Entrega no encontrada con ID: " + id));
        
        entrega.setEstadoEntrega(nuevoEstado);
        Entrega actualizada = entregaRepository.save(entrega);
        return mapToDTO(actualizada);
    }

    @Override
    @Transactional
    public EntregaDTO completarEntrega(UUID id) {
        Entrega entrega = entregaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Entrega no encontrada con ID: " + id));
        
        entrega.completar(); // Usa el método auxiliar de la entidad
        Entrega actualizada = entregaRepository.save(entrega);
        return mapToDTO(actualizada);
    }

    @Override
    public long contarPorEstado(String estadoEntrega) {
        return entregaRepository.countByEstadoEntrega(estadoEntrega);
    }

    @Override
    public long contarPorFecha(LocalDateTime fecha) {
        return entregaRepository.countByFechaEntrega(fecha);
    }
}
