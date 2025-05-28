/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uis.edu.tribuna360.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AbonoUsuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AbonoUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_abono_usuario")
    private Integer idAbonoUsuario;

    @ManyToOne
    @JoinColumn(name = "id_abono", nullable = false)
    private Abono abono;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDate fechaCompra;

    @Column(name = "estado",nullable = false, length=50)
    private String estado;

    @Column(name = "id_pago",nullable = true, length=50)
    private String idPago;

    @Column(name = "fecha_pago", nullable = true)
    private LocalDate fechaPago;

    @Column(name = "metodo_pago",nullable = true, length=50)
    private String metodoPago;

    @Column(name = "monto_pagado", nullable = true)
    private BigDecimal montoPagado;

    @Column(name = "estado_pago",nullable = false, length=50)
    private String estadoPago;
}
