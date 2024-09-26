package com.Services;

import com.modelo.Local;
import com.modelo.Turno;
import com.repository.LocalRepository;
import com.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    public static Turno saveOrUpdate(Turno turno) {
        return turno;
    }

    // Crear o actualizar un turno

    // Obtener todos los empleados
    public List<Turno> getAllturno() {
        return turnoRepository.findAll();
    }

    // Obtener turno por ID
    public Optional<Turno> findById(int id) {
        return turnoRepository.findById((long) id);
    }

    // Eliminar un turno por su ID
    public void deleteturno(Turno turno) {
        turnoRepository.delete( turno);
    }
    public Local updateturno(Long id, Turno updateturno) {
        return turnoRepository.findById(id).map(turno -> {
            turno.setId( updateturno.getId());
            turno.setEmpleado(updateturno.getEmpleado());
            turno.setLocal(updateturno.getLocal());
            turno.setFecha(updateturno.getFecha());
            turno.setEstado(updateturno.getEstado());
            return turnoRepository.save(turno); // Guardar cliente actualizado
        }).orElseThrow(() -> new RuntimeException("Local no encontrado")).getLocal();
    }

}
