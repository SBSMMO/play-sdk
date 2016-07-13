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
import com.google.inject.AbstractModule
import com.google.inject.Inject

/**
 * @author nshaw
 * 28 Jun 2016
 */
class CommonModule @Inject() (implicit configuration: Configuration) extends BaseModule with CommonConfig {

  val c = (Configuration.from(local) ++ configuration).getConfig(root).getOrElse(Configuration.empty)

  def configure() {
    if (!isEnabled) return

  }
}

trait CommonConfig {

  private val rootPath = "play.sdk"

  val enabled = "enabled"

  def isEnabled(implicit c: Configuration): Boolean = {
    c.getBoolean(enabled).getOrElse(false)
  }

  def local: Map[String, Any] = {
    Map(
      Add(enabled) -> true
    )
  }

  def root = {
    rootPath
  }

  def global: Map[String, Any] = {
    local
  }

  def Add(setting: String): String = {
    root + "." + setting
  }

}