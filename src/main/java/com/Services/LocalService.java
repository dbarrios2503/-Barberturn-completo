package com.Services;

import com.modelo.Empleado;
import com.modelo.Local;
import com.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class Servicelocal {


    @Autowired
    private LocalRepository localRepository;

    // Crear o actualizar un empleado
    public Local saveOrUpdate(Local local) {
        return localRepository.save(local);
    }

    // Obtener todos los empleados
    public List<Local> getAllLocal() {
        return localRepository.findAll();
    }

    // Obtener empleado por ID
    public Optional<Local> findById(int id) {
        return localRepository.findById((long) id);
    }

    // Eliminar un empleado por su ID
    public void deleteLocal(Local local) {
        localRepository.delete(local);
    }


}
