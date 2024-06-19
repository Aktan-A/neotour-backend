# neotour-backend

## Project Setup
```bash
git clone git@github.com:Aktan-A/neotour-backend.git
./mvnw clean spring-boot:run
```

## Running Tests
```bash
./mvnw test
```

## Running Docker-Compose
```bash
docker compose -f docker-compose.dev.yml up --build -d
```

Technologies
- Spring
- Spring Boot
- Spring MVC
- PostgreSQL
- Lombok
- JWT
- Docker
- Cloudinary

## Environment Variables
| Key                     | Description               |
|-------------------------|---------------------------|
| DB_URL                  | Database url.             |
| DB_USERNAME             | Database user username.   |
| DB_PASSWORD             | Database user password.   |
| ACCESS_TOKEN_SECRET_KEY | Secret for access tokens. |
| CLOUDINARY_CLOUD_NAME   | Cloudinary cloud name.    |
| CLOUDINARY_API_KEY      | Cloudinary api key.       |
| CLOUDINARY_API_SECRET   | Cloudinary api secret.    |