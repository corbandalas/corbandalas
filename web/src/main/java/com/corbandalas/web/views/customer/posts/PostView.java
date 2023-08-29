package com.corbandalas.web.views.customer.posts;

import com.corbandalas.domain.model.PostDTO;
import com.corbandalas.domain.ports.api.PostServicePort;
import com.corbandalas.web.views.customer.BasePostView;
import com.corbandalas.web.views.customer.MainLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@PageTitle("Статьи")
@Route(value = "post/:postID", layout = MainLayout.class)
@AnonymousAllowed
@Slf4j
public class PostView extends BasePostView {

    public PostView(PostServicePort postServicePort) {
        super(postServicePort);
    }

    @Override
    public Optional<String> getParamPostName() {
        return Optional.of("postID");
    }

    @Override
    public Optional<PostDTO> getPost(Optional<String> postID) {

        return getPostServicePort().retrieve(UUID.fromString(postID.orElseThrow()));

    }


}
