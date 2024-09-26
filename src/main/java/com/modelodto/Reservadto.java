package com.modelodto;
import com.modelo.Cliente;
import com.modelo.Empleado;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor


public class Reservadto {


    private Long id;


    private Empleado empleado;


    private Cliente cliente;

    private String reservalocal;


    private LocalDateTime reservafecha;


    private String reservaestado;
}