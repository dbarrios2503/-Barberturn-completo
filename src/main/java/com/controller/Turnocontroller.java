package com.controller;

import com.Services.TurnoService;
import com.modelo.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turno")

public class Turnocontroller {


    @Autowired

    private final TurnoService turnoservicio ;


    public Turnocontroller(TurnoService turnoservicio  ) {
        this.turnoservicio = turnoservicio;
    }

    // Crear o actualizar un Local
    @PostMapping("/Post")
    public ResponseEntity<Turno> Createturno(@RequestBody Turno turno ){
        Turno nuevoTurno = TurnoService.saveOrUpdate(turno);
        return new ResponseEntity<>(nuevoTurno, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Turno>> getAllturno() {
        List<Turno> turno = turnoservicio.getAllturno();
        return new ResponseEntity<>(turno, HttpStatus.OK);
    }

    // Obtener Local por ID
    @GetMapping("/id")
    public ResponseEntity<Turno> getTurnoById(@PathVariable int id) {
        return turnoservicio.findById(id)
                .map(Turno -> new ResponseEntity<>(Turno, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // Eliminar Turno servicio por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteTurno(@PathVariable int id) {
        return turnoservicio.findById(id)
                .map(Turno -> {
                    turnoservicio.deleteturno(Turno);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar un Turno
    @PutMapping("/{id}")
    public ResponseEntity<Turno> updateTurno(@PathVariable int id, @RequestBody Turno turno) {
        return turnoservicio.findById(id)
                .map(existingTurno -> {
                    turno.setId(existingTurno.getId());
                    Turno updateTurno = turnoservicio.saveOrUpdate(turno);
                    return new ResponseEntity<>(updateTurno, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}


