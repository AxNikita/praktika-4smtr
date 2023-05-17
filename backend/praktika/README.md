# Backend

## Запуск сервиса в Docker

Данные команды выполнять в корневом каталоге (praktika) где расположен Dockerfile

```bash
# Собрать image приложения

docker build -t praktika-app .
```

```bash
# Запуск приложения на порту 7000 (также на порту 7000 работает само приложение внутри контейнера)
# Если данный порт занят на вашей машине, необходимо изменить первый из портов (до знака ":")

docker run -d --name praktika-backend -p 7000:7000 praktika-app
```

```bash
# Остановить работу контейнера (сервиса)

docker stop praktika-backend
```

## H2 Database админ-панель

Открыть в браузере [Admin-H2](http://localhost:7000/praktika-console)

```
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:praktikadb
User Name: admin
Password: admin
```