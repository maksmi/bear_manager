### Title
Проверка изменения записи о медведе.
### Description
Проверка изменения записи о медведе.
### Precondition
Запущен докер контейнер с сервисом медведей на localhost:8091
В сервисе присутствует запись с ID = bearID и значениями:

oldBearInfo=``{"bear_type":"BROWN","bear_name":"test bear","bear_age":"5"}``

### Step 1
Выполним изменение записи
Выполнить команду

``PUT http://localhost:8091/bear/:bearID``

с телом newBearInfo =
``{"bear_type":"BLACK","bear_name":"new bear","bear_age":"47.5"}``

#### Expected result
Статус ответа = 200

### Step 2
Проверим запись.
Выполнить команду

``GET http://localhost:8091/bear/:bearID``

Сохранить ответ в newBearInfo

#### Expected result
Статус ответа = 200
body ответа содержит json c информаций о записи

### Step 3
Сравним информацио о медведе.

#### Expected result
Информация записанная в сервис совпадает с полученной
oldBearInfo == newBearInfo
