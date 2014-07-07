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

## Usage

Clone this repository and modify project name in `build.sbt`.

```scala
name := "play2-aws" // Modify this line

version := "1.0-SNAPSHOT"
...
```

### ElasticCache



### RDS

[Slick](http://slick.typesafe.com/) and it's integrated by [play-hikaricp](https://github.com/edulify/play-hikaricp.edulify.com).

### CloudSearch

CloudSearch API is provided by [cloudsearch4s](https://github.com/bizreach/cloudsearch4s).

```scala
import com.codebreak.play2aws.AWS

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

### DynamoDB

DynamoDB API is provided by [dynamodb4s](https://github.com/bizreach/dynamodb4s).

TODO