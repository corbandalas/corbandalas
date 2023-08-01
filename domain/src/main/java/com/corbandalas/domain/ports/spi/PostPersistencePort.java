package com.corbandalas.domain.ports.spi;

import com.corbandalas.domain.model.PostDTO;
import com.corbandalas.domain.model.Tag;

import java.time.LocalDate;
import java.util.List;

public interface PostPersistencePort extends CrudPersistencePort<PostDTO> {
    List<PostDTO> retrieveByDateIsBetween(LocalDate startDate, LocalDate endDate, int page, int pageSize);
    List<PostDTO> retrieveAllByTag(Tag tag, int page, int pageSize, String ...sortFieldName);
}
