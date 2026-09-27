package com.coffeemanager.coffeemanager.dto;

import java.time.LocalDateTime;

public class MovimientoInventarioResponseDTO {

    private Integer idMovimiento;
    private Integer idProducto;
    private Integer idUsuario;
    private String tipoMovimiento;
    private Integer cantidad;
    private LocalDateTime fecha;
    private String motivo;
    private String referencia;

    public MovimientoInventarioResponseDTO() {
    }

    public MovimientoInventarioResponseDTO(
            Integer idMovimiento,
            Integer idProducto,
            Integer idUsuario,
            String tipoMovimiento,
            Integer cantidad,
            LocalDateTime fecha,
            String motivo,
            String referencia) {

        this.idMovimiento = idMovimiento;
        this.idProducto = idProducto;
        this.idUsuario = idUsuario;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.motivo = motivo;
        this.referencia = referencia;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
}