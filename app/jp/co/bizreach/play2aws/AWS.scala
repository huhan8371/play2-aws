package jp.co.bizreach.play2aws

/**
 * Provides entry point to API for AWS services.
 */
object AWS {

  /**
   * Returns the CloudSearch client.
   */
  def CloudSearch() = com.codebreak.cloudsearch4s.CloudSearch(
    com.codebreak.cloudsearch4s.CloudSearch.CloudSearchSettings(
      registerUrl = Config.get("cloudsearch.register.url").get,
      searchUrl   = Config.get("cloudsearch.search.url").get
    )
  )

}
