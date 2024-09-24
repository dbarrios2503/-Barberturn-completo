/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Services;


import java.util.List;
import java.util.Optional;

import com.modelo.Empleado;
import com.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmpleadoServices {
    ;
    @Autowired
    private EmpleadoRepository empleadoRepository;

    // Crear o actualizar un empleado
    public Empleado saveOrUpdate(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    // Obtener todos los empleados
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll();
    }

    // Obtener empleado por ID
    public Optional<Empleado> findById(int id) {
        return empleadoRepository.findById((long) id);
    }

    // Eliminar un empleado por su ID
    public void deleteEmpleado(Empleado empleado) {
        empleadoRepository.delete(empleado);
    }

}

