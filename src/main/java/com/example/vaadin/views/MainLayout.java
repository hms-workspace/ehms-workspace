package com.example.vaadin.views;

import com.example.vaadin.security.SecurityUtils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.Layout;

/**
 * Main Layout component with authentication-aware navigation.
 */
@Layout
public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Management System");
        title.getStyle()
            .set("font-size", "var(--lumo-font-size-l)")
            .set("margin", "0");

        Button logoutButton = new Button("Logout", event -> {
            UI.getCurrent().getPage().setLocation("/logout");
        });

        HorizontalLayout headerLayout = new HorizontalLayout(toggle, title, logoutButton);
        headerLayout.expand(title);
        headerLayout.setAlignItems(Alignment.CENTER);
        headerLayout.setWidthFull();
        headerLayout.setPadding(true);

        addToNavbar(headerLayout);
    }

    private void createDrawer() {
        SideNav nav = new SideNav();

        if (SecurityUtils.hasRole("ROLE_ADMIN")) {
            nav.addItem(new SideNavItem("Admin Dashboard", DashboardView.class));
            nav.addItem(new SideNavItem("User Management", UserManagementView.class));
        }

        if (SecurityUtils.hasRole("ROLE_USER")) {
            nav.addItem(new SideNavItem("My Dashboard", UserDashboardView.class));
        }

        Scroller scroller = new Scroller(nav);
        scroller.setClassName("nav-scroller");

        addToDrawer(scroller);
    }
}
