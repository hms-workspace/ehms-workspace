# Vaadin Project Template

A complete, production-ready Vaadin project template with Spring Boot, featuring all necessary configurations and examples.

## Features

- **Vaadin 24.0.0**: Latest responsive UI framework
- **Spring Boot 3.1.5**: Enterprise-grade Java framework
- **Maven Build**: Complete pom.xml with all dependencies
- **Spring Data JPA**: ORM integration with database support
- **H2 Database**: Default in-memory database (easily replaceable)
- **Responsive Design**: Works on desktop, tablet, and mobile
- **Navigation**: Built-in routing and multi-view support
- **Custom Theming**: CSS-based styling with Lumo theme
- **Development Tools**: Hot reload, debugging support
- **Testing**: JUnit 5 and Vaadin TestBench integration

## Project Structure

```
src/
├── main/
│   ├── java/com/example/vaadin/
│   │   ├── VaadinApplication.java          # Main entry point
│   │   ├── views/
│   │   │   ├── MainLayout.java            # Application shell
│   │   │   ├── HomeView.java              # Home page
│   │   │   ├── DashboardView.java         # Dashboard
│   │   │   └── SettingsView.java          # Settings
│   │   ├── components/
│   │   │   └── Header.java                # Custom components
│   │   └── services/
│   │       └── DataService.java           # Business logic
│   └── resources/
│       ├── application.properties         # App configuration
│       ├── application-production.properties
│       └── META-INF/resources/
│           └── frontend/themes/my-theme/
│               └── styles.css             # Custom styles
├── test/
│   └── java/com/example/vaadin/
│       └── VaadinApplicationTests.java
├── pom.xml                                 # Maven configuration
└── .gitignore
```

## Prerequisites

- **Java 17** or later
- **Maven 3.6+** or **Gradle 7+**
- **Node.js 16+** (for Vaadin frontend)

## Getting Started

### 1. Build the Project

```bash
mvn clean install
```

### 2. Run the Application

```bash
mvn spring-boot:run
```

The application will be available at: `http://localhost:8080`

### 3. Access H2 Console (Development)

```
http://localhost:8080/h2-console
```

Database URL: `jdbc:h2:mem:testdb`
Username: `sa`
Password: (leave empty)

## Development

### Adding New Views

Create a new view class in `src/main/java/com/example/vaadin/views/`:

```java
@PageTitle("My View")
@Route(value = "my-view", layout = MainLayout.class)
public class MyView extends VerticalLayout {
    public MyView() {
        add(new H2("My View"));
    }
}
```

Then add it to the navigation in `MainLayout.java`.

### Customizing Theme

Edit `src/main/resources/META-INF/resources/frontend/themes/my-theme/styles.css`

### Database Configuration

Update `application.properties` for different databases (PostgreSQL, MySQL, etc.)

## Production Deployment

1. Build production JAR:
```bash
mvn clean package -DskipTests
```

2. Run with production profile:
```bash
java -jar target/vaadin-app-1.0.0.jar --spring.profiles.active=production
```

## Dependencies

- **Vaadin Spring Boot Starter**: Integration with Spring Boot
- **Spring Data JPA**: Database ORM support
- **H2 Database**: Embedded database (development)
- **Lombok**: Reduce boilerplate code
- **JUnit 5**: Testing framework

## Configuration Files

### application.properties
Main configuration file for development environment with:
- Server port (8080)
- Database connection (H2 in-memory)
- Logging levels
- Vaadin settings

### application-production.properties
Production environment settings with:
- Production mode enabled
- Optimized logging
- File-based database path
- Schema validation instead of auto-update

## Useful Resources

- [Vaadin Documentation](https://vaadin.com/docs)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Vaadin Components](https://vaadin.com/docs/latest/components)
- [Lumo Theme](https://vaadin.com/docs/latest/styling/lumo)

## License

Apache License 2.0

## Support

For issues and questions:
- Vaadin Forum: https://vaadin.com/forum
- GitHub Issues: Create an issue in your repository
- Stack Overflow: Tag with `vaadin`
