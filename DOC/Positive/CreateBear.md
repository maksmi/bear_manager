### Title
Проверка создания медведя.
### Description
Проверка создание медведя
### Precondition
Запущен докер контейнер с сервисом медведей на localhost:8091
### Step 1
Создадим новую запись
Выполнить команду

``POST http://localhost:8091/bear``

с телом bearInfo =
``{"bear_type":"BLACK","bear_name":"mikhail","bear_age":17.5}``

Сохранить ответ в bearID

#### Expected result
Статус ответа = 201
body ответа содержит ID созданной записи

### Step 2
Проверим запись.
Выполнить команду

``GET http://localhost:8091/bear/:bearID``

Сохранить ответ в newBear

#### Expected result
Статус ответа = 200
body ответа содержит json c информаций о записи

### Step 3
Сравним информацио о медведе.

#### Expected result
Информация записанная в сервис совпадает с полученной
bearInfo == newBear

### Step 4
Повторим шаги 1-3 но с различными данными.
``{"bear_type":"BLACK","bear_name":"mikhail","bear_age":0}
{"bear_type":"BROWN","bear_name":"Tor","bear_age":0.1}
{"bear_type":"POLAR","bear_name":"Bor","bear_age":999}
{"bear_type":"BIPOLAR","bear_name":"mcNikel","bear_age":10}
{"bear_type":"GUMMY","bear_name":"alPikel","bear_age":20}
``

#### Expected result
Информация записанная в сервис совпадает с полученной
bearInfo == newBear