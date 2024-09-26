
package com.modelodto;

import com.modelo.Empleado;
import com.modelo.Local;
import com.modelodto.Localdto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor

public class Turnodto {


    private Long id;


    private Empleado empleado;

    private Local turnolocal;

    private LocalDateTime fecha;

    private String estado;

}