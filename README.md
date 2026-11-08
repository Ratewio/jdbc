# DAO — Spring JDBC

Учебный проект, демонстрирующий паттерн **Data Access Object (DAO)** с использованием Spring JDBC.

## Создание базы данных

1. Откройте терминал и подключитесь к PostgreSQL:

   ```bash
   sudo -u postgres /opt/postgresql18/bin/psql -d postgres
   ```

2. Создайте базу данных:

   ```sql
   CREATE DATABASE dao;
   ```

3. Проверьте создание базы:

   ```sql
   \l
   ```

4. Выйдите из PostgreSQL:

   ```sql
   \q
   ```

## Технологии

- Java 25
- Spring Boot 4
- Spring JDBC (`NamedParameterJdbcTemplate`)
- PostgreSQL 18
- Maven

## Возможности

- Автоматическое создание таблиц `CUSTOMERS` и `ORDERS` через `schema.sql`.
- Получение названий товаров по имени покупателя.
- REST API для выполнения SQL-запроса.

## Запуск

Запустите приложение:

```bash
./mvnw spring-boot:run
```

При запуске Spring Boot автоматически выполнит `schema.sql` и создаст необходимые таблицы.

## API

### Получение товаров покупателя

**GET** `/products/fetch-product`

Параметр:

| Название | Описание |
|---|---|
| `name` | Имя покупателя |

Пример запроса:

```http
GET http://localhost:8080/products/fetch-product?name=Ivan
```

Пример ответа:

```json
[
  "Laptop",
  "Mouse"
]
```

Поиск имени выполняется без учёта регистра.

## Структура проекта

```text
src/
└── main/
    ├── java/com/example/demo/
    │   ├── controller/
    │   │   └── ProductController.java
    │   ├── repository/
    │   │   └── ProductRepository.java
    │   └── DemoApplication.java
    └── resources/
        ├── sql/
        │   └── find_products_by_name.sql
        ├── application.properties
        └── schema.sql
```

## Принцип работы

`ProductController` принимает HTTP-запрос и передаёт имя покупателя в `ProductRepository`.

`ProductRepository` выполняет SQL-запрос через `NamedParameterJdbcTemplate` и возвращает список названий товаров.

SQL-запрос хранится отдельно в `find_products_by_name.sql`.