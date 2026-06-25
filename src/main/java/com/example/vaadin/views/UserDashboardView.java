package com.example.vaadin.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@PageTitle("User Dashboard")
@Route(value = "user/dashboard", layout = MainLayout.class)
public class UserDashboardView extends VerticalLayout {

    public UserDashboardView() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        H2 header = new H2("User Dashboard");
        header.addClassNames(LumoUtility.Margin.Bottom.MEDIUM);

        Paragraph message = new Paragraph("Welcome! You have USER-level access to the application.");
        message.addClassNames(LumoUtility.Margin.Bottom.MEDIUM);

        add(header, message);
    }
}
