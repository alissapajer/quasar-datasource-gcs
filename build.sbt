ThisBuild / crossScalaVersions := Seq("2.12.10")
ThisBuild / scalaVersion := (ThisBuild / crossScalaVersions).value.head

ThisBuild / githubRepository := "quasar-datasource-gcs"

ThisBuild / homepage := Some(url("https://github.com/precog/quasar-datasource-gcs"))

ThisBuild / scmInfo := Some(ScmInfo(
  url("https://github.com/precog/quasar-datasource-gcs"),
  "scm:git@github.com:precog/quasar-datasource-gcs.git"))

ThisBuild / publishAsOSSProject := true

// Include to also publish a project's tests
lazy val publishTestsSettings = Seq(
  Test / packageBin / publishArtifact := true)

lazy val quasarVersion =
  Def.setting[String](managedVersions.value("precog-quasar"))

val Specs2Version = "4.9.4"
val SLF4SVersion = "1.7.25"

lazy val root = project
  .in(file("."))
  .settings(noPublishSettings)
  .aggregate(datasource)

lazy val datasource = project
  .in(file("datasource"))
  .settings(
    name := "quasar-datasource-gcs",
    quasarPluginName := "gcs",
    quasarPluginQuasarVersion := quasarVersion.value,
    quasarPluginDatasourceFqcn := Some("quasar.plugin.gcs.datasource.GCSDatasourceModule$"),
    quasarPluginDependencies ++= Seq(
      "org.slf4s" %% "slf4s-api" % SLF4SVersion),
    libraryDependencies ++= Seq(
      "org.specs2" %% "specs2-core" % Specs2Version % Test))
  .enablePlugins(QuasarPlugin)
