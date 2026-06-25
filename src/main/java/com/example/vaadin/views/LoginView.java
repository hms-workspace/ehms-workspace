package com.example.vaadin.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@PageTitle("Login")
@Route("login")
public class LoginView extends VerticalLayout {

    public LoginView() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        H2 title = new H2("Sign in to Management System");
        title.addClassNames(LumoUtility.Margin.Bottom.MEDIUM);

        LoginForm loginForm = new LoginForm();
        loginForm.setAction("/login");

        add(title, loginForm);
    }
}
