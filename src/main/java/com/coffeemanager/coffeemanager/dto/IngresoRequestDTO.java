package com.coffeemanager.coffeemanager.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class IngresoRequestDTO {

    private Integer idVenta;

    @NotNull(message = "El usuario es obligatorio")
    private Integer idUsuario;

    private LocalDateTime fecha;

    @NotBlank(message = "El concepto es obligatorio")
    @Size(
            max = 255,
            message = "El concepto no puede superar los 255 caracteres"
    )
    private String concepto;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "El monto no puede ser negativo"
    )
    private BigDecimal monto;

    @NotBlank(message = "El método de pago es obligatorio")
    @Size(
            max = 30,
            message = "El método de pago no puede superar los 30 caracteres"
    )
    private String metodoPago;

    @NotBlank(message = "El estado es obligatorio")
    @Size(
            max = 30,
            message = "El estado no puede superar los 30 caracteres"
    )
    private String estado;

    public IngresoRequestDTO() {
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}