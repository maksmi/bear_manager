### Title
Проверка создания медведя с пропущенными данными.
### Description
Проверка создание медведя с пропущенными данными.
### Precondition
Запущен докер контейнер с сервисом медведей на localhost:8091
### Step 1
Создадим новую запись

Выполнить команду

``POST http://localhost:8091/bear``

с телом bearInfo =
``{"bear_name":"bearname","bear_age":17.5}``

#### Expected result
Статус ответа = 200
body содержит "Error. Pls fill all parameters"

### Step 2
Повторим шаг 1, но с различными данными.
``{"bear_type":"BLACK","bear_age":5}
{"bear_type":"BROWN","bear_name":"Tor"}
``

#### Expected result
Статус ответа = 200
body содержит "Error. Pls fill all parameters"