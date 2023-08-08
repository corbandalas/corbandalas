package com.corbandalas.domain.ports.api;

import com.corbandalas.domain.model.PostDTO;
import com.corbandalas.domain.model.Tag;

import java.time.LocalDate;
import java.util.stream.Stream;

public interface PostServicePort extends CrudServicePort<PostDTO> {
    Stream<PostDTO> retrievePostsByDate(LocalDate startDate, LocalDate endDate, int page, int pageSize);
    Stream<PostDTO> retrievePostsByTag(Tag tag, int page, int pageSize, String ...sortFieldName);

}
