
package com.modelo;

import com.modelodto.Localdto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Turno {

    @Id
    private Long id;

    @ManyToOne
    private Empleado empleado;

    @ManyToOne
    private Localdto turnolocal;

    private LocalDateTime fecha;

    private String estado;

}
