// group ID
organization := "de.stejack"

// artifact ID                                                                                                                          
name := "connect4plus"

version := "1.0-SNAPSHOT"

// general project dependencies
libraryDependencies ++= Seq(
  "com.novocode" % "junit-interface" % "0.9" % "test",
  "com.google.inject" % "guice" % "4.0-beta"
)


// disable using the Scala version in output paths and artifacts
crossPaths := false



// setup entry points for sonar code analyzer
pomExtra := 
  <build>
    <sourceDirectory>src/main/java/connectfour</sourceDirectory>
    <testSourceDirectory>src/test/java/connect4</testSourceDirectory>
  </build>
  

// publishing target
publishTo := Some("HtwgPublishTo" at "http://lenny2.in.htwg-konstanz.de:8081/artifactory/libs-snapshot-local;build.timestamp=" + new java.util.Date().getTime())
