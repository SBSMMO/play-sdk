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

class SecurityModule @Inject() (implicit configuration: Configuration) extends CommonModule with SecurityConfig {

  override def configure() {
    super.configure()

    install(new CASModule)
    install(new OAuth1Module)
    install(new OAuth2Module)
    install(new OpenIDModule)
    install(new PasswordModule)
  }
}

private[security] trait SecurityConfig extends CommonConfig {

  private val securityRoot = "security"

  override def root: String = {
    super.root + "." + securityRoot
  }

  override def local: Map[String, Any] = {
    Map(
      Add(enabled) -> true
    )
  }

  override def global: Map[String, Any] = {
    super.global ++ local
  }

}