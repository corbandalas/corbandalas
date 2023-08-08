package com.corbandalas.domain.ports.api;

import com.corbandalas.domain.model.BaseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface CrudServicePort<T extends BaseDTO> {
    T create(T dto);
    Optional<T> retrieve(UUID uuid);
    T update(T dto);
    void delete(UUID uuid);
    List<T> retrieveAll();
    Stream<T> retrieveAllByPage(int page, int pageSize, String ...sortFieldName);
}
