package com.corbandalas.web.views.admin.posts;

import com.corbandalas.domain.model.PostDTO;
import com.corbandalas.domain.ports.api.CustomerServicePort;
import com.corbandalas.domain.ports.api.PostServicePort;
import com.corbandalas.web.views.admin.AdminLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


@Component
@Scope("prototype")
@Route(value = "admin/posts", layout = AdminLayout.class)
@PageTitle("Посты | corbandalas.com")
//@RolesAllowed("ROLE_ADMIN")
@AnonymousAllowed
public class PostListView extends VerticalLayout {
    private Grid<PostDTO> grid = new Grid<>(PostDTO.class);

    private DatePicker filterDateStart = new DatePicker();
    private DatePicker filterDateEnd = new DatePicker();

    private LocalDate startDate;
    private LocalDate endDate;

    private PostForm form;
    private PostServicePort postServicePort;
    private CustomerServicePort customerServicePort;

    public PostListView(PostServicePort postStampService, CustomerServicePort customerServicePort) {
        this.postServicePort = postStampService;
        this.customerServicePort = customerServicePort;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new PostForm(customerServicePort.retrieveAll());
        form.setWidth("25em");
        form.addListener(PostForm.SaveEvent.class, this::savePostStamp);
        form.addListener(PostForm.DeleteEvent.class, this::deleteContact);
        form.addListener(PostForm.CloseEvent.class, e -> closeEditor());

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
                editPost(event.getValue()));
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("title", "text", "date", "customer");
//        grid.addColumn(Topic::getTruncatedText).setHeader("text");
//        grid.addColumn(postStamp -> ((postStamp.getPostRelease() != null) ? postStamp.getPostRelease().getDescription() : "")).setHeader("Релиз");
        grid.addColumn(post -> post.getTags()).setHeader("Тэги");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private HorizontalLayout getToolbar() {
        filterDateStart.setPlaceholder("Установите начальную дату...");
        filterDateEnd.setPlaceholder("Установите конечную дату...");
        filterDateStart.setClearButtonVisible(true);
        filterDateEnd.setClearButtonVisible(true);
        filterDateStart.addValueChangeListener(event -> {
            startDate = event.getValue();
        });

        filterDateEnd.addValueChangeListener(event -> {

            endDate = event.getValue();

            grid.setItems(query -> postServicePort.retrievePostsByDate(startDate, endDate, query.getPage(), query.getPageSize()));
        });

        Button addContactButton = new Button("Добавить пост");
        addContactButton.addClickListener(click -> addPost());

        HorizontalLayout toolbar = new HorizontalLayout(filterDateStart, filterDateEnd, addContactButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void savePostStamp(PostForm.SaveEvent event) {

        PostDTO postDTO = event.getPostStamp();

//        Set<String> files = event.getSource().getMultiFileBuffer().getFiles();

//        List<FileData> fileDataList = files.stream().map(t -> event.getSource().getMultiFileBuffer().getFileData(t)).collect(Collectors.toList());

        if (postDTO.uuid == null) {

//            PostDTO.builder()
//                    .customer(postStamp.getCustomer())
//                    .date(LocalDateTime.now())
//                    .text(postStamp.getText())
//                    .build();
//
//
//            new PostDTO(postStamp.getName(), postStamp.getDescription(), postStamp.getReleaseDate(),
//                    fileDataList.toArray(new FileData[0]), postStamp.getPostStampType(), postStamp.getCirculation(), postStamp.getCountry(), postStamp.getTags())


            postServicePort.create(postDTO);
        } else {
            postServicePort.update(postDTO);
        }


        updateList();
        closeEditor();
    }

    private void deleteContact(PostForm.DeleteEvent event) {
        postServicePort.delete(event.getPostStamp().uuid);
        updateList();
        closeEditor();
    }

    public void editPost(PostDTO post) {
        if (post == null) {
            closeEditor();
        } else {
            form.setPost(post);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    void addPost() {
        grid.asSingleSelect().clear();
        editPost(new PostDTO());
    }

    private void closeEditor() {
        form.setPost(null);
        form.setVisible(false);
        form.clear();
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(query -> postServicePort.retrieveAllByPage(query.getPage(), query.getLimit()));
    }

    public Date convertToDateViaInstant(LocalDate dateToConvert) {

        if (dateToConvert != null)

            return Date.from(dateToConvert.atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant());

        else return new Date();
    }


}
