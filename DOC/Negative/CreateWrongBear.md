### Title
Проверка создания неправильного медведя.

### Description
Проверка создание медведя с некорректными данными

### Precondition
Запущен докер контейнер с сервисом медведей на localhost:8091
В сервисе нет записи с ID=147
### Step 1
Создадим новую запись

Выполнить команду

``POST http://localhost:8091/bear``

с телом bearInfo =
``{"bear_type":"BIPOLAR","bear_name":"mikhail","bear_age":17.5}``

#### Expected result
Статус ответа = 500

### Step 2
Повторим шаг 1, но с различными данными.
``{"bear_type":"BLACK","bear_name":"","bear_age":-1}
{"bear_type":"BROWN","bear_name":"Tor","bear_age":"5g"}
``

#### Expected result
Статус ответа = 500