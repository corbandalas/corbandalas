package com.corbandalas.web.views.customer.info;

import com.corbandalas.domain.model.Tag;
import com.corbandalas.domain.ports.api.PostServicePort;
import com.corbandalas.web.views.customer.MainLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Моя карьера")
@Route(value = "career", layout = MainLayout.class)
@AnonymousAllowed
public class BioView extends InfoPageView {


    public BioView(PostServicePort postServicePort) {
        super(postServicePort);
    }

    @Override
    public Tag getTag() {
        return Tag.ABOUT;
    }
}
