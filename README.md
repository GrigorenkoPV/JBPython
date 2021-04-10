# Type Renreders [sic] for Python

## [Задача 1](Task1.py)

Нужно реализовать функцию `print_vars()` на Python, которая будет анализировать локальные переменныев области видимости,
из которой её вызвали. Для каждой переменной нужно распечатать её имя и проверку, является ли она стандартным built-in
типом или нет.

Например, при таком вызове:

```python
def foo():
    a = 1
    b = MyClass()
    c = [1, 2, 3]
    d = pandas.read_csv("my_file.csv")
    print_vars()
```

Она должна будет распечатать:

```
a: True
b: False
c: True
d: False
```

Потому что `int` - это built-in тип, `MyClass` - пользовательский класс, `list` - это тоже built-in тип,
а `pandas.DataFrame` - хоть и библиотечный, но всё ещё не built-in тип.

Подсказка: получить доступ к локальным переменным с их именами и значениями в текущем фрейме можно с помощью метода
[sys._getframe()](https://docs.python.org/3/library/sys.html#sys._getframe)
