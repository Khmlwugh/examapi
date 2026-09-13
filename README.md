# ExamAPI

REST API for Peruvian university entrance exam questions (UNMSM, UNI, PUCP, UNAC, UNALM, UNFV), tagged by subject, category, college, and exam period. Backend for a planned daily-puzzle app and a filterable practice quiz tool.

**Live:** https://examapi.heygabo.dev
**Docs:** https://examapi.heygabo.dev/swagger-ui/index.html

## Stack

Java 17 · Spring Boot 4.1 · PostgreSQL (Neon) · Spring Data JPA · Flyway · Spring Security + JWT · Docker (Render)

## Key Endpoints

| Method | Path | Auth | Description |
|---|---|---|---|
| `GET` | `/api/questions/daily/{categoryId}` | Public | Deterministic question of the day per category |
| `GET` | `/api/questions/quiz` | Public | Random questions by optional filters |
| `POST` | `/api/questions` | JWT | Create a question with choices |
| `POST` | `/auth/login` | Public | Get a JWT |

## Running locally

Requires Java 17, Maven, and a Postgres instance. Set these environment variables, then run — Flyway applies migrations automatically:

- `DB_PASSWORD`
- `JWT_SECRET`
- `ADMIN_USERNAME`
- `ADMIN_PASSWORD` (a BCrypt hash, not plain text)

## Next up

CORS config, update/delete endpoints, choice-level images, tests.
