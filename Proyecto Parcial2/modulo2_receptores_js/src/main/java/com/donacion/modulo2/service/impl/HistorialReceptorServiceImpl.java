package com.donacion.modulo2.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donacion.modulo2.dto.HistorialReceptorDTO;
import com.donacion.modulo2.entity.Entrega;
import com.donacion.modulo2.entity.HistorialReceptor;
import com.donacion.modulo2.entity.HistorialReceptor.Estado;
import com.donacion.modulo2.entity.HistorialReceptor.TipoEvento;
import com.donacion.modulo2.entity.Receptor;
import com.donacion.modulo2.exception.RecursoNoEncontradoException;
import com.donacion.modulo2.repository.EntregaRepository;
import com.donacion.modulo2.repository.HistorialReceptorRepository;
import com.donacion.modulo2.repository.ReceptorRepository;
import com.donacion.modulo2.service.HistorialReceptorService;

/**
 * HistorialReceptorServiceImpl:
 * Implementación del servicio de historial de receptores.
 */
@Service
public class HistorialReceptorServiceImpl implements HistorialReceptorService {

    @Autowired
    private HistorialReceptorRepository historialRepository;

    @Autowired
    private ReceptorRepository receptorRepository;

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private ReceptorServiceImpl receptorService;

    @Autowired
    private EntregaServiceImpl entregaService;

    @Override
    @Transactional
    public HistorialReceptorDTO crearHistorial(HistorialReceptorDTO dto) {
        Receptor receptor = receptorRepository.findById(dto.getIdReceptor())
                .orElseThrow(() -> new RecursoNoEncontradoException("Receptor no encontrado con ID: " + dto.getIdReceptor()));

        HistorialReceptor historial = new HistorialReceptor();
        historial.setFechaEntrega(dto.getFechaEntrega());
        historial.setObservacion(dto.getObservacion());
        historial.setTipoEvento(dto.getTipoEvento() != null ? dto.getTipoEvento() : TipoEvento.ENTREGA);
        historial.setEstado(dto.getEstado() != null ? dto.getEstado() : Estado.COMPLETADO);
        historial.setDetalleAdicional(dto.getDetalleAdicional());
        historial.setCantidadEntregada(dto.getCantidadEntregada());
        historial.setUnidad(dto.getUnidad());
        historial.setValorEstimado(dto.getValorEstimado());
        historial.setUsuarioRegistrador(dto.getUsuarioRegistrador());
        historial.setReceptor(receptor);

        // Asociar la entrega si se proporciona el ID
        if (dto.getIdEntrega() != null) {
            Entrega entrega = entregaRepository.findById(dto.getIdEntrega())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Entrega no encontrada con ID: " + dto.getIdEntrega()));
            historial.setEntrega(entrega);
        }

        // ========== ADAPTADOR PARA INTEGRACIÓN CON MÓDULO DE DONACIÓN ==========
        // DESCRIPCIÓN: Convierte ID de donación numérico (String) a UUID para almacenamiento interno
        // PROPÓSITO: Registrar donaciones recibidas según PDF "Historial de receptor – Donaciones recibidas"
        if (dto.getIdDonacion() != null) {
            String donacionId = dto.getIdDonacion().trim();
            
            if (donacionId.matches("\\d+")) {
                // ✅ ES UN NÚMERO: Convertir a UUID con formato específico
                // Ejemplo: "1" → "00000000-0000-0000-0000-000000000001"
                long numeroId = Long.parseLong(donacionId);
                String uuidString = String.format("00000000-0000-0000-0000-%012d", numeroId);
                historial.setIdDonacion(UUID.fromString(uuidString));
                System.out.println("🔄 ADAPTADOR: ID donación convertido: " + donacionId + " → " + uuidString);
                
            } else if (donacionId.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
                // ✅ YA ES UN UUID: Usar directamente
                historial.setIdDonacion(UUID.fromString(donacionId));
                System.out.println("✅ ADAPTADOR: UUID donación usado directamente: " + donacionId);
                
            } else {
                // ❌ FORMATO NO VÁLIDO: Usar UUID temporal y loggear error
                historial.setIdDonacion(UUID.fromString("11111111-1111-1111-1111-111111111111"));
                System.err.println("⚠️ ADAPTADOR: Formato de ID donación no válido: " + donacionId + " - Usando UUID temporal");
            }
        } else {
            // 🔧 TESTING: UUID temporal cuando no viene idDonacion
            historial.setIdDonacion(UUID.fromString("11111111-1111-1111-1111-111111111111"));
            System.out.println("🧪 ADAPTADOR: Usando UUID temporal para testing (sin idDonacion)");
        }
        // ====================================================================

        HistorialReceptor guardado = historialRepository.save(historial);
        return mapToDTOSimple(guardado); // Usar mapeo simple para testing
    }

    @Override
    public List<HistorialReceptorDTO> obtenerTodos() {
        // TEMPORAL: Usar consulta simple para debugging
        try {
            List<HistorialReceptor> historiales = historialRepository.findAll();
            System.out.println("✅ Encontrados " + historiales.size() + " historiales");
            
            return historiales.stream()
                    .map(this::mapToDTOSimple)  // Usar mapeo simple
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.err.println("❌ Error en obtenerTodos: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public HistorialReceptorDTO obtenerPorId(UUID id) {
        HistorialReceptor historial = historialRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Historial no encontrado con ID: " + id));
        return mapToDTO(historial);
    }

    @Override
    @Transactional
    public HistorialReceptorDTO actualizarHistorial(UUID id, HistorialReceptorDTO dto) {
        HistorialReceptor historial = historialRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Historial no encontrado con ID: " + id));

        Receptor receptor = receptorRepository.findById(dto.getIdReceptor())
                .orElseThrow(() -> new RecursoNoEncontradoException("Receptor no encontrado con ID: " + dto.getIdReceptor()));

        historial.setFechaEntrega(dto.getFechaEntrega());
        historial.setObservacion(dto.getObservacion());
        historial.setTipoEvento(dto.getTipoEvento() != null ? dto.getTipoEvento() : historial.getTipoEvento());
        historial.setEstado(dto.getEstado() != null ? dto.getEstado() : historial.getEstado());
        historial.setDetalleAdicional(dto.getDetalleAdicional());
        historial.setCantidadEntregada(dto.getCantidadEntregada());
        historial.setUnidad(dto.getUnidad());
        historial.setValorEstimado(dto.getValorEstimado());
        historial.setUsuarioRegistrador(dto.getUsuarioRegistrador());
        historial.setReceptor(receptor);

        // Actualizar la entrega si se proporciona el ID
        if (dto.getIdEntrega() != null) {
            Entrega entrega = entregaRepository.findById(dto.getIdEntrega())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Entrega no encontrada con ID: " + dto.getIdEntrega()));
            historial.setEntrega(entrega);
        } else {
            // Si no se proporciona ID de entrega, limpiar la relación
            historial.setEntrega(null);
        }

        // ========== ADAPTADOR PARA INTEGRACIÓN CON MÓDULO DE DONACIÓN (UPDATE) ==========
        // Aplicar la misma lógica de conversión para actualizaciones
        if (dto.getIdDonacion() != null) {
            String donacionId = dto.getIdDonacion().trim();
            
            if (donacionId.matches("\\d+")) {
                // ✅ ES UN NÚMERO: Convertir a UUID
                long numeroId = Long.parseLong(donacionId);
                String uuidString = String.format("00000000-0000-0000-0000-%012d", numeroId);
                historial.setIdDonacion(UUID.fromString(uuidString));
                System.out.println("🔄 ADAPTADOR (UPDATE): ID donación convertido: " + donacionId + " → " + uuidString);
                
            } else if (donacionId.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
                // ✅ YA ES UN UUID: Usar directamente
                historial.setIdDonacion(UUID.fromString(donacionId));
                System.out.println("✅ ADAPTADOR (UPDATE): UUID donación usado directamente: " + donacionId);
            } else {
                // ❌ FORMATO NO VÁLIDO: Mantener el valor actual
                System.err.println("⚠️ ADAPTADOR (UPDATE): Formato inválido, manteniendo valor actual: " + donacionId);
            }
        }
        // ===============================================================================

        HistorialReceptor actualizado = historialRepository.save(historial);
        return mapToDTO(actualizado);
    }

    @Override
    @Transactional
    public void eliminarHistorial(UUID id) {
        HistorialReceptor historial = historialRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Historial no encontrado con ID: " + id));
        historialRepository.delete(historial);
    }

    @Override
    public List<HistorialReceptorDTO> obtenerHistorialPorReceptor(UUID receptorId) {
        List<HistorialReceptor> historiales = historialRepository.findByReceptorIdReceptorOrderByFechaEntregaDesc(receptorId);
        return historiales.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HistorialReceptorDTO> obtenerHistorialPorTipoEvento(TipoEvento tipoEvento) {
        List<HistorialReceptor> historiales = historialRepository.findByTipoEvento(tipoEvento);
        return historiales.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HistorialReceptorDTO> obtenerHistorialPorEstado(Estado estado) {
        List<HistorialReceptor> historiales = historialRepository.findByEstado(estado);
        return historiales.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HistorialReceptorDTO> obtenerHistorialPorReceptorYTipoEvento(UUID receptorId, TipoEvento tipoEvento) {
        List<HistorialReceptor> historiales = historialRepository.findByReceptorIdReceptorAndTipoEvento(receptorId, tipoEvento);
        return historiales.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HistorialReceptorDTO> obtenerHistorialPorReceptorYEstado(UUID receptorId, Estado estado) {
        List<HistorialReceptor> historiales = historialRepository.findByReceptorIdReceptorAndEstado(receptorId, estado);
        return historiales.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HistorialReceptorDTO> obtenerHistorialPorRangoFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<HistorialReceptor> historiales = historialRepository.findByFechaEntregaBetweenOrderByFechaEntregaDesc(fechaInicio, fechaFin);
        return historiales.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HistorialReceptorDTO> obtenerHistorialPorReceptorYRangoFechas(UUID receptorId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<HistorialReceptor> historiales = historialRepository.findByReceptorIdReceptorAndFechaEntregaBetweenOrderByFechaEntregaDesc(
                receptorId, fechaInicio, fechaFin);
        return historiales.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HistorialReceptorDTO obtenerUltimoHistorialPorReceptor(UUID receptorId) {
        HistorialReceptor historial = historialRepository.findUltimoHistorialPorReceptor(receptorId);
        return historial != null ? mapToDTO(historial) : null;
    }

    @Override
    public List<HistorialReceptorDTO> buscarHistorialPorTexto(String texto) {
        List<HistorialReceptor> historiales = historialRepository.buscarPorTexto(texto);
        return historiales.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public HistorialReceptorDTO completarHistorial(UUID historialId) {
        HistorialReceptor historial = historialRepository.findById(historialId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Historial no encontrado con ID: " + historialId));
        
        historial.completar();
        HistorialReceptor actualizado = historialRepository.save(historial);
        return mapToDTO(actualizado);
    }

    @Override
    @Transactional
    public HistorialReceptorDTO cancelarHistorial(UUID historialId) {
        HistorialReceptor historial = historialRepository.findById(historialId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Historial no encontrado con ID: " + historialId));
        
        historial.cancelar();
        HistorialReceptor actualizado = historialRepository.save(historial);
        return mapToDTO(actualizado);
    }

    @Override
    @Transactional
    public HistorialReceptorDTO registrarEntrega(UUID receptorId, String observacion, Double cantidadEntregada,
                                                String unidad, Double valorEstimado, String usuarioRegistrador) {
        Receptor receptor = receptorRepository.findById(receptorId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Receptor no encontrado con ID: " + receptorId));

        HistorialReceptor historial = new HistorialReceptor();
        historial.setObservacion(observacion);
        historial.setTipoEvento(TipoEvento.ENTREGA);
        historial.setEstado(Estado.COMPLETADO);
        historial.setCantidadEntregada(cantidadEntregada);
        historial.setUnidad(unidad);
        historial.setValorEstimado(valorEstimado);
        historial.setUsuarioRegistrador(usuarioRegistrador);
        historial.setReceptor(receptor);

        HistorialReceptor guardado = historialRepository.save(historial);
        return mapToDTO(guardado);
    }

    @Override
    public long contarHistorialPorReceptor(UUID receptorId) {
        return historialRepository.countByReceptorIdReceptor(receptorId);
    }

    @Override
    public Double obtenerTotalEntregadoPorReceptor(UUID receptorId) {
        return historialRepository.obtenerTotalEntregadoPorReceptor(receptorId);
    }

    @Override
    public Double obtenerValorTotalPorReceptor(UUID receptorId) {
        return historialRepository.obtenerValorTotalPorReceptor(receptorId);
    }

    @Override
    public List<HistorialReceptorDTO> obtenerHistorialPorUsuario(String usuario) {
        List<HistorialReceptor> historiales = historialRepository.findByUsuarioRegistradorIgnoreCase(usuario);
        return historiales.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<HistorialReceptorDTO> obtenerHistorialesRecientes() {
        LocalDateTime fechaInicio = LocalDateTime.now().minusDays(30);
        List<HistorialReceptor> historiales = historialRepository.findHistorialesRecientes(fechaInicio);
        return historiales.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Método auxiliar SIMPLE para mapear entidad a DTO (SIN respuesta anidada)
     */
    private HistorialReceptorDTO mapToDTOSimple(HistorialReceptor historial) {
        HistorialReceptorDTO dto = new HistorialReceptorDTO();
        dto.setIdHistorial(historial.getIdHistorial());
        dto.setFechaEntrega(historial.getFechaEntrega());
        dto.setObservacion(historial.getObservacion());
        dto.setFechaRegistro(historial.getFechaRegistro());
        dto.setFechaActualizacion(historial.getFechaActualizacion());
        dto.setTipoEvento(historial.getTipoEvento());
        dto.setEstado(historial.getEstado());
        dto.setDetalleAdicional(historial.getDetalleAdicional());
        dto.setCantidadEntregada(historial.getCantidadEntregada());
        dto.setUnidad(historial.getUnidad());
        dto.setValorEstimado(historial.getValorEstimado());
        dto.setUsuarioRegistrador(historial.getUsuarioRegistrador());
        
        // Solo incluir los IDs, NO los objetos completos
        try {
            dto.setIdReceptor(historial.getReceptor().getIdReceptor());
        } catch (Exception e) {
            System.err.println("⚠️ Error accediendo a receptor: " + e.getMessage());
            dto.setIdReceptor(null);
        }
        
        try {
            if (historial.getEntrega() != null) {
                dto.setIdEntrega(historial.getEntrega().getIdEntrega());
            }
        } catch (Exception e) {
            System.err.println("⚠️ Error accediendo a entrega: " + e.getMessage());
            dto.setIdEntrega(null);
        }

        // 🔄 ADAPTADOR: Convertir UUID a String para compatibilidad con Apollo
        if (historial.getIdDonacion() != null) {
            dto.setIdDonacion(historial.getIdDonacion().toString());
        }
        
        return dto;
    }

    // Método auxiliar para convertir de entidad a DTO
    private HistorialReceptorDTO mapToDTO(HistorialReceptor historial) {
        HistorialReceptorDTO dto = new HistorialReceptorDTO();
        dto.setIdHistorial(historial.getIdHistorial());
        dto.setFechaEntrega(historial.getFechaEntrega());
        dto.setObservacion(historial.getObservacion());
        dto.setFechaRegistro(historial.getFechaRegistro());
        dto.setFechaActualizacion(historial.getFechaActualizacion());
        dto.setTipoEvento(historial.getTipoEvento());
        dto.setEstado(historial.getEstado());
        dto.setDetalleAdicional(historial.getDetalleAdicional());
        dto.setCantidadEntregada(historial.getCantidadEntregada());
        dto.setUnidad(historial.getUnidad());
        dto.setValorEstimado(historial.getValorEstimado());
        dto.setUsuarioRegistrador(historial.getUsuarioRegistrador());
        
        // Mantener IDs para compatibilidad con creación/actualización
        dto.setIdReceptor(historial.getReceptor().getIdReceptor());
        
        // RESPUESTA ANIDADA: Incluir información completa del receptor
        dto.setReceptor(receptorService.mapToBasicDTO(historial.getReceptor()));
        
        // Agregar el ID de entrega y respuesta anidada si existe
        if (historial.getEntrega() != null) {
            dto.setIdEntrega(historial.getEntrega().getIdEntrega());
            // RESPUESTA ANIDADA: Incluir información completa de la entrega
            dto.setEntrega(entregaService.mapToBasicDTO(historial.getEntrega()));
        }

        // 🔄 ADAPTADOR: Convertir UUID a String para compatibilidad con Apollo
        if (historial.getIdDonacion() != null) {
            dto.setIdDonacion(historial.getIdDonacion().toString());
        }
        
        return dto;
    }

    /**
     * Método auxiliar BÁSICO para mapear entidad a DTO (para respuestas anidadas de otros servicios)
     */
    public HistorialReceptorDTO mapToBasicDTO(HistorialReceptor historial) {
        HistorialReceptorDTO dto = new HistorialReceptorDTO();
        dto.setIdHistorial(historial.getIdHistorial());
        dto.setFechaEntrega(historial.getFechaEntrega());
        dto.setObservacion(historial.getObservacion());
        dto.setFechaRegistro(historial.getFechaRegistro());
        dto.setFechaActualizacion(historial.getFechaActualizacion());
        dto.setTipoEvento(historial.getTipoEvento());
        dto.setEstado(historial.getEstado());
        dto.setDetalleAdicional(historial.getDetalleAdicional());
        dto.setCantidadEntregada(historial.getCantidadEntregada());
        dto.setUnidad(historial.getUnidad());
        dto.setValorEstimado(historial.getValorEstimado());
        dto.setUsuarioRegistrador(historial.getUsuarioRegistrador());
        
        // Solo incluir IDs básicos, NO respuestas anidadas para evitar recursión
        if (historial.getReceptor() != null) {
            dto.setIdReceptor(historial.getReceptor().getIdReceptor());
        }
        if (historial.getEntrega() != null) {
            dto.setIdEntrega(historial.getEntrega().getIdEntrega());
        }

        // 🔄 ADAPTADOR: Convertir UUID a String para compatibilidad con Apollo
        if (historial.getIdDonacion() != null) {
            dto.setIdDonacion(historial.getIdDonacion().toString());
        }
        
        return dto;
    }
}
