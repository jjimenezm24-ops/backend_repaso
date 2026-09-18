package com.repaso.vehiculo.service;

import com.repaso.vehiculo.dto.VehiculoDTO;
import com.repaso.vehiculo.entity.Vehiculo;
import com.repaso.vehiculo.repository.VehiculoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    // METODO DE EJEMPLO YA RESUELTO
    public List<VehiculoDTO> findAll() {
        return vehiculoRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // TODO 1 - MOSTRAR
    public List<VehiculoDTO> mostrarActivos() {
        return vehiculoRepository.findByEstadoTrueOrderByIdVehiculoDesc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // TODO 2 - GUARDAR
    public VehiculoDTO crearVehiculo(VehiculoDTO dto) {
        Vehiculo vehiculo = convertToEntity(dto);
        vehiculo.setEstado(true);
        Vehiculo guardado = vehiculoRepository.save(vehiculo);
        return convertToDTO(guardado);
    }

    // TODO 3 - MODIFICAR
    public VehiculoDTO modificarVehiculo(Integer idVehiculo, VehiculoDTO dto) {
        Vehiculo v = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new RuntimeException("El vehiculo no existe con id " + idVehiculo));

        v.setPlaca(dto.getPlaca());
        v.setMarca(dto.getMarca());
        v.setModelo(dto.getModelo());
        v.setColor(dto.getColor());
        v.setPrecioDia(dto.getPrecioDia());

        Vehiculo actualizado = vehiculoRepository.save(v);
        return convertToDTO(actualizado);
    }

    // TODO 4 - ANULAR (borrado logico)
    public VehiculoDTO anularVehiculo(Integer idVehiculo) {
        Vehiculo v = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new RuntimeException("El vehiculo no existe con id " + idVehiculo));

        v.setEstado(false);
        Vehiculo anulado = vehiculoRepository.save(v);
        return convertToDTO(anulado);
    }

    // METODOS DE CONVERSION YA RESUELTOS - NO MODIFICAR
    private VehiculoDTO convertToDTO(Vehiculo v) {
        VehiculoDTO dto = new VehiculoDTO();
        dto.setIdVehiculo(v.getIdVehiculo());
        dto.setEstado(v.getEstado());
        dto.setPlaca(v.getPlaca());
        dto.setMarca(v.getMarca());
        dto.setModelo(v.getModelo());
        dto.setColor(v.getColor());
        dto.setPrecioDia(v.getPrecioDia());
        return dto;
    }

    private Vehiculo convertToEntity(VehiculoDTO dto) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlaca(dto.getPlaca());
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setColor(dto.getColor());
        vehiculo.setPrecioDia(dto.getPrecioDia());
        vehiculo.setEstado(true);
        return vehiculo;
    }

}