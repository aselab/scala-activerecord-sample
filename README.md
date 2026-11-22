## Sample Heroku URL (Play! 2.x sample)

http://play2-scala-activerecord.herokuapp.com/

## Scala ActiveRecord sample

The Scalatra and Play! samples still use scala-activerecord 0.6.2 on Scala 2.13, as 0.7.0 does not include the
Scalatra and Play! 2 modules.

### Console application samples

These samples use scala-activerecord 0.7.0-SNAPSHOT on Scala 3.7. Running them needs JDK 17 or later (sbt 2).

```
cd sample/
sbt
> run
```

### Scalatra sample

```
cd scalatra/
sbt
> jetty:start
```

The application starts on http://localhost:8080.

### Scalatra sample (multiple-schema)

```
cd scalatra-multiple-schema/
sbt
> jetty:start
```

The application starts on http://localhost:8080.

### Play! 2.8 sample

```
cd play2x/
sbt
> run
```

The application starts on http://localhost:9000.

### Play! 2.8 sample (multiple-schema)

```
cd play2x-multiple-schema/
sbt
> run
```

The application starts on http://localhost:9000.
