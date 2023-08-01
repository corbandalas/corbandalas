package com.corbandalas.service;

import com.corbandalas.domain.model.PostDTO;
import com.corbandalas.domain.model.Tag;
import com.corbandalas.domain.ports.api.PasswordEncoder;
import com.corbandalas.domain.ports.api.PostServicePort;
import com.corbandalas.domain.ports.spi.PostPersistencePort;

import java.time.LocalDate;
import java.util.stream.Stream;


public class PostServiceImpl extends CrudServiceImpl<PostDTO> implements PostServicePort {

    private final PostPersistencePort postPersistencePort;

    public PostServiceImpl(PostPersistencePort postPersistencePort) {
        super(postPersistencePort);
        this.postPersistencePort = postPersistencePort;
    }

    @Override
    public Stream<PostDTO> getPostsByDate(LocalDate startDate, LocalDate endDate, int page, int pageSize) {
        return postPersistencePort.retrieveByDateIsBetween(startDate, endDate, page, pageSize).stream();
    }

    @Override
    public Stream<PostDTO> getPostsByTag(Tag tag, int page, int pageSize, String... sortFieldName) {
        return postPersistencePort.retrieveAllByTag(tag, page, pageSize, sortFieldName).stream();
    }


}
