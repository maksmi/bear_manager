### Title
Проверка изменения корректной записи о медведе на не корректную.
### Description
Проверка изменения корректной записи о медведе на не корректную.
### Precondition
Запущен докер контейнер с сервисом медведей на localhost:8091.
В сервисе присутствует корректная запись с ID = bearID.


### Step 1
Выполним изменение записи на некорректную.

Выполнить команду

``PUT http://localhost:8091/bear/:bearID``

с телом newBearInfo =
``{"bear_type":"BIPOLAR","bear_name":"wrong","bear_age":"47.5"}``

#### Expected result
Статус ответа = 500

### Step 2
Повторим шаг 1, но с различными данными.
``{"bear_type":"BLACK","bear_name":"","bear_age":-1}
{"bear_type":"BROWN","bear_name":"Tor","bear_age":"5g"}
{"bear_name":"Tor","bear_age":"5"}
{"bear_type":"BROWN","bear_age":"10"}
{"bear_type":"POLAR","bear_name":"Tor"}
``

#### Expected result
Статус ответа = 500


