package com.example.vaadin.views;

import com.example.vaadin.entity.AppUser;
import com.example.vaadin.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@PageTitle("User Management")
@Route(value = "admin/users", layout = MainLayout.class)
@PreAuthorize("hasRole('ADMIN')")
public class UserManagementView extends VerticalLayout {

    private final UserService userService;
    private final Grid<AppUser> userGrid;
    private final TextField usernameField;
    private final PasswordField passwordField;
    private final TextField roleField;
    private AppUser editingUser;

    public UserManagementView(UserService userService) {
        this.userService = userService;
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        H2 header = new H2("User Management");
        header.addClassNames(LumoUtility.Margin.Bottom.MEDIUM);

        userGrid = new Grid<>(AppUser.class, false);
        userGrid.addColumn(AppUser::getId).setHeader("ID").setAutoWidth(true);
        userGrid.addColumn(AppUser::getUsername).setHeader("Username").setAutoWidth(true);
        userGrid.addColumn(AppUser::getRole).setHeader("Role").setAutoWidth(true);
        userGrid.setItems(fetchUsers());
        userGrid.asSingleSelect().addValueChangeListener(event -> editUser(event.getValue()));
        userGrid.setHeight("40vh");

        usernameField = new TextField("Username");
        passwordField = new PasswordField("Password");
        roleField = new TextField("Role (ADMIN or USER)");

        Button saveButton = new Button("Save", event -> saveUser());
        Button newButton = new Button("New User", event -> resetForm());
        Button deleteButton = new Button("Delete", event -> deleteUser());

        HorizontalLayout formActions = new HorizontalLayout(saveButton, newButton, deleteButton);
        formActions.addClassNames(LumoUtility.Margin.Top.MEDIUM);

        VerticalLayout formLayout = new VerticalLayout(usernameField, passwordField, roleField, formActions);
        formLayout.setPadding(false);
        formLayout.setSpacing(true);
        formLayout.setWidth("40rem");

        add(header, userGrid, formLayout);
    }

    private List<AppUser> fetchUsers() {
        return userService.findAll();
    }

    private void editUser(AppUser user) {
        if (user == null) {
            resetForm();
            return;
        }

        editingUser = user;
        usernameField.setValue(user.getUsername());
        roleField.setValue(user.getRole());
        passwordField.clear();
    }

    private void saveUser() {
        if (usernameField.isEmpty() || roleField.isEmpty()) {
            Notification.show("Username and role are required.", 3000, Notification.Position.MIDDLE);
            return;
        }

        if (editingUser == null) {
            AppUser user = new AppUser();
            user.setUsername(usernameField.getValue());
            user.setRole(roleField.getValue().toUpperCase());
            user.setPassword(passwordField.getValue().isEmpty() ? "password" : passwordField.getValue());
            userService.save(user);
            Notification.show("User created successfully.", 3000, Notification.Position.BOTTOM_START);
        } else {
            editingUser.setUsername(usernameField.getValue());
            editingUser.setRole(roleField.getValue().toUpperCase());
            if (!passwordField.isEmpty()) {
                editingUser.setPassword(passwordField.getValue());
            }
            userService.save(editingUser);
            Notification.show("User updated successfully.", 3000, Notification.Position.BOTTOM_START);
        }

        refreshGrid();
        resetForm();
    }

    private void deleteUser() {
        if (editingUser == null) {
            Notification.show("Select a user to delete.", 3000, Notification.Position.MIDDLE);
            return;
        }

        userService.deleteById(editingUser.getId());
        Notification.show("User deleted successfully.", 3000, Notification.Position.BOTTOM_START);
        refreshGrid();
        resetForm();
    }

    private void refreshGrid() {
        userGrid.setItems(fetchUsers());
    }

    private void resetForm() {
        editingUser = null;
        usernameField.clear();
        passwordField.clear();
        roleField.clear();
        userGrid.asSingleSelect().clear();
    }
}
