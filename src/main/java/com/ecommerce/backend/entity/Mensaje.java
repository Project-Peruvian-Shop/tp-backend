package com.ecommerce.backend.entity;

import com.ecommerce.backend.enums.MensajeEstadoEnum;
import com.ecommerce.backend.enums.MensajeMedioRespuestaEnum;
import com.ecommerce.backend.enums.MensajeTipoDocumentoEnum;
import com.ecommerce.backend.enums.MensajeTipoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "mensaje")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 200)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento")
    private MensajeTipoDocumentoEnum tipo_documento;

    @Column(name = "documento", length = 30)
    private String documento;

    @Column(name = "telefono", length = 25)
    private String telefono;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "contenido", nullable = false)
    private String contenido;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private MensajeTipoEnum tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private MensajeEstadoEnum estado;

    @Enumerated(EnumType.STRING)
    @Column(name = "medio_respuesta", length = 20)
    private MensajeMedioRespuestaEnum medio_respuesta;

    @Column(name = "creacion", nullable = false)
    private LocalDateTime creacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
}
