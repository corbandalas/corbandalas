package com.corbandalas.service;

import com.corbandalas.domain.model.BaseDTO;
import com.corbandalas.domain.ports.api.CrudServicePort;
import com.corbandalas.domain.ports.spi.CrudPersistencePort;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public abstract class CrudServiceImpl<T extends BaseDTO> implements CrudServicePort<T> {

    private CrudPersistencePort<T> crudPersistencePort;

    public CrudServiceImpl() {
    }

//    @Autowired
    public CrudServiceImpl(CrudPersistencePort<T> crudPersistencePort) {
        this.crudPersistencePort = crudPersistencePort;
    }

//    @Transactional
    @Override
    public T create(T dto) {
        dto.setUuid(UUID.randomUUID());
        return crudPersistencePort.create(dto);
    }

//    @Transactional
    @Override
    public Optional<T> retrieve(UUID uuid) {
        return crudPersistencePort.retrieve(uuid);
    }

//    @Transactional
    @Override
    public T update(T dto) {
        return crudPersistencePort.update(dto);
    }

//    @Transactional
    @Override
    public void delete(UUID uuid) {
        crudPersistencePort.delete(uuid);
    }

//    @Transactional
    @Override
    public List<T> getAll() {
        return crudPersistencePort.getAll();
    }

//    @Transactional
    @Override
    public Stream<T> getAllByPage(int page, int pageSize, String ...sortFieldName) {
        return crudPersistencePort.getAll(page, pageSize, sortFieldName).stream();
    }
}
