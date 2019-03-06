import sbt._

object Dependencies {

  object Versions {
    val catsV             = "1.5.0"
    val scalatestV        = "3.0.5"
    val catsEffectV       = "1.0.0"
    val scalacheckV       = "1.14.0"
    val kindProjectorV    = "0.9.8"
    val betterMonadicForV = "0.3.0-M4"
  }

  object Libraries {
    lazy val cats       = "org.typelevel"  %% "cats-core"   % Versions.catsV
    lazy val catsEffect = "org.typelevel"  %% "cats-effect" % Versions.catsEffectV
    lazy val scalatest  = "org.scalatest"  %% "scalatest"   % Versions.scalatestV
    lazy val scalacheck = "org.scalacheck" %% "scalacheck"  % Versions.scalacheckV

  }
}
