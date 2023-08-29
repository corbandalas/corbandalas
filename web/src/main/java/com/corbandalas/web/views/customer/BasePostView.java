package com.corbandalas.web.views.customer;

import com.corbandalas.domain.model.PostDTO;
import com.corbandalas.domain.ports.api.PostServicePort;
import com.corbandalas.web.views.TagBadgeHolder;
import com.corbandalas.web.views.customer.posts.PostListTagView;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.wontlost.ckeditor.Constants;
import com.wontlost.ckeditor.VaadinCKEditor;
import com.wontlost.ckeditor.VaadinCKEditorBuilder;

import java.util.Optional;

public abstract class BasePostView extends VerticalLayout implements BeforeEnterObserver {

    private PostServicePort postServicePort;

    private OrderedList imageContainer;

    private boolean needShowDate;
    private boolean needShowTags;

    public BasePostView(PostServicePort postServicePort) {
        this(postServicePort, true, true);
    }

    public BasePostView(PostServicePort postServicePort, boolean needShowDate, boolean needShowTags) {
        this.postServicePort = postServicePort;
        this.needShowDate = needShowDate;
        this.needShowTags = needShowTags;
    }

    public abstract Optional<String> getParamPostName();

    protected abstract Optional<PostDTO> getPost(Optional<String> postID);

    private Optional<String> obtainParamsFromRoute(BeforeEnterEvent event) {

        return event.getRouteParameters().get(getParamPostName().orElse(""));
    }

    @Override
    public final void beforeEnter(BeforeEnterEvent event) {
        setSpacing(true);

        this.removeAll();

        Optional<String> paramsFromRoute = obtainParamsFromRoute(event);

        getPost(paramsFromRoute).ifPresent(post -> {

            H2 subtitle = new H2();
            subtitle.addClassNames("text-s", "text-secondary");
            subtitle.setText(post.getTitle());

            add(subtitle);

            if (needShowDate) {
                Span dateSubtitle = new Span();
                dateSubtitle.addClassNames("text-s", "text-secondary");
                dateSubtitle.setText(post.getFormattedDate());

                add(dateSubtitle);
            }



            VaadinCKEditor textEditor = new VaadinCKEditorBuilder().with(builder -> {
                builder.editorType = Constants.EditorType.INLINE;
                builder.editorData = "Inline";
            }).createVaadinCKEditor();

            textEditor.setReadOnly(true);
            textEditor.setValue(post.getText());

            add(textEditor);

//            StyledText description = new StyledText(post.getText());
//            description.addClassName("my-m");
//            description.setWhiteSpace(HasText.WhiteSpace.PRE_LINE);

//            add(description);


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


            if (post.getTags() != null && needShowTags) {
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

    public PostServicePort getPostServicePort() {
        return postServicePort;
    }
}
