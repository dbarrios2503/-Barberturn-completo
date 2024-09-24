/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.repository;

import com.modelo.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservaRepository extends JpaRepository<Reserva, Long> {
   
}