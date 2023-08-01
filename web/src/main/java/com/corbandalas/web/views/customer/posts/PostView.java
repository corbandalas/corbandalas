package com.corbandalas.web.views.customer.posts;

import com.corbandalas.domain.ports.api.PostServicePort;
import com.corbandalas.web.component.StyledText;
import com.corbandalas.web.views.TagBadgeHolder;
import com.corbandalas.web.views.customer.MainLayout;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@PageTitle("Посты")
@Route(value = "post/:postID", layout = MainLayout.class)
@AnonymousAllowed
@Slf4j
public class PostView extends VerticalLayout implements BeforeEnterObserver {

    private String postID;
    private PostServicePort postServicePort;

    private OrderedList imageContainer;

    public PostView(PostServicePort postServicePort) {
        this.postServicePort = postServicePort;

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        postID = event.getRouteParameters().get("postID").
                orElse("");

        setSpacing(true);

        postServicePort.retrieve(UUID.fromString(postID)).ifPresent(post -> {

            H2 dateSubtitle = new H2();
            dateSubtitle.addClassNames("text-s", "text-secondary");
            dateSubtitle.setText(post.getFormattedDate());

            add(dateSubtitle);

//            if (topic.getPostRelease() != null) {
//                Span subtitle = new Span();
//                subtitle.addClassNames("text-s", "text-secondary");
//                subtitle.setText(topic.getPostRelease().getDescription());
//
//                add(subtitle);
//            }


            StyledText description = new StyledText(post.getText());
            description.addClassName("my-m");
//            description.setWhiteSpace(HasText.WhiteSpace.PRE_LINE);

            add(description);


           constructUI();

//            if (topic.getPhotos() != null && topic.getPhotos().size() > 0) {
//                topic.getPhotos().stream().forEach(photo -> imageContainer.add(new TopicPhotoCard(photo)));
//            }

//            if (topic.getPhotos() != null && topic.getPhotos().size() > 0) {
//
//
//                List<ImageSlide> imageSlideList = topic.getPhotos().stream().map(photo ->
//                        new ImageSlide(photo)).collect(Collectors.toList());
//
//                Splide slider = new Splide(imageSlideList);
//
//                slider.setId("images-slider-demo");
//                slider.setWidth("450px");
//                slider.setHeight("300px");
//                slider.getElement().getStyle().set("margin", "auto");
//
//                add(slider);
//            }


//            add(imageContainer);


            if (post.getTags() != null) {
                HorizontalLayout horizontalLayout = new HorizontalLayout();

                post.getTags().forEach(tag -> {
                    Span badge = new Span();
                    badge.getElement().getThemeList().add(TagBadgeHolder.tagsBadgeThemeNames.get(tag));
//                badge.getElement().setAttribute("theme", TagBadgeHolder.tagsBadgeThemeNames.get(tag));
//                badge.setText(tag.name());

//                        badge.addClickListener (event -> {
//
//                            event.getSource().getUI().ifPresent(ui -> ui.navigate(PostView.class,
//                                    new RouteParameters("postID", postDTO.uuid.toString())));
//
//
//                        });

                    badge.setText(tag.name());

                    badge.addClickListener(e ->
                    {

                        e.getSource().getUI().ifPresent(ui -> ui.navigate(PostListTagView.class, tag.name()));

                    });
//                badge.add(new Anchor("/posttag/" + tag.name(), tag.name()));

                    horizontalLayout.add(badge);
                });

                add(horizontalLayout);
            }

        });

    }

    private void constructUI() {
        addClassNames("topics-view", "max-w-screen-lg", "mx-auto", "pb-l", "px-l");

        HorizontalLayout container = new HorizontalLayout();
        container.addClassNames("items-center", "justify-between");

        imageContainer = new OrderedList();
        imageContainer.addClassNames("gap-m", "grid", "list-none", "m-0", "p-0");

        add(container, imageContainer);

    }


}
