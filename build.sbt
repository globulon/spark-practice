name := "spark-practice"
version := "0.0.2-SNAPSHOT"

scalaVersion := "2.11.12"

scalacOptions ++= Seq(
  "-encoding", "utf8", // Option and arguments on same line
  "-Xfatal-warnings",  // New lines for each options
  "-deprecation",
  "-unchecked",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-language:postfixOps")

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.2.26" withSources(),
  "org.apache.spark" %% "spark-sql" % "2.3.2" % Provided withSources() ,
  "org.scalactic" %% "scalactic" % "3.0.5" % Test withSources() ,
  "org.scalatest" %% "scalatest" % "3.0.5" % Test  withSources()
)

fork in Test := true

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.7")

resolvers ++= Seq(
  Resolver.typesafeRepo("releases"),
  Resolver.sonatypeRepo("releases")
)

assemblyJarName in assembly := "first-sample.jar"
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
assemblyExcludedJars in assembly := {
  val cp = (fullClasspath in assembly).value
  cp filter {_.data.getName contains  "scoverage"}
}

coverageEnabled := true
