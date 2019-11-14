package com.tfg.tiendadeelectronica.DTO;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginUsuario {

	@NotBlank
    private String nombreUsuario;

    @NotBlank
    private String password;
    
}
