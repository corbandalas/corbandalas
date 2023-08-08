package com.corbandalas.web.views.customer.about;

import com.corbandalas.web.views.customer.MainLayout;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("О нас")
@Route(value = "about", layout = MainLayout.class)
@AnonymousAllowed
public class AboutView extends VerticalLayout {

    public AboutView() {

        setSpacing(false);

        Image img = new Image("https://sun9-64.userapi.com/impg/TBeQympmZgdYEil3lCogntXSO4SIhytoMrsS6Q/ZVfk3L3Gc7Q.jpg?size=1100x746&quality=95&sign=5f60d0f637b80c65e62e28106f152720&c_uniq_tag=lLqE3e3Xe5hNA4HpgXjRcopClyozhhgY5PkjPyb_x5o&type=album", "corbandalas.com");


        img.setWidth("90%");
        img.setHeight("70%");
        add(img);

        Label label = new Label("\n\n Всем привет! \n\n" +
                "Меня зовут Терещенко Артем, я backend Java-программист из Донецка с опытом работы на различных финтех проектах с 2006 года, страстный коллекционер марок и конвертов на тему Донбасса, а также винила группы Кино, фанат игры на укулеле, немного поэт и просто человек, который любит жить в своем родном городе несмотря на войну, обстрелы, взрывы и невзгоды, которые \"донецкие\" переживают последние 10 лет. \n\n" +
        "На моем сайте я буду делиться с вами своим опытом на Java, публиковать интересные статьи, фото и видео, а также наблюдения из своей жизни. Буду рад всем посетителям! \n\n  "
                );

        label.setWhiteSpace(HasText.WhiteSpace.PRE_LINE);

        Label labelSocialVK = new Label("Моя группа по филателии Донбасса в ВК: ");
        labelSocialVK.setWhiteSpace(HasText.WhiteSpace.PRE_LINE);

        Label labelSocialYouTube = new Label("\n\n Канал на YouTube: ");
        labelSocialYouTube.setWhiteSpace(HasText.WhiteSpace.PRE_LINE);

        Div wrapper = new Div();

        wrapper.add(labelSocialVK, new Anchor("https://vk.com/marki_donbassa", "https://vk.com/marki_donbassa"),
                labelSocialYouTube, new Anchor("https://www.youtube.com/channel/UCEh-4nHsNWhN_dDwttw_oXg", "https://www.youtube.com/channel/UCEh-4nHsNWhN_dDwttw_oXg"));


        add(label, wrapper);


        setSizeFull();
//        setJustifyContentMode(JustifyContentMode.AROUND);
//        setDefaultHorizontalComponentAlignment(Alignment.BASELINE);
//        getStyle().set("text-align", "center");
    }

}
