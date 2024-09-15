package br.com.fiap.savvyfix.service;

import java.util.Collection;

public interface ServiceDTO <Entity>{

    Collection<Entity> findAll();

    Entity findById(Long id);

    Entity save(Entity entity);





}
