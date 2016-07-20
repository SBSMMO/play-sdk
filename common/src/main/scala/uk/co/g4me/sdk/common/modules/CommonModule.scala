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

import play.api.Configuration
import com.google.inject.Inject

/**
 * @author nshaw
 * 28 Jun 2016
 */
class CommonModule @Inject() (configuration: Configuration) extends BaseModule with CommonConfig {

  implicit lazy val c = Configuration.from(global) ++ configuration

  def configure() {
    if (!isEnabled) return

  }

  override def isEnabled(implicit c: Configuration): Boolean = {
    super.isEnabled match {
      case false => false
      case true =>
        bind[Enabled].to[EnabledImpl]
        true
    }
  }
}

trait CommonConfig extends CommonConfigConstants {

  val enabled = Add(enabledPath)

  def settings: Map[String, Any] = {
    Map(
      enabled -> true
    )
  }

  def isEnabled(implicit c: Configuration): Boolean = {
    c.getBoolean(enabled).getOrElse(false)
  }

  def global: Map[String, Any] = {
    settings
  }

  def Add(setting: String): String = {
    root + "." + setting
  }

  def root = {
    rootPath
  }

}

trait CommonConfigConstants {
  val rootPath = "play.sdk"
  val enabledPath = "enabled"
}