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

package uk.co.g4me.play.sdk.security.modules

import play.api.{ Environment, Configuration }
import javax.inject.Inject
import play.api.inject.Module
import uk.co.g4me.sdk.common.modules.{ BaseModule, CommonModule, Enabled, EnabledImpl }
import uk.co.g4me.sdk.common.modules.CommonConfiguration

class SecurityModule @Inject() (configuration: Configuration) extends BaseModule {

  private val c = SecurityConfiguration.fromConfiguration(configuration)

  override def configure() {
    if (!isEnabled) return

    install(new CommonModule(configuration))

    //    install(new CASModule(configuration))
    //    install(new OAuth1Module(configuration))
    //    install(new OAuth2Module(configuration))
    //    install(new OpenIDModule(configuration))
    //    install(new PasswordModule(configuration))
  }

  private def isEnabled(): Boolean = {
    c.enabled && CommonConfiguration.fromConfiguration(configuration).enabled

  }
}

case class SecurityConfiguration(enabled: Boolean)

object SecurityConfiguration {

  val rootPath = "play.sdk.security"
  val enabledPath = "enabled"

  def fromConfiguration(conf: Configuration): SecurityConfiguration = {
    val c = conf.getConfig(rootPath).getOrElse(Configuration.empty)

    SecurityConfiguration(
      enabled = c.getBoolean(enabledPath).getOrElse(true)
    )
  }

  def from(data: Map[String, Any]) = {
    val c = Configuration.from(data)

    fromConfiguration(c)
  }
}