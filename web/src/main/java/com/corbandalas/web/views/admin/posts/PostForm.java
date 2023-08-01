package com.corbandalas.web.views.admin.posts;

import com.corbandalas.domain.model.CustomerDTO;
import com.corbandalas.domain.model.PostDTO;
import com.corbandalas.domain.model.Tag;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.*;
import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.shared.Registration;
import com.wontlost.ckeditor.Constants;
import com.wontlost.ckeditor.VaadinCKEditor;
import com.wontlost.ckeditor.VaadinCKEditorBuilder;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class PostForm extends FormLayout {

    private PostDTO post;

    private DatePicker date = new DatePicker();

    private TextField title = new TextField("Заголовок");
    VaadinCKEditor textEditor = new VaadinCKEditorBuilder().with(builder->{
        builder.editorType= Constants.EditorType.INLINE;
        builder.editorData="Inline";
    }).createVaadinCKEditor();

//    private TextField circulation = new TextField("Тираж");

    private ComboBox<CustomerDTO> customers = new ComboBox<>("Пользователь");

//    private ComboBox<Country> country = new ComboBox<>("Республика");

//    private ComboBox<PostStampType> postStampType = new ComboBox<>("Тип");

    private MultiSelectComboBox<Tag> tags = new MultiSelectComboBox("Тэги");

    Binder<PostDTO> binder = new BeanValidationBinder<>(PostDTO.class);

    Button save = new Button("Сохранить");
    Button delete = new Button("Удалить");
    Button close = new Button("Отменить");

    public PostForm(List<CustomerDTO> allCustomers) {
        addClassName("contact-form");
        binder.forField(this.date)
                .withConverter(new LocalDateConverter())
                .bind(PostDTO::getDate, PostDTO::setDate);
        binder.forField(this.textEditor)
                        .withValidator(content->content!=null && content.length()>0, "Empty is not allowed")
                .bind(PostDTO::getText, PostDTO::setText);

        binder.forField(this.customers)
                        .bind(PostDTO::getCustomer, PostDTO::setCustomer);

        binder.bindInstanceFields(this);


//        country.setItems(Arrays.asList(Country.values()));
//        country.setItemLabelGenerator(Country::name);
//
//        postStampType.setItems(Arrays.asList(PostStampType.values()));
//        postStampType.setItemLabelGenerator(PostStampType::name);
//
        customers.setItems(allCustomers);
//        postRelease.setItemLabelGenerator(PostRelease::getDescription);
//
        tags.setItems(Arrays.asList(Tag.values()));

        add(title, date, /*circulation, country,
                postRelease, postStampType, */ textEditor, customers, tags,
                createButtonsLayout());

    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

//        save.addClickShortcut(Key.ENTER);
//        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, post)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));


        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    public void setPost(PostDTO post) {
        this.post = post;
        binder.readBean(post);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(post);
            fireEvent(new SaveEvent(this, post));
        } catch (ValidationException e) {
            log.error("error", e);
        }
    }

    public void clear() {

        this.textEditor.clear();
    }

//    public MultiFileBuffer getMultiFileBuffer() {
//        return multiFileBuffer;
//    }

    // Events
    public static abstract class PostStampFormEvent extends ComponentEvent<PostForm> {
        private PostDTO post;

        protected PostStampFormEvent(PostForm source, PostDTO post) {
            super(source, false);
            this.post = post;
        }

        public PostDTO getPostStamp() {
            return post;
        }
    }

    public static class SaveEvent extends PostStampFormEvent {
        SaveEvent(PostForm source, PostDTO post) {
            super(source, post);
        }
    }

    public static class DeleteEvent extends PostStampFormEvent {
        DeleteEvent(PostForm source, PostDTO post) {
            super(source, post);
        }

    }

    public static class CloseEvent extends PostStampFormEvent {
        CloseEvent(PostForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

    class LocalDateConverter
            implements Converter<LocalDate, LocalDateTime> {
        @Override
        public Result<LocalDateTime> convertToModel(LocalDate value,
                                                    ValueContext context) {
            if (value == null) {
                return Result.ok(null);
            }
            return Result.ok(LocalDateTime.of(value, LocalTime.MIDNIGHT));
        }

        @Override
        public LocalDate convertToPresentation(LocalDateTime value,
                                               ValueContext context) {

            if (value == null) {
                return null;
            }
            return value.toLocalDate();
        }
    }

}