package com.tfg.tiendadeelectronica.DTO;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NuevoUsuario {

    @NotBlank
    private String nombre;
    
    @NotBlank
    private String apellidos;
    
    @NotBlank
    private String dni;
    
    @NotBlank
    private String telefono1;
    
    @NotBlank
    private String telefono2;
    
    @NotBlank
    private String email;

    @NotBlank
    private String nombreUsuario;

    @NotBlank
    private String password;
    
    private Set<String> roles; 
    
}
