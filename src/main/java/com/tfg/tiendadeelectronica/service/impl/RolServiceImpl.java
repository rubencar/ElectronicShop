package com.tfg.tiendadeelectronica.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.tiendadeelectronica.enums.RolNombre;
import com.tfg.tiendadeelectronica.model.Rol;
import com.tfg.tiendadeelectronica.repository.RolRepository;
import com.tfg.tiendadeelectronica.service.RolService;

@Service
@Transactional
public class RolServiceImpl implements RolService{

	@Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }
    
}
