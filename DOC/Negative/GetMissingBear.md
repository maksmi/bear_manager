### Title
Проверка получения записи о несуществующем медведе.
### Description
Проверка получения записи о несуществующем медведе.
### Precondition
Запущен докер контейнер с сервисом медведей на localhost:8091
### Step 1
Проверим запись.

Выполнить команду

``GET http://localhost:8091/bear/147``


#### Expected result
Статус ответа = 200
body = EMPTY
