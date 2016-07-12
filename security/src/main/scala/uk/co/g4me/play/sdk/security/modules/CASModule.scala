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

import com.google.inject.PrivateModule
import net.codingwell.scalaguice.ScalaPrivateModule
import play.api.Configuration
import javax.inject.Inject
import uk.co.g4me.sdk.common.modules.CommonModule
import net.codingwell.scalaguice.ScalaModule

/**
 * @author nshaw
 * 2 Jul 2016
 */
class CASModule @Inject() (implicit configuration: Configuration) extends CommonModule with ScalaModule with CASConfig {

  override def configure() {

  }

  val defaultPasswordConfig: Configuration = {
    Configuration.from(local)
  }

}

private[security] trait CASConfig extends SecurityConfig {

  private val casRoot = "cas"

  override val local: Map[String, Any] = {
    Map(
      Add(enabled) -> true
    )
  }

  override def global: Map[String, Any] = {
    super.global ++ local
  }

  override def root: String = {
    super.root.concat(casRoot)
  }

}