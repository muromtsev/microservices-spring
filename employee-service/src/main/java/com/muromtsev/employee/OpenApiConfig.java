package com.muromtsev.employee;

import com.muromtsev.employee.model.dto.EmployeeRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Employee Service API",
                version = "1.0.0",
                description = "API для управления сотрудниками",
                contact = @Contact(
                        name = "IlR",
                        email = "ipozvezd@gmail.com"
                ),
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                )),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Локальный сервер"
                )})
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .schema("EmployeeRequest", new Schema<EmployeeRequest>()
                        .addProperty("firstName", new StringSchema().example("John"))
                        .addProperty("lastName", new StringSchema().example("Doe"))
                        .addProperty("email", new StringSchema().example("john@doe.com"))
                        .addProperty("position", new StringSchema().example("Software Engineer"))
                        .addProperty("organizationUuid",  new StringSchema().example("a1b2c3d4-e5f6-7890-abcd-ef1234567890"))
                );
    }
}
