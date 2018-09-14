/*
scalafmt: {
  style = defaultWithAlign
  maxColumn = 150
  align.tokens = [
    { code = "=>", owner = "Case" }
    { code = "?", owner = "Case" }
    { code = "extends", owner = "Defn.(Class|Trait|Object)" }
    { code = "//", owner = ".*" }
    { code = "{", owner = "Template" }
    { code = "}", owner = "Template" }
    { code = ":=", owner = "Term.ApplyInfix" }
    { code = "++=", owner = "Term.ApplyInfix" }
    { code = "+=", owner = "Term.ApplyInfix" }
    { code = "%", owner = "Term.ApplyInfix" }
    { code = "%%", owner = "Term.ApplyInfix" }
    { code = "%%%", owner = "Term.ApplyInfix" }
    { code = "->", owner = "Term.ApplyInfix" }
    { code = "?", owner = "Term.ApplyInfix" }
    { code = "<-", owner = "Enumerator.Generator" }
    { code = "?", owner = "Enumerator.Generator" }
    { code = "=", owner = "(Enumerator.Val|Defn.(Va(l|r)|Def|Type))" }
  ]
}
 */

val ammoniteVersion  = "1.1.2"
val circeVersion     = "0.9.3"
val commonsVersion   = "0.10.22"
val scalaTestVersion = "3.0.5"
val akkaVersion      = "2.5.14"
val serviceVersion   = "0.10.15"

lazy val commonsTest = nexusDep("commons-test", commonsVersion)
lazy val serviceHttp = nexusDep("service-http", serviceVersion)

lazy val root = project
  .in(file("."))
  .settings(common, noPublish)
  .settings(
    name        := "data-generator",
    moduleName  := "data-generator",
    description := "Nexus Provenance Data Generator",
    fork in run := true,
    libraryDependencies ++= Seq(
      commonsTest,
      serviceHttp,
      "com.lihaoyi"   %% "ammonite-ops" % ammoniteVersion,
      "io.circe"      %% "circe-core"   % circeVersion,
      "io.circe"      %% "circe-parser" % circeVersion,
      "io.circe"      %% "circe-parser" % circeVersion,
      "org.scalatest" %% "scalatest"    % scalaTestVersion % Test
    )
  )
lazy val noPublish = Seq(publishLocal := {}, publish := {})

lazy val common = Seq(scalacOptions in (Compile, console) ~= (_ filterNot (_ == "-Xfatal-warnings")))

def nexusDep(name: String, version: String): ModuleID =
  "ch.epfl.bluebrain.nexus" %% name % version
