## Валидатор данных
***
Библиотека для проверки корректности данных. Реализована проверка объектов типа String/Number/Map на соответствие заданным условиям:
* Валидацию строки (содержимое, минимальная длина, наличие заданных подстрок);
* Валидацию числа (числовой тип, знак числа, присутствие в заданном диапазоне);
* Валидацию объектов типа Map (тип данных карты, соответствие заданному размеру);
* Вложенную валидацию;
***
### Hexlet tests and linter status:
[![Actions Status](https://github.com/Alexandr-Kuzmin13/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/Alexandr-Kuzmin13/java-project-78/actions)
<a href="https://codeclimate.com/github/Alexandr-Kuzmin13/java-project-78/maintainability"><img src="https://api.codeclimate.com/v1/badges/5dd6276de5c3947e63b3/maintainability" /></a>
<a href="https://codeclimate.com/github/Alexandr-Kuzmin13/java-project-78/test_coverage"><img src="https://api.codeclimate.com/v1/badges/5dd6276de5c3947e63b3/test_coverage" /></a>

Примеры использования:

````
Validator v = new Validator();

StringSchema schema = v.string();

schema.isValid(""); // true
schema.isValid(null); // true

schema.required();

schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true
schema.isValid(null); // false
schema.isValid(""); // false

schema.contains("wh").isValid("what does the fox say"); // true
schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

schema.isValid("what does the fox say"); // false
````
````
Validator v = new Validator();

NumberSchema schema = v.number();

schema.isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(10) // true
schema.isValid("5"); // false

schema.positive().isValid(10); // true
schema.isValid(-10); // false

schema.range(5, 10);

schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false
````
````
Validator v = new Validator();

MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();

schema.isValid(null) // false
schema.isValid(new HashMap()); // true
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true
````
````
Validator v = new Validator();

MapSchema schema = v.map();

Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());
schema.shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "Maya");
human2.put("age", null);
schema.isValid(human2); // true

Map<String, Object> human3 = new HashMap<>();
human3.put("name", "");
human3.put("age", null);
schema.isValid(human3); // false

Map<String, Object> human4 = new HashMap<>();
human4.put("name", "Valya");
human4.put("age", -5);
schema.isValid(human4); // false
````
