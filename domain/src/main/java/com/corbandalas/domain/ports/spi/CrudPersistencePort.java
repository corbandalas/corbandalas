package com.corbandalas.domain.ports.spi;

import com.corbandalas.domain.model.BaseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CrudPersistencePort<T extends BaseDTO> {
    T create(T dto);

    Optional<T> retrieve(UUID uuid);

    T update(T dto);

    void delete(UUID uuid);

    List<T> getAll();

    List<T> getAll(int page, int pageSize, String ...sortFieldName);

}
