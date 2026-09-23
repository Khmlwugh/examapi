# ExamAPI

REST API for Peruvian university entrance exam questions (UNMSM, UNI, PUCP, UNAC, UNALM, UNFV), tagged by subject, category, college, and exam period. Backend for a filterable practice quiz app and a planned daily-puzzle app.

**Live:** https://examapi.heygabo.dev
**Docs:** https://examapi.heygabo.dev/swagger-ui/index.html

## Stack

Java 17 · Spring Boot 4.1 · PostgreSQL (Neon) · Spring Data JPA · Flyway · Spring Security + JWT · Bucket4j (rate limiting) · Docker · Nginx + Certbot on a self-hosted VPS

## Key Endpoints

| Method | Path | Auth | Description |
|---|---|---|---|
| `GET` | `/api/questions/daily/{categoryId}` | Public | Deterministic question of the day per category |
| `GET` | `/api/questions/quiz` | Public | Random questions by optional filters (capped at 10) |
| `POST` | `/api/questions/{id}/check-answer` | Public | Grade a submitted choice without exposing the answer key elsewhere |
| `GET` | `/api/colleges` / `/api/subjects` | Public | Only returns colleges/subjects that actually have questions |
| `POST` | `/api/questions` | JWT | Create a question with choices |
| `POST` | `/auth/login` | Public | Get a JWT |

## Running locally

Requires Java 17, Maven, and a Postgres instance. Set these environment variables, then run — Flyway applies migrations automatically:

- `DB_PASSWORD`
- `JWT_SECRET`
- `ADMIN_USERNAME`
- `ADMIN_PASSWORD` (a BCrypt hash, not plain text)

## Deploying / redeploying

Runs as a Docker container on a VPS behind Nginx (reverse proxy + TLS via Certbot). There's no CI/CD — redeploying after a push is manual:

```bash
ssh admin@<vps-ip>
cd /opt/examapi
git pull
docker compose up -d --build
```

## Next up

Update/delete endpoints for questions (currently patched directly in the DB when needed), choice-level images (one known question needs it), automated tests, more transcribed content.
