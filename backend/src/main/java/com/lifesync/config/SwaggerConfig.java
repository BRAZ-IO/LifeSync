package com.lifesync.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${spring.profiles.active:dev}")
    private String activeProfile;

    @Bean
    public OpenAPI lifeSyncOpenAPI() {
        return new OpenAPI()
            .info(apiInfo())
            .servers(apiServers())
            .tags(apiTags())
            .components(new Components()
                .addSecuritySchemes("bearer-jwt", createBearerTokenSecurityScheme()))
            .addSecurityItem(new SecurityRequirement().addList("bearer-jwt"))
            .externalDocs(new ExternalDocumentation()
                .description("LifeSync API Documentation & Wiki")
                .url("https://docs.lifesync.com"));
    }

    private Info apiInfo() {
        return new Info()
            .title("LifeSync API")
            .description("""
                **AI Fitness Coach Backend API**
                
                Comprehensive fitness tracking and workout planning system with AI-powered recommendations.
                
                ## Features
                - 🏃‍♂️ Personalized workout plans
                - 📊 Progress tracking and analytics
                - 🎯 Habit management system
                - 🤖 AI-powered fitness coaching
                - 🔐 JWT-based authentication
                - 📱 Mobile-ready REST API
                
                ## Authentication
                This API uses JWT (JSON Web Token) for authentication. Include the token in the Authorization header:
                `Authorization: Bearer <your-jwt-token>`
                
                ## Rate Limiting
                - Development: No limits
                - Production: 1000 requests/hour per user
                """)
            .version(getApiVersion())
            .contact(apiContact())
            .license(apiLicense())
            .termsOfService("https://lifesync.com/terms");
    }

    private Contact apiContact() {
        return new Contact()
            .name("LifeSync API Team")
            .email("api-support@lifesync.com")
            .url("https://lifesync.com/contact")
            .extensions(java.util.Map.of(
                "x-twitter", "@lifesync_api",
                "x-github", "https://github.com/lifesync/api"
            ));
    }

    private License apiLicense() {
        return new License()
            .name("MIT License")
            .url("https://opensource.org/licenses/MIT")
            .extensions(java.util.Map.of(
                "x-commercial-use", "Allowed",
                "x-attribution", "Required"
            ));
    }

    private List<Server> apiServers() {
        return Arrays.asList(
            new Server()
                .url("http://localhost:8080/api")
                .description("🔧 Local Development Server")
                .extensions(java.util.Map.of(
                    "x-environment", "development",
                    "x-version", "latest"
                )),
            new Server()
                .url("https://staging-api.lifesync.com/api")
                .description("🧪 Staging Environment")
                .extensions(java.util.Map.of(
                    "x-environment", "staging",
                    "x-version", "v2.0.0"
                )),
            new Server()
                .url("https://api.lifesync.com/api")
                .description("🚀 Production Server")
                .extensions(java.util.Map.of(
                    "x-environment", "production",
                    "x-version", "v2.0.0"
                ))
        );
    }

    private List<Tag> apiTags() {
        return Arrays.asList(
            new Tag()
                .name("Authentication")
                .description("🔐 User authentication and authorization endpoints")
                .externalDocs(new ExternalDocumentation()
                    .description("JWT Authentication Guide")
                    .url("https://docs.lifesync.com/auth")),
            new Tag()
                .name("Users")
                .description("👤 User management and profile endpoints")
                .externalDocs(new ExternalDocumentation()
                    .description("User Profile Management")
                    .url("https://docs.lifesync.com/users")),
            new Tag()
                .name("Habits")
                .description("📈 Habit tracking and completion endpoints")
                .externalDocs(new ExternalDocumentation()
                    .description("Habit Tracking System")
                    .url("https://docs.lifesync.com/habits")),
            new Tag()
                .name("Workouts")
                .description("💪 Workout management and planning endpoints")
                .externalDocs(new ExternalDocumentation()
                    .description("Workout Planning Guide")
                    .url("https://docs.lifesync.com/workouts")),
            new Tag()
                .name("Workout Sessions")
                .description("⏱️ Workout session tracking and execution endpoints")
                .externalDocs(new ExternalDocumentation()
                    .description("Session Tracking")
                    .url("https://docs.lifesync.com/sessions")),
            new Tag()
                .name("Progress")
                .description("📊 Physical progress tracking and analytics endpoints")
                .externalDocs(new ExternalDocumentation()
                    .description("Progress Analytics")
                    .url("https://docs.lifesync.com/progress")),
            new Tag()
                .name("AI Coach")
                .description("🤖 AI-powered fitness coaching and recommendations endpoints")
                .externalDocs(new ExternalDocumentation()
                    .description("AI Coach Features")
                    .url("https://docs.lifesync.com/ai-coach"))
        );
    }

    private SecurityScheme createBearerTokenSecurityScheme() {
        return new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer")
            .description("""
                JWT Authentication Token
                
                **How to get a token:**
                1. POST /auth/login with your credentials
                2. Copy the token from the response
                3. Include it in the Authorization header: `Bearer <token>`
                
                **Token format:** `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...`
                
                **Token expiration:** 24 hours
                """);
    }

    private String getApiVersion() {
        return switch (activeProfile) {
            case "prod" -> "2.0.0";
            case "staging" -> "2.0.0-rc.1";
            default -> "2.0.0-SNAPSHOT";
        };
    }
}
