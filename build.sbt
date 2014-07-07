name := "play2-aws"

version := "1.0-SNAPSHOT"

resolvers += "amateras-repo" at "http://amateras.sourceforge.jp/mvn/"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "com.edulify"                     %% "play-hirakicp"    % "1.2.0",
  "com.github.mumoshu"              %% "play2-memcached"  % "0.4.0",
  "jp.sf.amateras.play2.fastassets" %% "play2-fastassets" % "0.0.4",
  "com.codebreak"                   %% "cloudsearch4s"    % "0.0.1"
)

play.Project.playScalaSettings
