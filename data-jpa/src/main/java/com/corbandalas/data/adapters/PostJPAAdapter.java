package com.corbandalas.data.adapters;

import com.corbandalas.data.mapper.PostMapper;
import com.corbandalas.data.repository.PostRepository;
import com.corbandalas.domain.model.PostDTO;
import com.corbandalas.domain.model.Tag;
import com.corbandalas.domain.ports.spi.PostPersistencePort;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@NoArgsConstructor
public class PostJPAAdapter implements PostPersistencePort {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostMapper postMapper;

    @Override
    @Transactional
    @Caching(put = @CachePut(cacheNames = "post", key = "#result.id"),
            evict = {@CacheEvict(cacheNames = "posts", allEntries = true),
                    @CacheEvict(cacheNames = "post_tags", allEntries = true)
            })
    public PostDTO create(PostDTO postDTO) {

        var post = postMapper.toEntity(postDTO);

        return postMapper.toDto(postRepository.save(post));
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "post", key = "#uuid")
    public Optional<PostDTO> retrieve(UUID uuid) {
        return Optional.ofNullable(postMapper.toDto(postRepository.getReferenceById(uuid)));
    }

    @Override
    @Transactional
    @Caching(put = @CachePut(cacheNames = "post", key = "#result.uuid"),
            evict = {@CacheEvict(cacheNames = "posts", allEntries = true),
                    @CacheEvict(cacheNames = "post_tags", allEntries = true)
            })
    public PostDTO update(PostDTO postDTO) {
        var post = postMapper.toEntity(postDTO);

        return postMapper.toDto(postRepository.save(post));
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = "post", key = "#uuid"),
            @CacheEvict(cacheNames = "posts", allEntries = true),
            @CacheEvict(cacheNames = "post_tags", allEntries = true)
    }) public void delete(UUID uuid) {

    }

    @Override
    @Transactional
    @Cacheable
    public List<PostDTO> retrieveAll() {
        return postRepository.findAll().stream().map(postMapper::toDto).toList();
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "posts")
    public List<PostDTO> retrieveAll(int page, int pageSize, String... sortFieldName) {
        return postMapper.toDto(postRepository.findAllPosts(PageRequest.of(page, pageSize, (sortFieldName != null && sortFieldName.length > 0) ? Sort.by(sortFieldName).descending() : Sort.unsorted())));
    }


    @Override
    @Transactional
    public List<PostDTO> retrieveByDateIsBetween(LocalDate startDate, LocalDate endDate, int page, int pageSize) {
        return postMapper.toDto(postRepository.findByDateIsBetween(startDate, endDate, PageRequest.of(page, pageSize)));
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "posts_tags", key = "#tag")
    public List<PostDTO> retrieveAllByTag(Tag tag, int page, int pageSize, String... sortFieldName) {
        return postMapper.toDto(postRepository.findAllPostsByTags(tag, PageRequest.of(page, pageSize, (sortFieldName != null && sortFieldName.length > 0) ? Sort.by(sortFieldName).descending() : Sort.unsorted())));
    }

}
