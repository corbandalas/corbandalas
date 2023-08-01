package com.corbandalas.web.views.customer.posts;

import com.corbandalas.domain.model.PostDTO;
import com.corbandalas.web.views.TagBadgeHolder;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class BasePostView extends Div {

    public static final int TOPIC_TRUNK_SIZE = 250;

    private void addTagsToLayout(PostDTO postDTO, VerticalLayout description) {
        if (postDTO.getTags() != null) {
            HorizontalLayout horizontalLayout = new HorizontalLayout();

            postDTO.getTags().forEach(tag -> {
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

            if (description != null ) {
                description.add(horizontalLayout);
            }
        }
    }

     protected HorizontalLayout createCard(PostDTO postDTO) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");

        String defaultImageURL = "https://sun9-north.userapi.com/sun9-79/s/v1/if1/NY1A9C8V4PLYL3m0xzLU7Q8khTmdYuvc1scNbEjUMz-4SnbHiQm_GmdJy7m4N7Pu8QfNuvn5.jpg?size=307x200&quality=96&type=album";

        Image image = new Image();
//        if (topic.getPhotos() != null && topic.getPhotos().size() > 0) {
//            image.setSrc(topic.getPhotos().stream().findFirst().orElse(defaultImageURL));
//
//        } else {
        image.setSrc(defaultImageURL);
//        }

        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);


        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        String nameTitle = postDTO.getTitle();

        Span name = new Span(nameTitle);
        name.addClassName("name");
        H1 date = new H1(postDTO.getFormattedDate());
        date.addClassName("date");
        header.add(name, date);

//        Span post = new Span(topic.getText());
//        post.addClassName("post");

        Div wrapper = new Div();

//        Label post = new Label(postDTO.getTruncatedText(TOPIC_TRUNK_SIZE));
//
//        post.setWhiteSpace(WhiteSpace.PRE_LINE);

        Paragraph post = new Paragraph(postDTO.getTruncatedText(TOPIC_TRUNK_SIZE));
        post.addClassName("post");



        wrapper.add(post, new Anchor("/post/" + postDTO.uuid, "Показать полностью..."));


//        HorizontalLayout actions = new HorizontalLayout();
//        actions.addClassName("actions");
//        actions.setSpacing(false);
//        actions.getThemeList().add("spacing-s");

//        Icon likeIcon = VaadinIcon.HEART.create();
//        likeIcon.addClassName("icon");
//        Span likes = new Span(person.getLikes());
//        likes.addClassName("likes");
//        Icon commentIcon = VaadinIcon.COMMENT.create();
//        commentIcon.addClassName("icon");
//        Span comments = new Span(person.getComments());
//        comments.addClassName("comments");
//        Icon shareIcon = VaadinIcon.CONNECT.create();
//        shareIcon.addClassName("icon");
//        Span shares = new Span(person.getShares());
//        shares.addClassName("shares");

//        actions.add(likeIcon, likes, commentIcon, comments, shareIcon, shares);

        description.add(header, wrapper/*, actions*/);
        card.add(image, description);


        addTagsToLayout(postDTO, description);


        return card;
    }
}
