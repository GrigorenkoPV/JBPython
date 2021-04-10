import sys


def print_vars() -> None:
    # `sys._getframe(n: int)` returns n-th (0-based indexing) frame in the current frame stack
    # print_vars's caller frame is frame 1, thus `sys._getframe(1)`
    # `frame.f_locals` is a dictionary of frame's local variables with their names as keys and values as values
    for var_name, var_value in sys._getframe(1).f_locals.items():
        # `__module__` attribute of a type object is a string with the name of the module where the type was defined
        # for built-in types, it is (unsurprisingly) "builtins"
        print(f"{var_name}: {type(var_value).__module__ == 'builtins'}")


# demo
if __name__ == '__main__':
    class MyClass:
        def __init__(self, n: int = 3):
            self.n = n


    def foo():
        a = 1  # built-in
        b = MyClass()  # user-defined
        c = [1, 2, 3]  # built-in
        print_vars()


    a = 3  # shadowing foo's local variable of a built-in type with a variable of a built-in type
    c = MyClass  # shadowing foo's local variable of a built-in type with a variable of a user-defined type
    foo()
