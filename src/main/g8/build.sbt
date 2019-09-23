import Dependencies.Libraries

name := """$name$"""

organization in ThisBuild := "$organization$"

scalaVersion in ThisBuild := "$scala_version_212$"

crossScalaVersions in ThisBuild := Seq("$scala_version_212$", "$scala_version_213$")

lazy val commonSettings = Seq(
  organizationName := "$organization$",
  scalafmtOnCompile := true,
  libraryDependencies ++= Seq(
    Libraries.cats,
    Libraries.catsEffect,
    Libraries.scalaTest  % Test,
    Libraries.scalaCheck % Test,
    compilerPlugin(Libraries.kindProjector),
    compilerPlugin(Libraries.betterMonadicFor)
  )
)

lazy val `$name$-root` =
  (project in file("."))
    .aggregate(`$name$-core`)

lazy val `$name$-core` = project
  .in(file("core"))
  .settings(commonSettings: _*)
