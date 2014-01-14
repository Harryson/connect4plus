// group ID
organization := "de.stejack"

// artifact ID                                                                                                                          
name := "connect4plus"

version := "1.0-SNAPSHOT"

resolvers += "db4omaverepositories" at "http://source.db4o.com/maven"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Scala sbt" at "http://repo.scala-sbt.org/scalasbt/plugins-releases"


// general project dependencies
libraryDependencies ++= Seq(
  "com.novocode" % "junit-interface" % "0.9" % "test",
  "com.google.inject" % "guice" % "4.0-beta",
  "com.db4o" % "db4o-full-java5" % "8.1-SNAPSHOT",
  "org.hibernate" % "hibernate-core" % "4.2.+",
  "mysql" % "mysql-connector-java" % "5.1.+",
  "org.ektorp" % "org.ektorp" % "1.4.1"
)


javacOptions ++= Seq("-source", "1.6", "-target", "1.6")

// disable using the Scala version in output paths and artifacts
crossPaths := false




sonarProperties := sonarProperties.value ++Map(
  "sonar.projectName" -> "connect4plus",
  "sonar.host.url" -> "http://lenny2.in.htwg-konstanz.de:9000",
  "sonar.jdbc.url" -> "jdbc:h2:tcp://lenny2.in.htwg-konstanz.de/sonar"
)


// setup entry points for sonar code analyzer
pomExtra := 
  <build>
    <sourceDirectory>src/main/java/connectfour</sourceDirectory>
    <testSourceDirectory>src/test/java/connectfour</testSourceDirectory>
  </build>
  

// publishing target
publishTo := Some("HtwgPublishTo" at "http://lenny2.in.htwg-konstanz.de:8081/artifactory/libs-snapshot-local;build.timestamp=" + new java.util.Date().getTime())

