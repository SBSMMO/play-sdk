/*
 * Copyright 2014-2016 Game For Me LTD.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.g4me.sdk.common.modules

import com.google.inject.Inject
import play.api.mvc._
import play.api.{ Environment, Configuration }

/**
 * @author nshaw
 * 28 Jun 2016
 */
class CommonModule @Inject() (configuration: Configuration) extends BaseModule {

  private val c = CommonConfiguration.fromConfiguration(configuration)

  def configure() {
    if (!isEnabled) return

  }

  private def isEnabled(): Boolean = {
    if (c.enabled) bind[Enabled].to[EnabledImpl]

    c.enabled
  }
}

case class CommonConfiguration(enabled: Boolean)

object CommonConfiguration {

  val rootPath = "play.sdk"
  val enabledPath = "enabled"

  def fromConfiguration(conf: Configuration): CommonConfiguration = {
    val c = conf.getConfig(rootPath).getOrElse(Configuration.empty)

    CommonConfiguration(
      enabled = c.getBoolean(enabledPath).getOrElse(true)
    )
  }

  def from(data: Map[String, Any]) = {
    val c = Configuration.from(data)

    fromConfiguration(c)
  }
}