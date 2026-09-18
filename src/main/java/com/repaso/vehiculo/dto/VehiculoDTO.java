package com.repaso.vehiculo.dto;

import java.math.BigDecimal;
import lombok.Data;

/**
 * DTO ENTREGADO POR EL CATEDRATICO - NO MODIFICAR.
 */
@Data
public class VehiculoDTO {
    private Integer idVehiculo;
    private Boolean estado;
    private String placa;
    private String marca;
    private String modelo;
    private String color;
    private BigDecimal precioDia;
}
