package com.corbandalas.data.mapper;

import com.corbandalas.data.model.Post;
import com.corbandalas.domain.model.PostDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = CustomerMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PostMapper extends EntityMapper<PostDTO, Post> {


}
