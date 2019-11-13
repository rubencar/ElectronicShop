package com.tfg.tiendadeelectronica.DTO;

import java.util.Collection;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class LoginUsuario {

	@NotBlank
    private String nombreUsuario;

    @NotBlank
    private String password;
    
}
