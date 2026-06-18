# Swagger UI Verification Report

Generated: 2026-06-14 14:15 IST

## Runtime Verification

| Check | Result |
| --- | --- |
| Service startup | Passed, Spring Boot started on port 8080 |
| Java runtime | OpenJDK 17.0.7, IntelliJ JBR |
| Gradle runtime | Gradle 8.2.1 |
| Health endpoint | UP |
| Swagger UI | HTTP 200 at http://localhost:8080/swagger-ui.html |
| OpenAPI JSON | HTTP 200 at http://localhost:8080/v3/api-docs |
| Seed profile API | Returned 1 profile record(s) |

## OpenAPI Info

| Field | Value |
| --- | --- |
| OpenAPI version | 3.0.1 |
| API title | OpenAPI definition |
| API version | v0 |

## Profile Endpoints

| Method | Path | Operation |
| --- | --- | --- |
| GET | `/api/v1/profiles` | list |
| POST | `/api/v1/profiles` | create |
| DELETE | `/api/v1/profiles/{id}` | delete |
| GET | `/api/v1/profiles/{id}` | getById |
| PATCH | `/api/v1/profiles/{id}` | update |
| PATCH | `/api/v1/profiles/{id}/deactivate` | deactivate |
| GET | `/api/v1/profiles/by-username/{username}` | getByUsername |

## Useful Local URLs

- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs
- Health: http://localhost:8080/actuator/health
- H2 console: http://localhost:8080/h2-console

