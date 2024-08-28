package br.com.fiap.savvyfix.service;

import java.util.Collection;

public interface ServiceDTO <Entity, Request, Response>{

	Entity toEntity(Request request);

    Response toResponse(Entity entity);

    Collection<Entity> findAll();

    Entity findById(Long id);

    Entity save(Entity entity);





}
