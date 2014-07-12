play2-aws
=====================================

Play Framework2 with AWS support.

## Supported AWS services

- ElasticCache (Memcached)
- RDS (Slick)
- CloudSearch
- DynamoDB

## TODO

- S3
- SES
- SQS

## Usage

Clone this repository and modify project name in `build.sbt`.

```scala
name := "play2-aws" // Modify this line

version := "1.0-SNAPSHOT"
...
```

### ElasticCache

play2-aws makes possible to use ElasticCache (Memcached) as back-end of Play's Cache API by [play2-memcached](https://github.com/mumoshu/play2-memcached).

In the local development environment, you can use Ehcache in your JavaVM by configuration below:

```
# Disable Memcached in development mode
memcachedplugin=disabled

# Disable Ehcache if you want to use Memcached
#ehcacheplugin=disabled

# Memcached host and port
#memcached.host="xxx.xxx.xxx.xxx:11211"

# Memcached timeout (seconds)
#memcached.timeout=1800
```

In the production environment, you can replace Ehcache with Memcached by modifying `conf/application.conf` as below:

```
# Disable Memcached in development mode
#memcachedplugin=disabled

# Disable Ehcache if you want to use Memcached
ehcacheplugin=disabled

# Memcached host and port
memcached.host="xxx.xxx.xxx.xxx:11211"

# Memcached timeout (seconds)
memcached.timeout=1800
```


### RDS

[Slick](http://slick.typesafe.com/) and it's integrated by [play-hikaricp](https://github.com/edulify/play-hikaricp.edulify.com).

Configure connection pool settings in `conf/application.conf`.

```
dbplugin=disabled
db.default.driver=com.mysql.jdbc.Driver
db.default.url="jdbc:mysql://localhost:3306/test"
db.default.user=root
db.default.password=""
db.default.logStatements=true

db.default.partitionCount=1
db.default.maxConnectionsPerPartition=4
db.default.minConnectionsPerPartition=1

db.default.acquireRetryDelay=4000
db.default.maxConnectionAge=2000000
db.default.initSQL="SELECT 1"
```

### CloudSearch

CloudSearch API is provided by [cloudsearch4s](https://github.com/bizreach/cloudsearch4s).

```scala
import jp.co.bizreach.play2aws.AWS

val cloudsearch = AWS.CloudSearch()

// Register single document by case class
cloudsearch.registerIndex(Job("Title", "Content")) match {
  case Left(error) => println(error.messages) // CloudSearchError
  case Right(id)   => println(id)             // String
}

// Search and retrieve document as case class
cloudsearch.search(classOf[Job],
  // Query which is assembled using Lucene's query builder API
  new TermQuery(new Term("index_type", "crawl"))
  // Highlight settings (Optional)
  highlights = Seq(HighlightParam("job_title")),
  // Facet search settings (Optional)
  facets = Seq(FacetParam("employment_status"))
) match {
  case Left(error) => println(error.messages) // CloudSearchError
  case Right(result) => {                     // CloudSearchResult[Job]
    ...
  }
}
```

Configure CloudSearch URL settings in `conf/application.conf`.

```
cloudsearch.search.url="http://xxxx.cloudsearch.amazonaws.com/xxxx/search"
cloudsearch.register.url="http://xxxx.cloudsearch.amazonaws.com/xxxx/documents/batch"
```

### DynamoDB

DynamoDB API is provided by [dynamodb4s](https://github.com/bizreach/dynamodb4s).

```scala
import jp.co.bizreach.play2aws.AWS
import jp.co.bizreach.dynamodb4s._

implicit val db = AWS.DynamoDB()

// Put with case class
Members.put(Member(1, "Japan", "Naoki Takezoe", 30, "BizReach"))

// Query with case class mapping
val list: Seq[Member] = Members.query.keyConditions { t =>
  t.id -> Condition.eq(1) :: t.country -> Condition.eq("Japan") :: Nil
}.as[Member]
```

Configure DynamoDB access settings in `conf/application.conf`. If these configuration entries don't exist,
DynamoDB client accesses to the local DynamoDB (http://localhost:8000).

```
dynamodb.accessKeyId=xxx
dynamodb.secretAccessKey=xxx
```
