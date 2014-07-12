name := "play2-aws"

version := "1.0-SNAPSHOT"

resolvers += "amateras-repo" at "http://amateras.sourceforge.jp/mvn/"

resolvers += Resolver.url("Edulify Repository", url("http://edulify.github.io/modules/releases/"))(Resolver.ivyStylePatterns)

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "com.edulify"                     %% "play-hirakicp"    % "1.2.0",
  "com.github.mumoshu"              %% "play2-memcached"  % "0.4.0",
  "jp.sf.amateras.play2.fastassets" %% "play2-fastassets" % "0.0.4",
  "jp.co.bizreach"                  %% "cloudsearch4s"    % "0.0.1",
  "jp.co.bizreach"                  %% "dynamodb4s"       % "0.0.1"
)

scalacOptions := Seq("-deprecation")

play.Project.playScalaSettings
