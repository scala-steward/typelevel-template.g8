# Typelevel Giter8 Template 

[![Build Status](https://travis-ci.com/olivierschultz/typelevel-template.g8.svg?branch=master)](https://travis-ci.com/olivierschultz/typelevel-template.g8)
[![codecov](https://codecov.io/gh/olivierschultz/typelevel-template/branch/master/graphs/badge.svg)](https://codecov.io/gh/olivierschultz/typelevel-template)
<a href="https://typelevel.org/cats/"><img src="https://typelevel.org/cats/img/cats-badge.svg" height="40px" alt="Cats friendly" /></a>

This is a [Giter8](http://www.foundweekends.org/giter8/) template to start new Typelevel Scala Projects based on Cats v1.x.x.

__Prerequisites__:
* JDK11+
* Giter8 0.11.0
* Scala 2.12.x (also compatible with scala 2.13.0)

### Features

|                           | light (`light-features`) | medium (`master`)  | heavy (`heavy-features`) |
|---------------------------|--------------------------|--------------------|--------------------------|
| scalafmt                  | :heavy_check_mark:       | :heavy_check_mark: | :heavy_check_mark:       |
| sbt-tpolecat              | :heavy_check_mark:       | :heavy_check_mark: | :heavy_check_mark:       |
| sbt-explicit-dependencies |                          | :heavy_check_mark: | :heavy_check_mark:       |
| sbt-wartremover           |                          | :heavy_check_mark: | :heavy_check_mark:       |
| sbt-scoverage             |                          | :heavy_check_mark: | :heavy_check_mark:       |
| TravisCI                  |                          | :heavy_check_mark: | :heavy_check_mark:       |
| sbt-header                |                          |                    | :heavy_check_mark:       |
| tut-plugin                |                          |                    | :heavy_check_mark:       |
| sbt-microsites            |                          |                    | :heavy_check_mark:       |
| sbt-release               |                          |                    | :heavy_check_mark:       |
| sbt-pgp                   |                          |                    | :heavy_check_mark:       |
| sbt-sonatype              |                          |                    | :heavy_check_mark:       |

## Usage

* Install [sbt](https://www.scala-sbt.org/1.x/docs/Setup.html).
* Run the following command:

_sbt:_
```
sbt new olivierschultz/typelevel-scala-template.g8 -b <branch-name>
```
_or_
```
g8 https://github.com/olivierschultz/typelevel-scala-template.g8 -b <branch-name>
```

## Template license

Written in 2019 by [Olivier Schultz](https://github.com/olivierschultz).

To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this 
template to the public domain worldwide. This template is distributed without any warranty. 
See http://creativecommons.org/publicdomain/zero/1.0/.