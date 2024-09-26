package com.Services;

import com.modelo.Local;
import com.modelo.Turno;
import com.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LocalService {


    @Autowired
    private static LocalRepository localRepository;

    // Crear o actualizar un empleado
    public static Local saveOrUpdate(Local local) {
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
    public Local updateLocal(Long id, Local updateLocal) {
        return localRepository.findById(id).map(Local -> {
            Local.setIdlocal(updateLocal.getIdlocal());
            Local.setNombre(updateLocal.getNombre());
            Local.setTelefono(updateLocal.getTelefono());
            Local.setDirrecion(updateLocal.getDirrecion());
            return localRepository.save(Local); // Guardar cliente actualizado
        }).orElseThrow(() -> new RuntimeException("Local no encontrado"));
    }


}
