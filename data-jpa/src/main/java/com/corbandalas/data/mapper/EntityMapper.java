package com.corbandalas.data.mapper;

import com.corbandalas.data.model.BaseEntity;
import com.corbandalas.domain.model.BaseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;

public interface EntityMapper<D extends BaseDTO, E extends BaseEntity> {

    D toDto(E entity);

    E toEntity(D dto);

    List<D> toDto(List<E> entities);

    List<E> toEntity(List<D> dtos);

    default Optional<D> toDto(Optional<E> entity) {

        if (entity.isPresent()) {
            return Optional.of(toDto(entity.get()));
        } else {
            return Optional.empty();
        }
    }
}