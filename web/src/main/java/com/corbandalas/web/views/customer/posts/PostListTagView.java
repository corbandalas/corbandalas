package com.corbandalas.web.views.customer.posts;

import com.corbandalas.domain.model.PostDTO;
import com.corbandalas.domain.model.Tag;
import com.corbandalas.domain.ports.api.PostServicePort;
import com.corbandalas.web.views.customer.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.extern.slf4j.Slf4j;

@PageTitle("Статьи по тегам")
@Route(value = "tags", layout = MainLayout.class)
@RouteAlias(value = "tags", layout = MainLayout.class)
@AnonymousAllowed
@Slf4j
public class PostListTagView extends BasePostListView implements AfterNavigationObserver, HasUrlParameter<String> {

    private Tag selectedTag;

    public PostListTagView(PostServicePort postServicePort) {
        super(postServicePort);

    }


    @Override
    public void afterNavigation(AfterNavigationEvent event) {

        getGrid().setItems(query ->  getPostServicePort().retrievePostsByTag(selectedTag, query.getPage(), query.getPageSize()));
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        selectedTag = Tag.valueOf(parameter);
    }
}