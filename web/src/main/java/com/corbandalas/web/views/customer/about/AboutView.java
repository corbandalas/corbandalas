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

        Image img = new Image("https://sun9-east.userapi.com/sun9-35/s/v1/s2/qyYw18jWVOm_8Ag_2rHY3PaAO0zTcwfQIz6yXhRd3-5otTUgM6K7715JLOHy_yIvM-g70nnU80vJOY5QSuk3fUg.jpg", "Марки Донбасса");

        img.setWidth("100%");
        add(img);

        Label label = new Label("\n\n Интернет-сообщество \"Марки Донбасса\", посвященное филателии Донецка и Луганска, c 2017 года объединяет вокруг себя филателистов и коллекционеров, которых интересует филателия Донбасса на протяжении всей его истории.\n" +
                "\n" +
                "Вот уже 5 лет на страницах нашего сообщества в ВК мы стараемся подробно освещать каждый выпуск республиканских почтовых ведомств, делиться с вами инсайдами, находить разновидности марок и конвертов, а также просто общаться и отвечать на вопросы подписчиков.\n" +
                "\n" +
                "Созданием данного сайта мы хотим расширить географию наших подписчиков, выйти за рамки сообщества во Вконтакте и рассказать о филателии Донбасса всему русскоговорящему миру.\n" +
                "\n" +
                "Собирайте наши марки и конверты, и следите за новостями филателии республик. Обещаем, что вас ждет масса всего интересного!"
        );

        label.setWhiteSpace(HasText.WhiteSpace.PRE_LINE);

        Label labelSocialVK = new Label("Наше сообщество в ВК: ");
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
