import Dependencies.Libraries

name := """$name$"""

organization in ThisBuild := "$organization$"

crossScalaVersions in ThisBuild := Seq("$scala_version_211$", scalaVersion.value, "$scala_version_213$")

lazy val warts = Warts.allBut(
  Wart.Any,
  Wart.ArrayEquals,
  Wart.Nothing,
  Wart.Product,
  Wart.Serializable,
  Wart.Overloading,
  Wart.NonUnitStatements,
  Wart.ImplicitConversion,
  Wart.PublicInference,
  Wart.ImplicitParameter
)

lazy val commonSettings = Seq(
  organizationName := "$organization$",
  wartremoverErrors in (Compile, compile) := warts,
  wartremoverErrors in (Test, compile) := warts,
  libraryDependencies ++= Seq(
    Libraries.cats,
    Libraries.catsEffect,
    Libraries.scalaTest  % Test,
    Libraries.scalaCheck % Test,
    compilerPlugin(Libraries.kindProjector),
    compilerPlugin(Libraries.betterMonadicFor)
  )
)

lazy val testSettings = Seq(
  fork in Test := true,
  parallelExecution in Test := false
)

lazy val `$name$-root` =
  (project in file("."))
    .aggregate(`$name$-core`)

lazy val `$name$-core` = project
  .in(file("core"))
  .settings(commonSettings)
  .settings(testSettings)
