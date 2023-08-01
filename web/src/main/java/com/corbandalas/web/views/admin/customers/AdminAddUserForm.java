package com.corbandalas.web.views.admin.customers;

import com.corbandalas.domain.model.CustomerDTO;
import com.corbandalas.domain.model.RoleDTO;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import java.util.List;

public class AdminAddUserForm extends FormLayout {
    private CustomerDTO user;

    TextField username = new TextField("Имя");
    PasswordField password = new PasswordField("Пароль");
    EmailField email = new EmailField("Почта");
    ComboBox<RoleDTO> role = new ComboBox<>("Роли");
    Binder<CustomerDTO> binder = new BeanValidationBinder<>(CustomerDTO.class);

    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Cancel");

    public AdminAddUserForm(List<RoleDTO> roles) {
        addClassName("user-form");
        binder.bindInstanceFields(this);

        role.setItems(roles);
        role.setItemLabelGenerator(RoleDTO::getName);
        add(username,
                password,
                email,
                email,
                role,
                createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, user)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));


        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    public void setUser(CustomerDTO user) {
        this.user = user;
        binder.readBean(user);
    }



    private void validateAndSave() {
        try {
            binder.writeBean(user);
            fireEvent(new SaveEvent(this, user));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class UserFormEvent extends ComponentEvent<AdminAddUserForm> {
        private CustomerDTO user;

        protected UserFormEvent(AdminAddUserForm source, CustomerDTO contact) {
            super(source, false);
            this.user = contact;
        }

        public CustomerDTO getUser() {
            return user;
        }
    }

    public static class SaveEvent extends UserFormEvent {
        SaveEvent(AdminAddUserForm source, CustomerDTO user) {
            super(source, user);
        }
    }

    public static class DeleteEvent extends UserFormEvent {
        DeleteEvent(AdminAddUserForm source, CustomerDTO user) {
            super(source, user);
        }

    }

    public static class CloseEvent extends UserFormEvent {
        CloseEvent(AdminAddUserForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}