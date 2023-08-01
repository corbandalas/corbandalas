package com.corbandalas.data.mapper;

import com.corbandalas.data.model.Post;
import com.corbandalas.domain.model.PostDTO;
import com.corbandalas.domain.model.Tag;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-26T21:16:47+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    private final CustomerMapper customerMapper;

    @Autowired
    public PostMapperImpl(CustomerMapper customerMapper) {

        this.customerMapper = customerMapper;
    }

    @Override
    public PostDTO toDto(Post entity) {
        if ( entity == null ) {
            return null;
        }

        PostDTO.PostDTOBuilder<?, ?> postDTO = PostDTO.builder();

        postDTO.uuid( entity.getUuid() );
        postDTO.title( entity.getTitle() );
        postDTO.text( entity.getText() );
        postDTO.date( entity.getDate() );
        postDTO.customer( customerMapper.toDto( entity.getCustomer() ) );
        Set<Tag> set = entity.getTags();
        if ( set != null ) {
            postDTO.tags( new LinkedHashSet<Tag>( set ) );
        }

        return postDTO.build();
    }

    @Override
    public Post toEntity(PostDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Post.PostBuilder<?, ?> post = Post.builder();

        post.uuid( dto.getUuid() );
        post.title( dto.getTitle() );
        post.text( dto.getText() );
        post.customer( customerMapper.toEntity( dto.getCustomer() ) );
        post.date( dto.getDate() );
        Set<Tag> set = dto.getTags();
        if ( set != null ) {
            post.tags( new LinkedHashSet<Tag>( set ) );
        }

        return post.build();
    }

    @Override
    public List<PostDTO> toDto(List<Post> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PostDTO> list = new ArrayList<PostDTO>( entities.size() );
        for ( Post post : entities ) {
            list.add( toDto( post ) );
        }

        return list;
    }

    @Override
    public List<Post> toEntity(List<PostDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Post> list = new ArrayList<Post>( dtos.size() );
        for ( PostDTO postDTO : dtos ) {
            list.add( toEntity( postDTO ) );
        }

        return list;
    }
}
