package jp.co.bizreach.play2aws

/**
 * Provides entry point to API for AWS services.
 */
object AWS {

  /**
   * Returns the CloudSearch client.
   */
  def CloudSearch() = jp.co.bizreach.cloudsearch4s.CloudSearch(
    jp.co.bizreach.cloudsearch4s.CloudSearch.CloudSearchSettings(
      registerUrl = Config.get("cloudsearch.register.url").get,
      searchUrl   = Config.get("cloudsearch.search.url").get
    )
  )

  /**
   * Returns the implicit DynamoDB instance.
   */
  implicit def DynamoDB() = {
    (for {
      accessKeyId     <- Config.get("dynamodb.accessKeyId")
      secretAccessKey <- Config.get("dynamodb.secretAccessKey")
    } yield {
      awscala.dynamodbv2.DynamoDB(accessKeyId, secretAccessKey)
    }).getOrElse(awscala.dynamodbv2.DynamoDB.local())
  }

}
