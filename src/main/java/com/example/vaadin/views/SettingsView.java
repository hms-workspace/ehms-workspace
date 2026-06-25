package com.example.vaadin.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

/**
 * Settings View
 * 
 * Settings page for application configuration
 */
@PageTitle("Settings")
@Route(value = "settings", layout = MainLayout.class)
public class SettingsView extends VerticalLayout {

    public SettingsView() {
        setSpacing(false);

        H2 header = new H2("Settings");
        header.addClassNames(LumoUtility.Margin.MEDIUM);

        Div content = new Div();
        content.addClassNames(
            LumoUtility.Display.FLEX,
            LumoUtility.FlexDirection.COLUMN,
            LumoUtility.Padding.MEDIUM
        );
        content.add(header);

        add(content);
        
        setClassName("settings-view");
        setSizeFull();
        setPadding(false);
        setMargin(false);
    }
}
