# A Simple DSL Template
[condition]If there is a Person with name of "{name}"=Person(name=="{name}")
[condition]Person is at least {age} years old and lives in "{location}"=Person(age >= {age}, location=="{location}")
[condition]And = and
[condition]get All Messages=message : Message()
[consequence]Say "{message}"=System.out.println("{message}");insert(new Message("{message}"));

[condition]When there is a person living in a place with name that sounds like "{poorlySpelledLocation}"=Person(location soundslike "{poorlySpelledLocation}")
