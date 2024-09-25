package com.controller;



import com.Services.LocalService;
import com.modelo.Empleado;
import com.modelo.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class LocalController {

    @Autowired

    private LocalService LocalService;

    public void EmpleadoController(LocalService LocalService) {
        this.LocalService = LocalService;
    }

    // Crear o actualizar un empleado
    @PostMapping("/Post")
    public ResponseEntity<Local> createEmpleado(@RequestBody Local local) {
        Local nuevoLocal = LocalService.saveOrUpdate(local);
        return new ResponseEntity<>(nuevoLocal, HttpStatus.CREATED);
    }

    // Obtener todos los empleados
    @GetMapping
    public ResponseEntity<List<Local>> getAllEmpleados() {
        List<Local> empleados = LocalService.getAllLocal();
        return new ResponseEntity<>(empleados, HttpStatus.OK);
    }

    // Obtener empleado por ID
    @GetMapping("/id")
    public ResponseEntity<Local> getlocalById(@PathVariable int id) {
        return LocalService.findById(id)
                .map(Local -> new ResponseEntity<>(Local, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar empleado por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable int id) {
        return LocalService.findById(id)
                .map(Local -> {
                    LocalService.deleteLocal(Local);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar un Local
    @PutMapping("/{id}")
    public ResponseEntity<Local> updateLocal(@PathVariable int id, @RequestBody Local local) {
        return LocalService.findById(id)
                .map(existingLocal -> {
                    local.setId(existingLocal.getId());
                    Local updateLocal = LocalService.saveOrUpdate(local);
                    return new ResponseEntity<>(updateLocal, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
