
package com.controller;


import com.Services.ClienteServices;
import com.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private ClienteServices clienteServices;

    @PostMapping ("/post")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteServices.create(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteServices.getAllClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        return clienteServices.findById(id)
                .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        return clienteServices.findById(id) // Buscar el cliente por ID
                .map(cliente -> {
                    clienteServices.delete(cliente); // Eliminar cliente si existe
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); // Respuesta 204 si se elimina correctamente
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Respuesta 404 si no se encuentra el cliente
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente updatedCliente) {
        try {
            Cliente clienteActualizado = clienteServices.updateCliente(id, updatedCliente);
            return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
