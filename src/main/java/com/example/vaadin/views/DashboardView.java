package com.example.vaadin.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * Dashboard View
 * 
 * Dashboard page with placeholder content
 */
@PageTitle("Dashboard")
@Route(value = "dashboard", layout = MainLayout.class)
public class DashboardView extends VerticalLayout {

    public DashboardView() {
        setSpacing(false);

        H2 header = new H2("Dashboard");
        header.addClassNames(LumoUtility.Margin.MEDIUM);

        Div content = new Div();
        content.addClassNames(
            LumoUtility.Display.FLEX,
            LumoUtility.FlexDirection.COLUMN,
            LumoUtility.Padding.MEDIUM
        );
        content.add(header);

        add(content);
        
        setClassName("dashboard-view");
        setSizeFull();
        setPadding(false);
        setMargin(false);
    }
}
