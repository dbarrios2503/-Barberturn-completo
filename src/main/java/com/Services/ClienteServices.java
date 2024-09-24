
package com.Services;

import com.modelo.Cliente;
import com.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ClienteServices {
     @Autowired
     private ClienteRepository clienteRepository;

     // Método para guardar un cliente
     public Cliente create(Cliente cliente) {
          // Guardamos el cliente en la base de datos y lo devolvemos
          return clienteRepository.save(cliente);
     }

     // Lista
     public List<Cliente> getAllClientes() {
          return clienteRepository.findAll();
     }

     public void delete(Cliente cliente) {
          clienteRepository.delete(cliente);
     }

     public Optional<Cliente> findById(Long id) {
          return clienteRepository.findById(id);
     }

     public Cliente updateCliente(Long id, Cliente updatedCliente) {
          return clienteRepository.findById(id).map(cliente -> {
               cliente.setNombre(updatedCliente.getNombre());
               cliente.setApellido(updatedCliente.getApellido());
               cliente.setTelefono(updatedCliente.getTelefono());
               cliente.setEmail(updatedCliente.getEmail());
               cliente.setContrsena(updatedCliente.getContrsena());
               return clienteRepository.save(cliente); // Guardar cliente actualizado
          }).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
     }


}