package com.corbandalas.web.views.customer.info;

import com.corbandalas.domain.model.PostDTO;
import com.corbandalas.domain.model.Tag;
import com.corbandalas.domain.ports.api.PostServicePort;
import com.corbandalas.web.views.customer.BasePostView;

import java.util.Optional;

public abstract class InfoPageView extends BasePostView {

    public InfoPageView(PostServicePort postServicePort) {
        super(postServicePort, false, false);
    }

    @Override
    public Optional<String> getParamPostName() {
        return Optional.empty();
    }

    public abstract Tag getTag();

    @Override
    protected Optional<PostDTO> getPost(Optional<String> postID) {

        return getPostServicePort().retrievePostsByTag(getTag(), 0, 10).findFirst();
    }
}
