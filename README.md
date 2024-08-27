<details><summary>Запуск</summary>
Для того, чтобы запустить необходимо проделать следующие шаги, установите [Git Bash](https://git-scm.com/)

1. Склонируйте репозиторий

```shell
git clone https://github.com/maksim25y/CaseTask.git
```

2. Скачайте и установите Docker

Скачать и найти инструкцию по установке вы можете на официальном сайте [Docker](https://www.docker.com)

3. Запустите приложение в Docker

Для этого запустите Docker, откройте терминал и перейдите в папку репозитория

```shell
cd CaseTask
```
Далее введите команду

```shell
docker-compose up
```
Готово! Сервер запущен.
Протестировать работу API можно в Postman по ссылке: localhost:8080

Чтобы остановить работу контейнеров, в терминале, откуда вы запускали docker-compose нажмите Ctrl+C (Control + C для Mac)
</details>
<details><summary>Функционал</summary>
Создание файла с корректными данными:

![image](https://github.com/user-attachments/assets/86022100-b244-42d1-ad50-5f586ad3c1c0)

Создание файла с некорректной датой:

![image](https://github.com/user-attachments/assets/a238beee-2974-4472-812f-0e086a43a074)

Создание файла с base64, который некорректен:

![image](https://github.com/user-attachments/assets/bf067b16-551d-4914-9427-4b3cd12e5db2)

Получение файла по существующему Id:

![image](https://github.com/user-attachments/assets/96544b45-f890-4157-9b57-05eaedb590f4)

Получение файла по несуществуеющему Id:

![image](https://github.com/user-attachments/assets/aa2ac678-366a-46f1-8d2c-4ccdc1585c91)

Получение всех файлов:

![image](https://github.com/user-attachments/assets/a40642d9-040e-428c-b437-e228b7503440)

Получение всех файлов с сортировкой по дате создания:

![image](https://github.com/user-attachments/assets/5f25bd0f-bc5b-46a2-a93e-10d9ebf909cc)

Получение всех файлов с пагинацией и без сортировки:

![image](https://github.com/user-attachments/assets/97603942-cf8d-4292-8470-ce536597ee5f)

![image](https://github.com/user-attachments/assets/57ee33fc-8248-4d9a-bb55-c596bd229a4f)

Получение всех файлов с пагинацией и c сортировкой:

![image](https://github.com/user-attachments/assets/123c6699-7a67-4f5d-aef0-162daa5b76af)

![image](https://github.com/user-attachments/assets/227b652b-3e39-4557-bd5b-fbbf5e051851)

</details>
<details><summary>Описание решения</summary>
Используемые технологии: Spring Boot, Spring Data JPA, Flyway, Docker, PostgreSQL, JUnit.
Был добавлен Dockerfile для сборки приложения:
  
![image](https://github.com/user-attachments/assets/928dc727-d18b-484a-bd15-61c1365e4f13)

Был добавлен docker-compose.yaml для запука приложения в контейнере. В нём же происходит создание БД. Чтение данных (Пароль к БД, Логин к БД и др) идёт из файла .env.docker.

![image](https://github.com/user-attachments/assets/4d597665-cddf-4232-af16-eb640382330d)



  
</details>
