name := "test_titan_mngm_2"

version := "1.0"

scalaVersion := "2.11.2"

//autoScalaLibrary := true

resolvers ++= Seq(
  "snapshots"           at "http://oss.sonatype.org/content/repositories/snapshots",
  "releases"            at "http://oss.sonatype.org/content/repositories/releases",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Orient Technologies Maven2 Repository" at "http://www.orientechnologies.com/listing/m2",
  "Ansvia Releases Repo" at "http://scala.repo.ansvia.com/releases/"
)

libraryDependencies +=     "org.scalatest"              %%  "scalatest"                % "2.2.0" % "test"

libraryDependencies +=     "com.typesafe.scala-logging" %%  "scala-logging-slf4j"      % "2.1.2"

libraryDependencies +=     "ch.qos.logback"             %   "logback-core"             % "1.1.2"

libraryDependencies += "com.thinkaurelius.titan" % "titan" % "0.5.0"

libraryDependencies += "com.thinkaurelius.titan" % "titan-es" % "0.5.0"

libraryDependencies += "com.thinkaurelius.titan" % "titan-hbase" % "0.5.0"

libraryDependencies += "com.thinkaurelius.titan" % "titan-cassandra" % "0.5.0"

libraryDependencies += "com.thinkaurelius.titan" % "titan-all" % "0.5.0"

libraryDependencies += "commons-net" % "commons-net" % "3.2"

