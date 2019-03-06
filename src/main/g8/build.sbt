import sbt.Resolver

lazy val root = (project in file(".")).settings(
  name := "$name$",
  organization := "$organization$",
  scalaVersion := "$scala_version$",
  version := "$version$",
  libraryDependencies ++= Seq(
    Libraries.scalatest % Test,
    Libraries.cats,
    Libraries.catsEffect
  ),
  scalacOptions ++= Seq(
    "-deprecation", // Warn about use of deprecated APIs
    "-encoding",
    "UTF-8", // Source files are in UTF-8
    "-explaintypes", // Explain type errors in more detail.
    "-feature", // Warn about misused language features
    "-language:existentials", // Existential types (besides wildcard types) can be written and inferred
    "-language:higherKinds", // Allow higher kinded types without `import scala.language.higherKinds`
    "-unchecked", // Warn about unchecked type parameters
    "-Xfatal-warnings", // Turn compiler warnings into errors
    "-Xlint", // Enable handy linter warnings
    "-Ypartial-unification", // Allow the compiler to unify type constructors of different arities
  ),
  addCompilerPlugin("org.spire-math" %% "kind-projector"     % Versions.kindProjectorV),
  addCompilerPlugin("com.olegpy"     %% "better-monadic-for" % Versions.betterMonadicForV)
)
