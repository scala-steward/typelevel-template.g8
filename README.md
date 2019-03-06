# Typelevel Giter8 Template 

[![Build Status](https://travis-ci.com/olivierschultz/typelevel-template.g8.svg?branch=master)](https://travis-ci.com/olivierschultz/typelevel-template.g8)
[![codecov](https://codecov.io/gh/olivierschultz/typelevel-template/branch/master/graphs/badge.svg)](https://codecov.io/gh/olivierschultz/typelevel-template)<a href="https://typelevel.org/cats/"><img src="https://typelevel.org/cats/img/cats-badge.svg" height="40px" align="right" alt="Cats friendly" /></a>

This is a [Giter8](http://www.foundweekends.org/giter8/) template to start new Typelevel Scala Projects based on Cats v1.x.x.

__Prerequisites__:
* JDK8+
* Giter8 0.11.0
* Scala 2.12.x (also compatible with 2.13.0-M5)

## Usage

* Install [sbt](https://www.scala-sbt.org/1.x/docs/Setup.html).
* Run the following command:

_sbt:_
```
sbt new olivierschultz/typelevel-scala-template.g8
```
_or_
```
g8 https://github.com/olivierschultz/typelevel-scala-template.g8
```
## Setup

### Github Pages

1. Initial gh-pages branch using the directions from [sbt-ghpages](https://github.com/sbt/sbt-ghpages/blob/7e2ee06180a5a955a89659915ec8cd75ea28147e/README.md#initializing-the-gh-pages-branch).

2. Go into Travis-CI.com, there you will need to configure and enter a value for environment variable `GITHUB_TOKEN` with a credential that can access your account. [Creating A Token](https://help.github.com/en/articles/creating-a-personal-access-token-for-the-command-line).

### Sonatype Publishing

1. Setup Sonatype Account if you have not already. Good documentation on the process can be found on the [sbt-release-early-wiki](https://github.com/scalacenter/sbt-release-early/wiki/How-to-release-with-Sonatype#you-dont-have-a-sonatype-account).

2. Go into Travis-CI.com, there you will need to configure and enter values for environment variables `SONATYPE_USERNAME` and `SONATYPE_PASSWORD` for publishing SNAPSHOTS to sonatype.

### Changelog Generator

I highly recommend to use a changelog generator with Github Release to make it easier for users and contributors to see precisely what notable changes have been made between each release (or version) of the project.

Check out [github-changelog-generator](https://github.com/github-changelog-generator/github-changelog-generator#installation).

## Inspiration

This template gathers best practices from several typelevel projects. And also:

* [library.g8](https://github.com/ChristopherDavenport/library.g8) by [Christopher Davenport](https://github.com/ChristopherDavenport).

## Template license

Written in 2019 by [Olivier Schultz](https://github.com/olivierschultz).

To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this 
template to the public domain worldwide. This template is distributed without any warranty. 
See http://creativecommons.org/publicdomain/zero/1.0/.
