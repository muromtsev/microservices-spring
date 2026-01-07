## Микросервисная Система Управления Сотрудниками и Организациями

Система управления сотрудниками и организациями, построенная на микросервисной архитектуре с использованием Spring Cloud.

## Текущая реализация
- `config-server` - Spring Cloud Config Server
- `employee-service` - Сервис управления сотрудниками
- `api-gateway` - API Gateway (в разработке)
- `service-discovery` - Service Discovery (в разработке)

## Быстрый старт
Предварительные требования:
- Java 17+
- Maven 3.6+
- Docker & Docker Compose (опционально)
- Git

```bash
# 1. Клонируйте репозиторий
git clone https://github.com/muromtsev/microservices-spring.git
# 2. Поднимаем PostgreSQL БД employee_dev  
# 3. Запуск сервисов:
  - config-server
  - eureka-server
  - organization-service
  - employee-service
  
```

## Конфигурация сервисов
Порт и настройки по умолчанию:

| Сервис               | Порт | Профиль | База данных  | Swagger UI                                  |
|----------------------|------|---------|--------------|---------------------------------------------|
| Config Server        | 8888 | default | -            | -                                           |
| Eureka Server        | 8070 | default | -            | http://localhost:8070/                      |
| Employee Service     | 8080 | dev     | employee_dev | http://localhost:8080/swagger-ui/index.html |
| Organization Service | 8081 | dev     | employee_dev | http://localhost:8081/swagger-ui/index.html |

### Профили Spring:
`dev` - разработка с PostgreSQL

При запуске в dev профиле автоматически создаются тестовые данные

`prod` - production с PostgreSQL

## API Документация
#### Employee Service API

- `GET /v1/employees` - список сотрудников
- `POST /v1/employees` - создать сотрудника
- `GET /v1/employees/{employeeId}` - получить сотрудника по ID
- `PUT /v1/employees/{employeeId}` - обновить сотрудника
- `DELETE /v1/employees/{employeeId}` - удалить сотрудника

#### Organization Service API

- `GET /v1/organizations/all` - список организаций
- `POST /v1/organizations` - создать организацию
- `GET /v1/organizations/{uuid}` - получить организацию по UUID
- `GET /v1/organizations/code/{code}` - получить организацию по CODE
- `POST /v1/organizations/update/{uuid}` - обновить организацию
- `DELETE /v1/organizations/{uuid}` - удалить организацию

