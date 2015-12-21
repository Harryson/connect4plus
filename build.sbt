// group ID
organization := "com.github.connect4plus"

// artifact ID                                                                                                                          
name := "connect4plus"

version := "1.0-SNAPSHOT"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "Scala sbt" at "http://repo.scala-sbt.org/scalasbt/plugins-releases"

scalaVersion := "2.11.7"

// general project dependencies
libraryDependencies ++= Seq(
  "com.novocode" % "junit-interface" % "0.9" % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.3",
  "org.scala-lang" % "scala-swing" % "2.11+",
  "com.typesafe.scala-logging" % "scala-logging_2.11" % "3.1.0",
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
)

javacOptions in (Compile, compile) ++= Seq("-source", "1.8", "-target", "1.8")

// disable using the Scala version in output paths and artifacts
crossPaths := false

publishArtifact in Test := true




// setup entry points for sonar code analyzer
pomExtra := 
  <build>
    <sourceDirectory>src/main/java</sourceDirectory>
    <testSourceDirectory>src/test/java</testSourceDirectory>
    <plugins>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.1</version>
        <configuration>
          <fork>true</fork>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
    </plugins>
    <testResources>
      <testResource>
        <directory>src/test/</directory>
      </testResource>
    </testResources>
  </build>
  

// publishing target
publishTo := Some("HtwgPublishTo" at "http://lenny2.in.htwg-konstanz.de:8081/artifactory/libs-snapshot-local;build.timestamp=" + new java.util.Date().getTime())

