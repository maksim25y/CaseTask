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

#### Переменные окружения в .env

Описание:
1. POSTGRES_USER - логин для БД
2. POSTGRES_PASSWORD - пароль для БД
3. SPRING_DATASOURCE_URL - адрес БД
4. SPRING_DATASOURCE_USERNAME - логин для БД, но для Spring
5. SPRING_DATASOURCE_PASSWORD - пароль для БД, но для Spring

С помощью Flyway была добавлена миграция:

![image](https://github.com/user-attachments/assets/0fdcdce5-73a8-41a1-88e1-9eac492b476f)

Была создана модель файла для сохранения в БД:

![image](https://github.com/user-attachments/assets/c28bad18-dd0f-441d-af03-2342d4657274)

Был создан репозиторий для выполнения операция с использованием JPA. 
В том числе был добавлен метод для получения файлов с сортировкой по дате создания:

![image](https://github.com/user-attachments/assets/97a2d0d3-c7e7-4eeb-8471-7bebba7f2442)

Был создан FileDTO. Объект, который передаётся в JSON:

![image](https://github.com/user-attachments/assets/9384c919-51b9-4551-b2eb-eeba206bd7d6)

Был добавлен FileService. Данный класс связывает JPA с контроллером. 
Был добавлен метод для сохранения файла в БД. Добавлена обработка исключений на случай, если некорректна дата или файл в base64:

![image](https://github.com/user-attachments/assets/67e61cee-fb0f-4abd-912b-c2c2b70f9569)

Был добавлен метод конвертации файла из base64 в набор байтов:

![image](https://github.com/user-attachments/assets/bb64fc27-b2c1-4a70-a7f2-16408776b4c5)

Был добавлен метод для конвертаци набора байтов в base64:

![image](https://github.com/user-attachments/assets/d9f49125-822c-4a1a-982c-67f08107a169)

Добавлен метод для получения файла по id:

![image](https://github.com/user-attachments/assets/9f412026-f1d6-4457-8457-734f58552f12)

Добавлен метод для перевода FileModel в FileDTO:

![image](https://github.com/user-attachments/assets/f817ebb2-8e48-4b22-83e4-a911aa23fbc6)

Добавлен метод для получения файлов с сортировкой по дате создания:

![image](https://github.com/user-attachments/assets/d5a3b7e2-9999-473d-a8bd-41905a22dc87)

Добавлен метод для получения файлов с пагинацией и сортировкой по дате:

![image](https://github.com/user-attachments/assets/d73aaa09-1156-42e9-9e0f-3008ed02991f)

Создан контроллер для обработки запросов.
Обработчик POST запросов для создания файла:

![image](https://github.com/user-attachments/assets/903306b2-7b02-4f95-8379-695ebf10a5b7)

Обработчик GET запросов на получение файла по его ID:

![image](https://github.com/user-attachments/assets/e3f1e517-d269-4dbc-b8d7-f6ebd2ced80c)

Обработчик GET запросов на получение всех файлов (с сортировкой, пагинацией):

![image](https://github.com/user-attachments/assets/3e09e3ec-42e3-49d3-9686-4473e5db6b93)


</details>
