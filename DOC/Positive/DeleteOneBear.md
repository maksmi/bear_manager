### Title
Проверка удаления одной записи.
### Description
Проверка удаления одной записи.
### Precondition
Запущен докер контейнер с сервисом медведей на localhost:8091
В сервисе присутствует N записей
### Step 1
Создадим новую запись

Выполнить команду

``POST http://localhost:8091/bear``

с телом bearInfo =
``{"bear_type":"BLACK","bear_name":"mikhail","bear_age":17.5}``

Сохранить ответ в bearID

#### Expected result
Статус ответа = 200
body ответа содержит ID созданой записи

### Step 2
Проверим запись.

Выполнить команду

``GET http://localhost:8091/bear/:bearID``

#### Expected result
Статус ответа = 200
body ответа содержит json c информаций о записи

### Step 3
Удалим одну запись

Выполнить команду

``DELETE http://localhost:8091/bear/:bearID``

#### Expected result
Статус ответа = 200

### Step 4
Проверим количество записей в сервисе

Выполнить команду

``GET http://localhost:8091/bear``
#### Expected result
Статус ответа = 200
body содердит только N записей