package com.corbandalas.web.views.admin.customers;


import com.corbandalas.domain.model.CustomerDTO;
import com.corbandalas.domain.ports.api.CustomerServicePort;
import com.corbandalas.domain.ports.api.RoleServicePort;
import com.corbandalas.web.views.admin.AdminLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
@Route(value="admin/users", layout = AdminLayout.class)
@PageTitle("Пользователи | Марки Донбасса")
@RolesAllowed("ROLE_ADMIN")
public class AdminListUserView extends VerticalLayout {
    private Grid<CustomerDTO> grid = new Grid<>(CustomerDTO.class);

    private TextField filterText = new TextField();

    private AdminAddUserForm form;

    private final CustomerServicePort customerServicePort;
    private final RoleServicePort roleServicePort;
    private final PasswordEncoder passwordEncoder;

    public AdminListUserView(CustomerServicePort customerServicePort, RoleServicePort roleServicePort, PasswordEncoder passwordEncoder) {
        this.customerServicePort = customerServicePort;
        this.roleServicePort = roleServicePort;
        this.passwordEncoder = passwordEncoder;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new AdminAddUserForm(roleServicePort.getAll());
        form.setWidth("25em");
        form.addListener(AdminAddUserForm.SaveEvent.class, this::saveContact);
        form.addListener(AdminAddUserForm.DeleteEvent.class, this::deleteContact);
        form.addListener(AdminAddUserForm.CloseEvent.class, e -> closeEditor());

        FlexLayout content = new FlexLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.setFlexShrink(0, form);
        content.addClassNames("content", "gap-m");
        content.setSizeFull();


        add(getToolbar(), content);

        updateList();

        closeEditor();


        grid.asSingleSelect().addValueChangeListener(event ->
                editContact(event.getValue()));

        add(getToolbar(), grid);
    }

    private void configureGrid() {
        grid.addClassNames("topic-grid");
        grid.setSizeFull();
        grid.setColumns("username","enabled", "expired", "locked", "roles");
//        grid.addColumn(topic -> topic..getStatus().getName()).setHeader("Status");
//        grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);

        Button addUserButton = new Button("Добавить пользователя");

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addUserButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void updateList() {
        grid.setItems(customerServicePort.getAll());
    }

    private void saveContact(AdminAddUserForm.SaveEvent event) {
//        customerServicePort.create(new CustomerDTO(event.getUser().getName(), /*passwordEncoder.encode(event.getUser().getHashedPassword()),*/ event.getUser().getEmail(), event.getUser().getRoles()));
        updateList();
        closeEditor();
    }

    private void deleteContact(AdminAddUserForm.DeleteEvent event) {
        customerServicePort.delete(event.getUser().getUuid());
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setUser(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    public void editContact(CustomerDTO user) {
        if (user == null) {
            closeEditor();
        } else {
            form.setUser(user);
            form.setVisible(true);
            addClassName("editing");
        }
    }
}

