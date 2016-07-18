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
import uk.co.g4me.sdk.common.modules.{ CommonModule, CommonConfig }
import uk.co.g4me.sdk.common.modules.BaseModule

class SecurityModule @Inject() (configuration: Configuration) extends CommonModule(configuration) with SecurityConfig {

  override lazy implicit val c = Configuration.from(global) ++ configuration

  override def configure() {
    super.configure()

    if (!isEnabled) return

    install(new CASModule(c))
    install(new OAuth1Module(c))
    install(new OAuth2Module(c))
    install(new OpenIDModule(c))
    install(new PasswordModule(c))
  }

  override def isEnabled(implicit c: Configuration): Boolean = {
    c.getBoolean(enabled).getOrElse(false) && super.isEnabled
  }
}

private[security] trait SecurityConfig extends SecurityConfigConstants with CommonConfig {

  override val enabled = Add(enabledPath)

  override def root: String = {
    super.root + "." + securityRoot
  }

  override def local: Map[String, Any] = {
    Map(
      enabled -> true
    )
  }

  override def global: Map[String, Any] = {
    super.global ++ local
  }

}

trait SecurityConfigConstants {
  val securityRoot = "security"
}