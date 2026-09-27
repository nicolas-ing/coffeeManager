package com.coffeemanager.coffeemanager.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoResponseDTO {

    private Integer idPedido;
    private Integer idCliente;
    private Integer idUsuario;
    private LocalDateTime fecha;
    private String estado;
    private String observaciones;
    private BigDecimal subtotal;
    private BigDecimal impuesto;
    private BigDecimal total;

    public PedidoResponseDTO() {
    }

    public PedidoResponseDTO(
            Integer idPedido,
            Integer idCliente,
            Integer idUsuario,
            LocalDateTime fecha,
            String estado,
            String observaciones,
            BigDecimal subtotal,
            BigDecimal impuesto,
            BigDecimal total) {

        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.estado = estado;
        this.observaciones = observaciones;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(BigDecimal impuesto) {
        this.impuesto = impuesto;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}