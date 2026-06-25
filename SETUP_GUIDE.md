# Vaadin Development Setup Guide

## Quick Start

### 1. Prerequisites
- Java 17+ 
- Maven 3.6+
- Node.js 16+ (optional, Vaadin can manage frontend)

### 2. Clone and Setup
```bash
cd hmsworkspace
mvn clean install
```

### 3. Run Development Server
```bash
mvn spring-boot:run
```

Navigate to: `http://localhost:8080`

### 4. Hot Reload
Changes to Java files and views will automatically reload thanks to Spring Boot DevTools.

## Project Configuration

### Key Configuration Files

1. **pom.xml**
   - Maven dependencies
   - Build plugins
   - Spring Boot and Vaadin versions

2. **application.properties**
   - Development settings
   - Database configuration
   - Logging levels
   - Vaadin settings

3. **application-production.properties**
   - Production optimizations
   - Performance settings
   - Security configurations

### Database Setup

#### H2 (Default - Development)
- Automatically configured in-memory database
- Console at: `http://localhost:8080/h2-console`
- No additional setup needed

#### PostgreSQL (Production Ready)
1. Uncomment PostgreSQL dependency in pom.xml
2. Update application.properties:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/vaadindb
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQL10Dialect
```

#### MySQL
1. Add MySQL connector to pom.xml (see commented example)
2. Update properties accordingly

### Docker Deployment

Build and run with Docker:
```bash
docker build -t vaadin-app .
docker run -p 8080:8080 vaadin-app
```

Or use Docker Compose:
```bash
docker-compose up
```

## Project Structure Details

### Views (`src/main/java/.../views/`)
- **MainLayout.java** - Application shell with navigation
- **HomeView.java** - Landing page
- **DashboardView.java** - Dashboard page
- **SettingsView.java** - Settings page

Add new views following the same pattern.

### Components (`src/main/java/.../components/`)
- Reusable UI components
- Example: Header.java - Custom header component

### Services (`src/main/java/.../services/`)
- Business logic and data access
- Spring-managed services
- Database operations

### Frontend Resources (`src/main/resources/`)
- **application.properties** - App configuration
- **META-INF/resources/frontend/themes/my-theme/** - Custom styling

## Development Workflow

### Adding a New Page

1. Create View class in `src/main/java/.../views/`
```java
@PageTitle("My Page")
@Route(value = "my-page", layout = MainLayout.class)
public class MyPageView extends VerticalLayout {
    public MyPageView() {
        add(new H2("Welcome to My Page"));
    }
}
```

2. Add navigation item in MainLayout.java:
```java
SideNavItem myPageItem = new SideNavItem("My Page", MyPageView.class);
nav.addItem(myPageItem);
```

### Adding Database Entity

1. Create entity class in appropriate package
2. Annotate with `@Entity` and `@Table`
3. Use Spring Data JPA Repository for data access

### Customizing Theme

Edit `src/main/resources/META-INF/resources/frontend/themes/my-theme/styles.css`

## Testing

### Run Tests
```bash
mvn test
```

### Test Files Location
`src/test/java/com/example/vaadin/`

## Production Build

### Build JAR
```bash
mvn clean package -DskipTests
```

### Run JAR
```bash
java -jar target/vaadin-app-1.0.0.jar
```

### With Production Profile
```bash
java -jar target/vaadin-app-1.0.0.jar --spring.profiles.active=production
```

## Troubleshooting

### Build Issues
- Clear Maven cache: `mvn clean`
- Update dependencies: `mvn dependency:resolve`

### Port Already in Use
- Change port in application.properties: `server.port=8081`
- Or kill process on port 8080

### Database Connection
- Verify H2 console connection URL
- Check application.properties database settings
- Ensure database service is running (for external databases)

## Performance Tuning

1. **Enable Production Mode**
   - Set `vaadin.productionMode=true`
   - Reduces frontend bundle size

2. **Database Connection Pool**
   - Configure in application.properties
   - Tune based on load

3. **Caching**
   - Implement response caching
   - Use Spring Cache abstraction

## Security Notes

- Use HTTPS in production
- Implement authentication/authorization
- Validate user inputs
- Use Spring Security for authentication
- Keep dependencies updated

## Resources

- [Vaadin Documentation](https://vaadin.com/docs)
- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [Maven Documentation](https://maven.apache.org/guides/)
