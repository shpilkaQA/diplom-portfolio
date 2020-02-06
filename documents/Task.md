# Дипломный проект профессии «Тестировщик»

Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

## Описание приложения

### Бизнес часть

Приложение представляет из себя веб-сервис.

![](/documents/pic/service.png)

Приложение предлагает купить тур по определённой цене с помощью двух способов:
1. Обычная оплата по дебетовой карте
1. Уникальная технология: выдача кредита по данным банковской карты

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:
* сервису платежей (далее - Payment Gate)
* кредитному сервису (далее - Credit Gate)

Приложение должно в собственной СУБД сохранять информацию о том, каким способом был совершён платёж и успешно ли он был совершён (при этом данные карт сохранять не допускается).

*Важно: в реальной жизни приложение не должно через себя даже пропускать данные карт, если у него нет PCI DSS, но мы сделали именно так 😈.*

### Техническая часть

Само приложение расположено в файле [`aqa-shop.jar`](/artifacts/aqa-shop.jar) и запускается стандартным способом `java -jar aqa-shop.jar` на порту 8080.

В файле `application.properties` приведён ряд типовых настроек:
* учётные данные и url для подключения к СУБД
* url-адреса банковских сервисов

### СУБД

Заявлена поддержка двух СУБД (вы это должны проверить):
* MySQL
* PostgreSQL

Учётные данные и url для подключения задаются в файле `application.properties`.

### Банковские сервисы

Доступа к живым банковским сервисам вам не дают, поэтому разработчики подготовили симулятор банковских сервисов, который может принимать запросы в нужном формате и генерировать ответы.

Симулятор написан на Node.js, поэтому для запуска вам нужен либо Docker, либо установленный Node.js. Симулятор расположен в каталоге [gate-simulator](gate-simulator). Для запуска необходимо перейти в этот каталог. 

Запускается симулятор командой `npm start` на порту 9999.

Симулятор позволяет для заданного набора карт генерировать предопределённые ответы.

Набор карт представлен в формате JSON в файле [`data.json`](/gate-simulator/data.json).

Обратите внимание, разработчики сделали один сервис, симулирующий и Payment Gate, и Credit Gate.

## Задача

Ваша ключевая задача - автоматизировать сценарии (как позитивные, так и негативные) покупки тура.

Задача разложена на 4 этапа:
1. Планировании автоматизации тестирования
1. Непосредственно самой автоматизации
1. Подготовке отчётных документов по итогам автоматизированного тестирования
1. Подготовка отчётных документов по итогам автоматизации

Все материалы (документы, авто-тесты, открытые issue, отчёты и т.д.) должны быть размещены в одном публичном репозитории, ссылку на который вы и будете отправлять дипломному руководителю.