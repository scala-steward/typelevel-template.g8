import Dependencies.Libraries

lazy val buildSettings = Seq(
  organization := "$organization$",
  startYear := Some($year$),
  licenses += ("MIT", new URL("https://opensource.org/licenses/MIT")),
  scalaVersion := "$scala_version_212$",
  crossScalaVersions := Seq(scalaVersion.value, "$scala_version_213$")
)

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

lazy val commonSettings =
  CompilerOptions.compilerOptions ++
    Seq(
      headerMappings := headerMappings.value + (HeaderFileType.scala -> HeaderCommentStyle.cppStyleLineComment),
      headerLicense := Some(HeaderLicense.MIT("$year$", "$contributorName$")),
      wartremoverErrors in (Compile, compile) := warts,
      wartremoverErrors in (Test, compile) := warts,
      scalacOptions in (Compile, doc) ++= Seq(
        "-groups",
        "-sourcepath",
        (baseDirectory in LocalRootProject).value.getAbsolutePath,
        "-doc-source-url",
        "https://github.com/$contributorUsername$/$name$/blob/v" + version.value + "€{FILE_PATH}.scala"
      ),
      libraryDependencies ++= Seq(
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

lazy val contributors = Seq(
  "$contributorUsername$" -> "contributorName"
)

lazy val publishSettings = Seq(
  publishMavenStyle := true,
  publishArtifact in Test := false,
  homepage := Some(url("https://github.com/$contributorUsername$/$name$")),
  pomIncludeRepository := { _ =>
    false
  },
  pomExtra := {
    <developers>
      {for ((username, name) <- contributors) yield
      <developer>
        <id>
          {username}
        </id>
        <name>
          {name}
        </name>
        <url>http://github.com/
          {username}
        </url>
      </developer>}
    </developers>
  },
  resolvers += "Apache public".at("https://repository.apache.org/content/groups/public/"),
  publishTo := {
    val sonatype = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots".at(sonatype + "content/repositories/snapshots"))
    else
      Some("releases".at(sonatype + "service/local/staging/deploy/maven2"))
  },
  credentials ++= (
    for {
      username <- Option(System.getenv().get("SONATYPE_USERNAME"))
      password <- Option(System.getenv().get("SONATYPE_PASSWORD"))
    } yield
      Credentials(
        "Sonatype Nexus Repository Manager",
        "oss.sonatype.org",
        username,
        password
      )
  ).toSeq,
  releasePublishArtifactsAction := PgpKeys.publishSigned.value,
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/$contributorUsername$/$name$"),
      "git@github.com:$contributorUsername$/$name$.git"
    )
  )
)

lazy val skipOnPublishSettings = Seq(
  skip in publish := true,
  publish := (()),
  publishLocal := (()),
  publishArtifact := false,
  publishTo := None
)

lazy val releaseSettings = {
  import ReleaseTransformations._
  Seq(
    releaseCrossBuild := true,
    releaseProcess := Seq[ReleaseStep](
      checkSnapshotDependencies,
      releaseStepCommand("mimaReportBinaryIssues"),
      inquireVersions,
      runClean,
      runTest,
      setReleaseVersion,
      commitReleaseVersion,
      tagRelease,
      // For non cross-build projects, use releaseStepCommand("publishSigned")
      releaseStepCommandAndRemaining("+publishSigned"),
      setNextVersion,
      commitNextVersion,
      releaseStepCommand("sonatypeReleaseAll"),
      releaseStepCommand("docs/publishMicrosite"),
      pushChanges
    )
  )
}

lazy val mimaSettings = {
  import sbtrelease.Version

  def semverBinCompatVersions(major: Int, minor: Int, patch: Int): Set[(Int, Int, Int)] = {
    val majorVersions: List[Int] =
      if (major == 0 && minor == 0) List.empty[Int]
      else List(major)
    val minorVersions: List[Int] =
      if (major >= 1) Range(0, minor).inclusive.toList
      else List(minor)

    def patchVersions(currentMinVersion: Int): List[Int] =
      if (minor == 0 && patch == 0) List.empty[Int]
      else if (currentMinVersion != minor) List(0)
      else Range(0, patch - 1).inclusive.toList

    val versions = for {
      maj <- majorVersions
      min <- minorVersions
      pat <- patchVersions(min)
    } yield (maj, min, pat)
    versions.toSet
  }

  def mimaVersions(version: String): Set[String] = {
    Version(version) match {
      case Some(Version(major, Seq(minor, patch), _)) =>
        semverBinCompatVersions(major.toInt, minor.toInt, patch.toInt)
          .map { case (maj, min, pat) => maj.toString + "." + min.toString + "." + pat.toString }
      case _ =>
        Set.empty[String]
    }
  }

  // Safety Net For Exclusions
  lazy val excludedVersions: Set[String] = Set()

  // Safety Net for Inclusions
  lazy val extraVersions: Set[String] = Set()

  Seq(
    mimaFailOnProblem := mimaVersions(version.value).toList.headOption.isDefined,
    mimaPreviousArtifacts := (mimaVersions(version.value) ++ extraVersions)
      .filterNot(excludedVersions.contains(_))
      .map { v =>
        val moduleN = moduleName.value + "_" + scalaBinaryVersion.value.toString
        organization.value % moduleN % v
      },
    mimaBinaryIssueFilters ++= {
      import com.typesafe.tools.mima.core._
      import com.typesafe.tools.mima.core.ProblemFilters._
      Seq()
    }
  )
}

lazy val `typelevel-template-g8-example` =
  (project in file("."))
    .aggregate(core, docs)
    .settings(buildSettings, commonSettings, testSettings, skipOnPublishSettings, releaseSettings)

lazy val core = project
  .in(file("core"))
  .enablePlugins(AutomateHeaderPlugin)
  .settings(buildSettings, commonSettings, testSettings, publishSettings, mimaSettings)
  .settings(
    name := "$name$",
    description := "$project_description$",
    libraryDependencies ++= Seq(
      Libraries.cats,
      Libraries.catsEffect
    )
  )

lazy val docs = project
  .in(file("docs"))
  .dependsOn(core)
  .enablePlugins(TutPlugin)
  .enablePlugins(MicrositesPlugin)
  .settings(buildSettings, commonSettings, testSettings, skipOnPublishSettings, micrositeSettings)

lazy val micrositeSettings = {
  import microsites._
  Seq(
    micrositeName := "$name$",
    micrositeDescription :=
      "$project_description$",
    micrositeAuthor := "$contributorName$",
    micrositeGithubOwner := "$contributorUsername$",
    micrositeGithubRepo := "$name$",
    micrositeBaseUrl := "/$name$",
    micrositeDocumentationUrl := "https://www.javadoc.io/doc/$organization$/$name$_2.12",
    micrositeDocumentationLabelDescription := "API Documentation",
    micrositeGitterChannel := true,
    micrositeGitterChannelUrl := "$organization$/$name$",
    micrositeHighlightTheme := "atom-one-light",
    micrositePalette := Map(
      "brand-primary"   -> "#5B5988",
      "brand-secondary" -> "#292E53",
      "brand-tertiary"  -> "#222749",
      "gray-dark"       -> "#49494B",
      "gray"            -> "#7B7B7E",
      "gray-light"      -> "#E5E5E6",
      "gray-lighter"    -> "#F4F3F4",
      "white-color"     -> "#FFFFFF"
    ),
    fork in tut := true,
    scalacOptions in Tut --= Seq(
      "-Xfatal-warnings",
      "-Ywarn-unused-import",
      "-Ywarn-numeric-widen",
      "-Ywarn-dead-code",
      "-Xlint:-missing-interpolator,_"
    ),
    scalacOptions in Tut ++= Seq("-Ydelambdafy:inline"),
    micrositePushSiteWith := GitHub4s,
    micrositeGithubToken := sys.env.get("GITHUB_TOKEN"),
    micrositeExtraMdFiles := Map(
      file("CHANGELOG.md") -> ExtraMdFileConfig(
        "changelog.md",
        "page",
        Map("title" -> "changelog", "section" -> "changelog", "position" -> "100")),
      file("CODE_OF_CONDUCT.md") -> ExtraMdFileConfig(
        "code-of-conduct.md",
        "page",
        Map("title" -> "code of conduct", "section" -> "code of conduct", "position" -> "101")),
      file("LICENSE") -> ExtraMdFileConfig(
        "license.md",
        "page",
        Map("title" -> "license", "section" -> "license", "position" -> "102"))
    )
  )
}
