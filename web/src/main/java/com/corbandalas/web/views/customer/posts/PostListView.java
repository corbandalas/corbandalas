package com.corbandalas.web.views.customer.posts;

import com.corbandalas.domain.model.PostDTO;
import com.corbandalas.domain.model.Tag;
import com.corbandalas.domain.ports.api.PostServicePort;
import com.corbandalas.web.views.customer.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.extern.slf4j.Slf4j;

@PageTitle("")
@Route(value = "posts", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
@Slf4j
public class PostListView extends BasePostListView implements AfterNavigationObserver, BeforeEnterObserver {


    private String selectedTag;

    public PostListView(PostServicePort postServicePort) {
        super(postServicePort);

    }


    @Override
    public void afterNavigation(AfterNavigationEvent event) {

        getGrid().setItems(query ->  getPostServicePort().retrieveAllByPage(query.getPage(), query.getPageSize(), "date").filter(p -> !p.getTags().contains(Tag.ABOUT) && !p.getTags().contains(Tag.BIO)));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        selectedTag = event.getRouteParameters().get("tag").
                orElse("");
        log.info(selectedTag);
    }


}