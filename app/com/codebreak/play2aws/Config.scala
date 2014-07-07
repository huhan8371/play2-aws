package com.codebreak.play2aws

import _root_.java.io.File
import com.typesafe.config.ConfigFactory

object Config {

  private val config = {
    val resource = System.getProperty("config.resource")
    val file = System.getProperty("config.file")

    if(file != null && file.trim.length > 0){
      // from specified file
      ConfigFactory.parseFile(new File(file))
    } else if(resource != null && resource.trim.length > 0){
      // from classpath
      ConfigFactory.load(resource)
    } else {
      // from default file
      ConfigFactory.load()
    }
  }

  private val local = ConfigFactory.load("application_local.conf")

  /**
   * Returns the setting value of the given key as String.
   *
   * @param name the setting key
   * @return the setting value (or None if the setting entry does not exist)
   */
  def get(name: String): Option[String] = if(local.hasPath(name)) Some(local.getString(name)) else if(config.hasPath(name)) Some(config.getString(name)) else None

  /**
   * Returns the setting value of the given key as list of strings.
   *
   * @param name the setting key
   * @return the setting value (or None if the setting entry does not exist)
   */
  def getStringList(name: String): Option[java.util.List[String]] = if(local.hasPath(name)) Some(local.getStringList(name)) else if(config.hasPath(name)) Some(config.getStringList(name)) else None

  /**
   * Returns the setting value of the given key as Int.
   *
   * @param name the setting key
   * @return the setting value (or None if the setting entry does not exist)
   */
  def getInt(name: String): Option[Int] = if(local.hasPath(name)) Some(local.getInt(name)) else if(config.hasPath(name)) Some(config.getInt(name)) else None

  /**
   * Returns the setting value of the given key as Long.
   *
   * @param name the setting key
   * @return the setting value (or None if the setting entry does not exist)
   */
  def getLong(name: String): Option[Long] = if(local.hasPath(name)) Some(local.getLong(name)) else if(config.hasPath(name)) Some(config.getLong(name)) else None

  /**
   * Returns the setting value of the given key as Boolean.
   *
   * @param name the setting key
   * @return the setting value (or None if the setting entry does not exist)
   */
  def getBoolean(name: String): Option[Boolean] = if(local.hasPath(name)) Some(local.getBoolean(name)) else if(config.hasPath(name)) Some(config.getBoolean(name)) else None

  /**
   * Tests whether the given key exists in the configuration file or not.
   *
   * @param name the setting key
   * @return true if it exists,  otherwise false
   */
  def hasPath(name: String): Boolean = local.hasPath(name) || config.hasPath(name)
}