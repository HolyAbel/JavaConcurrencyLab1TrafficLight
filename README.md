# Лабораторная № 1 по дисциплине "Параллельное программирование"
## Задание №2. Описание.
Перекресток. Автомобили движутся в заданных направлениях. Подъезжая к перекрестку, машины встают в очередь. На перекрестке стоит интелектуальный светофор и разрешает ехать одновременно тем машинам, траектории которых не имеют пересечения. У перекрестка четыре направления: N,W,S,E. Светофор может разрешить движение по любым непересекающимся комбинациям направлений, например, NS,SE.

![Перекресток](https://github.com/HolyAbel/JavaConcurrencyLab1TrafficLight/blob/master/trafficlight.JPG)

## Реализация задачи
Машина представлена классом Car. Каждая машина имеет направление движения. Создаются 4 машины в соответствии с заданием.

За разрешение движения на перекрестке отвечает класс TrafficLight. Когда более 1 машины ожидают на перекрестке, светофор определяет разрешеные направления движения, а затем соответствующие машины проезжают перекресток. Через некоторое время машина, соответствующая той, что проехала, подъезжает к перекрестку снова.

## Пример работы программы
![Результат](https://github.com/HolyAbel/JavaConcurrencyLab1TrafficLight/blob/master/res.JPG)
