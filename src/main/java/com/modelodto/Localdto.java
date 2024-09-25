package com.modelodto;

import aj.org.objectweb.asm.commons.Remapper;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "localdto")
public class Localdto {
    @Id
    private int id;

    private String nombre;
    private String dirrecion;

    private long telefono;



}