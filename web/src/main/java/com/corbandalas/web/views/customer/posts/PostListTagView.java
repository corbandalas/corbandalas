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

@PageTitle("Посты по тегам")
@Route(value = "tags", layout = MainLayout.class)
@RouteAlias(value = "tags", layout = MainLayout.class)
@AnonymousAllowed
@Slf4j
public class PostListTagView extends BasePostView implements AfterNavigationObserver, HasUrlParameter<String> {

    public static final int TOPIC_TRUNK_SIZE = 250;
    private PostServicePort postServicePort;

    private Grid<PostDTO> grid = new Grid<>();
    private Tag selectedTag;

    public PostListTagView(PostServicePort postServicePort) {
        this.postServicePort = postServicePort;
        addClassName("card-list-view");
        setSizeFull();
        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(postDTO -> createCard(postDTO));
//        grid.setPageSize(20);
//        grid.addItemClickListener(event -> {
//            PostDTO postDTO = event.getItem();

//            event.getColumn().getUI().ifPresent(ui -> ui.navigate(PostView.class,
//                    new RouteParameters("postID", postDTO.uuid.toString())));


//        });
        add(grid);

    }



    @Override
    public void afterNavigation(AfterNavigationEvent event) {

        grid.setItems(query ->  postServicePort.getPostsByTag(selectedTag, query.getPage(), query.getPageSize()));
    }

    @Override
    public void setParameter(BeforeEvent event, String parameter) {
        selectedTag = Tag.valueOf(parameter);
    }
}