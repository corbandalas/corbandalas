package com.corbandalas.web.views.customer.info;

import com.corbandalas.domain.model.Tag;
import com.corbandalas.domain.ports.api.PostServicePort;
import com.corbandalas.web.views.customer.MainLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("О сайте и обо мне")
@Route(value = "about", layout = MainLayout.class)
@AnonymousAllowed
public class AboutView extends InfoPageView {


    public AboutView(PostServicePort postServicePort) {
        super(postServicePort);
    }

    @Override
    public Tag getTag() {
        return Tag.ABOUT;
    }
}
