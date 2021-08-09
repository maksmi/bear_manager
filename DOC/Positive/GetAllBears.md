### Title
Проверка получения всех записей.
### Description
Проверка получения всех записей.
### Precondition
Запущен докер контейнер с сервисом медведей на localhost:8091
### Step 1
Создадим новую запись
Выполнить команду

``POST http://localhost:8091/bear``

с телом bearInfo =
``{"bear_type":"BLACK","bear_name":"mikhail","bear_age":17.5}``


#### Expected result
Статус ответа = 201

### Step 2
Создадим новую запись
Выполнить команду

``POST http://localhost:8091/bear``

с телом bearInfo =
``{"bear_type":"BROWN","bear_name":"Tor","bear_age":1.5}``

#### Expected result
Статус ответа = 201

### Step 3
Проверим записи.
Выполнить команду

``GET http://localhost:8091/bear``

#### Expected result
Статус ответа = 200
body ответа содержит json c информаций о 2-х записях.
Записи из шага 1 и 2 присутствуют в body.
