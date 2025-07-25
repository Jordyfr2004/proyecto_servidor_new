package com.donacion.modulo2.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donacion.modulo2.dto.ReceptorDTO;
import com.donacion.modulo2.entity.Receptor;
import com.donacion.modulo2.exception.RecursoNoEncontradoException;
import com.donacion.modulo2.repository.ReceptorRepository;
import com.donacion.modulo2.service.ReceptorService;

import jakarta.transaction.Transactional;

/**
 * ReceptorServiceImpl:
 * Implementación de la lógica de negocio para la entidad Receptor.
 */
@Service
public class ReceptorServiceImpl implements ReceptorService {

    @Autowired
    private ReceptorRepository receptorRepository;

    /**
     * Crea un nuevo receptor con validación de correo único.
     *
     * @param dto Objeto DTO con los datos del receptor.
     * @return ReceptorDTO creado.
     */
    @Override
    @Transactional
    public ReceptorDTO crearReceptor(ReceptorDTO dto) {
        // Validar que el correo no exista
        if (receptorRepository.findByCorreo(dto.getCorreo()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un receptor registrado con el correo: " + dto.getCorreo());
        }

        // Validar que la cédula no exista
        if (receptorRepository.findByCedula(dto.getCedula()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un receptor registrado con la cédula: " + dto.getCedula());
        }

        Receptor nuevoReceptor = mapToEntity(dto);
        Receptor receptorGuardado = receptorRepository.save(nuevoReceptor);
        return mapToDTO(receptorGuardado);
    }

    /**
     * Obtiene una lista de todos los receptores registrados.
     *
     * @return Lista de ReceptorDTO.
     */
    @Override
    public List<ReceptorDTO> obtenerTodos() {
        return receptorRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca un receptor por su ID.
     *
     * @param id UUID del receptor.
     * @return ReceptorDTO si se encuentra.
     * @throws RecursoNoEncontradoException si el receptor no existe.
     */
    @Override
    public ReceptorDTO obtenerPorId(UUID id) {
        Receptor receptor = receptorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el receptor con ID: " + id));
        return mapToDTO(receptor);
    }

    /**
     * Actualiza los datos de un receptor existente.
     *
     * @param id UUID del receptor.
     * @param dto Datos nuevos del receptor.
     * @return ReceptorDTO actualizado.
     * @throws RecursoNoEncontradoException si el receptor no existe.
     */
    @Override
    @Transactional
    public ReceptorDTO actualizarReceptor(UUID id, ReceptorDTO dto) {
        Receptor receptorExistente = receptorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró el receptor con ID: " + id));

        receptorExistente.setNombre(dto.getNombre());
        receptorExistente.setCedula(dto.getCedula());
        receptorExistente.setTelefono(dto.getTelefono());
        receptorExistente.setCorreo(dto.getCorreo());

        Receptor actualizado = receptorRepository.save(receptorExistente);
        return mapToDTO(actualizado);
    }

    /**
     * Elimina un receptor por su ID.
     *
     * @param id UUID del receptor.
     * @throws RecursoNoEncontradoException si no existe.
     */
    @Override
    @Transactional
    public void eliminarReceptor(UUID id) {
        if (!receptorRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("No se encontró el receptor con ID: " + id);
        }
        receptorRepository.deleteById(id);
    }

    /**
     * Verifica si ya existe un receptor con el correo especificado.
     *
     * @param correo el correo a verificar.
     * @return true si existe, false si no.
     */
    @Override
    public boolean existePorCorreo(String correo) {
        return receptorRepository.findByCorreo(correo).isPresent();
    }

    /**
     * Verifica si una cédula ya está registrada.
     *
     * @param cedula cédula a verificar.
     * @return true si existe, false si no.
     */
    @Override
    public boolean existePorCedula(String cedula) {
        return receptorRepository.findByCedula(cedula).isPresent();
    }

    /**
     * Busca un receptor por su correo electrónico.
     *
     * @param correo correo del receptor.
     * @return ReceptorDTO si existe.
     */
    @Override
    public ReceptorDTO obtenerPorCorreo(String correo) {
        Receptor receptor = receptorRepository.findByCorreo(correo)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró receptor con correo: " + correo));
        return mapToDTO(receptor);
    }

    /**
     * Busca un receptor por su cédula.
     *
     * @param cedula cédula del receptor.
     * @return ReceptorDTO si existe.
     */
    @Override
    public ReceptorDTO obtenerPorCedula(String cedula) {
        Receptor receptor = receptorRepository.findByCedula(cedula)
                .orElseThrow(() -> new RecursoNoEncontradoException("No se encontró receptor con cédula: " + cedula));
        return mapToDTO(receptor);
    }

    // ====================================================
    // Métodos auxiliares para convertir entre DTO y entidad
    // ====================================================

    /**
     * Método público para crear un DTO básico sin relaciones (para respuestas anidadas)
     */
    public ReceptorDTO mapToBasicDTO(Receptor receptor) {
        ReceptorDTO dto = new ReceptorDTO();
        dto.setIdReceptor(receptor.getIdReceptor());
        dto.setNombre(receptor.getNombre());
        dto.setCedula(receptor.getCedula());
        dto.setTelefono(receptor.getTelefono());
        dto.setDireccion(""); // Temporal - evitar problemas con relaciones LAZY
        dto.setCorreo(receptor.getCorreo());
        
        // No incluir relaciones para evitar problemas de serialización
        dto.setIdsDirecciones(null);
        dto.setIdsSolicitudes(null);
        dto.setIdsHistorial(null);
        
        return dto;
    }

    private ReceptorDTO mapToDTO(Receptor receptor) {
        ReceptorDTO dto = new ReceptorDTO();
        dto.setIdReceptor(receptor.getIdReceptor());
        dto.setNombre(receptor.getNombre());
        dto.setCedula(receptor.getCedula());
        dto.setTelefono(receptor.getTelefono());
        dto.setDireccion(""); // Temporal - evitar problemas con relaciones LAZY
        dto.setCorreo(receptor.getCorreo());
        
        // Mapear las relaciones OneToMany como null temporalmente para evitar problemas LAZY
        dto.setIdsDirecciones(null);
        dto.setIdsSolicitudes(null);
        dto.setIdsHistorial(null);
        
        return dto;
    }

    private Receptor mapToEntity(ReceptorDTO dto) {
        Receptor receptor = new Receptor();
        receptor.setIdReceptor(dto.getIdReceptor());
        receptor.setNombre(dto.getNombre());
        receptor.setCedula(dto.getCedula());
        receptor.setTelefono(dto.getTelefono());
        // TODO: Crear entidad Direccion separada si se proporciona dirección
        // Por ahora no creamos la dirección automáticamente
        receptor.setCorreo(dto.getCorreo());
        return receptor;
    }
}
