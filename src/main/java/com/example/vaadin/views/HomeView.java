package com.example.vaadin.views;

import com.example.vaadin.security.SecurityUtils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * Welcome View
 * 
 * Public landing page accessible without authentication.
 */
@PageTitle("Welcome")
@Route(value = "")
public class HomeView extends VerticalLayout implements BeforeEnterObserver {

    public HomeView() {
        setSpacing(false);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();

        H2 header = new H2("Management System");
        header.addClassNames(LumoUtility.Margin.MEDIUM, LumoUtility.FontSize.XXXLARGE);

        Paragraph introduction = new Paragraph(
            "A secure Vaadin Flow application with role-based dashboards, user management, "
            + "and a responsive UI built with Spring Boot."
        );
        introduction.addClassNames(LumoUtility.Margin.MEDIUM, LumoUtility.TextAlign.CENTER);
        introduction.getStyle().set("max-width", "36rem");

        Button loginButton = new Button("Login", event -> UI.getCurrent().navigate("login"));
        Button registerButton = new Button("Register", event -> UI.getCurrent().navigate("login"));

        HorizontalLayout actions = new HorizontalLayout(loginButton, registerButton);
        actions.addClassNames(LumoUtility.Gap.MEDIUM, LumoUtility.Margin.MEDIUM);

        add(header, introduction, actions);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (SecurityUtils.isUserLoggedIn()) {
            if (SecurityUtils.hasRole("ROLE_ADMIN")) {
                event.forwardTo("dashboard");
            } else if (SecurityUtils.hasRole("ROLE_USER")) {
                event.forwardTo("user/dashboard");
            }
        }
    }
}
