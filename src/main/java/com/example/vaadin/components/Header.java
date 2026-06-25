package com.example.vaadin.components;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;

/**
 * Reusable header component
 * 
 * Example custom component demonstrating component composition
 */
public class Header extends HorizontalLayout {

    private TextField searchField;
    private Button settingsButton;

    public Header() {
        setWidthFull();
        createUI();
    }

    private void createUI() {
        searchField = new TextField();
        searchField.setPlaceholder("Search...");
        searchField.setWidth("300px");

        settingsButton = new Button("Settings");

        add(searchField, settingsButton);
        setFlexGrow(1, searchField);
    }

    public TextField getSearchField() {
        return searchField;
    }

    public Button getSettingsButton() {
        return settingsButton;
    }
}
