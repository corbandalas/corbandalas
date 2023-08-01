package com.corbandalas.web.views.customer;

import com.corbandalas.domain.model.CustomerDTO;
import com.corbandalas.web.appnav.AppNav;
import com.corbandalas.web.appnav.AppNavItem;
import com.corbandalas.web.security.AuthenticatedUser;
import com.corbandalas.web.views.customer.about.AboutView;
import com.corbandalas.web.views.customer.posts.PostListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

/**
 * The main view is a top-level placeholder for other views.
 */
@CssImport("/themes/myapp/styles.css")
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;

    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();

    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Блог Донецкого программиста");


        StreamResource res = new StreamResource("bg.png", () -> {
            // eg. load image data from classpath (src/main/resources/images/image.png)
            return MainLayout.class.getClassLoader().getResourceAsStream("images/logo.png");
        });
        Image imageFromStream = new Image( res,"corbandalas.com .:. I am that I am");

        imageFromStream.addClickListener(event -> event.getSource().getUI().get().navigate(PostListView.class));


        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(imageFromStream);

        Scroller scroller = createScroller();

        addToDrawer(header, scroller, createFooter());

    }

    private Scroller createScroller() {
        Scroller scroller = new Scroller(createNavigation());
        scroller.getElement().getStyle().set("background-image" , "url('img/bg.png')" );
        scroller.getElement().getStyle().set("background-repeat", "no-repeat");
        scroller.getElement().getStyle().set("background-position", "0px 90px");
        return scroller;
    }

    private AppNav createNavigation() {
        // AppNav is not an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        // Starting with v24.1, AppNav will be replaced with the official
        // SideNav component.
        AppNav nav = new AppNav();


        if (accessChecker.hasAccess(PostListView.class)) {
            nav.addItem(new AppNavItem("Статьи", PostListView.class,  LineAwesomeIcon.FILE.create()));

        }
        if (accessChecker.hasAccess(AboutView.class)) {
            nav.addItem(new AppNavItem("О сайте", AboutView.class,  LineAwesomeIcon.SUN.create()));

        }

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        Optional<CustomerDTO> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            CustomerDTO user = maybeUser.get();

            Avatar avatar = new Avatar(user.getName());

            StreamResource resource = new StreamResource("avatar.png", () -> {
                // eg. load image data from classpath (src/main/resources/images/image.png)
                return MainLayout.class.getClassLoader().getResourceAsStream("images/avatar.png");
            });

            avatar.setImageResource(resource);
            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(avatar);
            div.add(user.getName());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
            userName.add(div);
            if (user.isUserAdmin()) {
                userMenu.addItem("Админка", e -> {
                    e.getSource().getUI().get().navigate("/admin/posts");
                });
            }
            userName.getSubMenu().addItem("Выйти", e -> {
                authenticatedUser.logout();
            });

            layout.add(userMenu);
        } else {
            Anchor loginLink = new Anchor("login", "Логин");
            layout.add(loginLink);
        }

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "corbandalas.com .:. I Am that I Am" : String.join("corbandalas.com .:.",title.value());
    }
}
