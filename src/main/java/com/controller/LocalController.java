package com.controller;

import com.Services.LocalService;
import com.modelo.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/local")
public class LocalController {

    @Autowired

    private final LocalService LocalService;

    public LocalController(LocalService localService) {
        this.LocalService = localService;
    }

    // Crear o actualizar un Local
    @PostMapping ("/Post")
    public ResponseEntity<Local> createEmpleado(@RequestBody Local local) {
        Local nuevolocal = LocalService.saveOrUpdate(local);
        return new ResponseEntity<>(nuevolocal, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Local>> getAlllocal() {
        List<Local> Local = LocalService.getAllLocal();
        return new ResponseEntity<>(Local, HttpStatus.OK);
    }

    // Obtener Local por ID
    @GetMapping("/id")
    public ResponseEntity<Local> getEmpleadoById(@PathVariable int id) {
        return LocalService.findById(id)
                .map(Local -> new ResponseEntity<>(Local, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    // Eliminar empleado por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> Deletelocal(@PathVariable int id) {
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
                    local.setIdlocal(existingLocal.getIdlocal());
                    Local updateLocal = LocalService.saveOrUpdate(local);
                    return new ResponseEntity<>(updateLocal, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
