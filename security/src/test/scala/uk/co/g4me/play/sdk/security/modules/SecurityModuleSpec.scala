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

import uk.co.g4me.sdk.common.test.AbstractSpec
import com.google.inject.{ ConfigurationException, Guice }
import com.mohiva.play.silhouette.persistence.daos.DelegableAuthInfoDAO
import com.mohiva.play.silhouette.impl.providers.CasInfo
import play.api.{ Configuration, Environment }
import play.api.mvc.Results._
import play.api.mvc.{ Action, EssentialAction }
import org.scalatestplus.play.PlaySpec
import akka.stream.Materializer
import org.scalatestplus.play.OneAppPerSuite
import uk.co.g4me.sdk.common.modules.Enabled

/**
 * @author nshaw
 * 28 Jun 2016
 */
class SecurityModuleSpec extends AbstractSpec with SecurityConfig {

  "The SecurityConfig trait " should {

    "provide a Map of default settings and " should {

      "be enabled by default " in {

        assert(enabled == "play.sdk.security.enabled")
        assert(settings.contains(enabled))
        assert(settings.get(enabled) == Some(true))
      }
    }

    "be enabled with a default configuration " in {
      implicit val config = Configuration.empty

      isEnabled mustBe true
    }

    "be disabled when CommonConfig is disabled " in {
      implicit val config = Configuration.from(Map(
        "play.sdk.enabled" -> false
      ))

      isEnabled mustBe false
    }
  }

  "The SecurityModule " should {
    "Automatically install modules on the class path " in {
      pending
    }

    "be enabled by default " in {
      val c = Configuration.empty
      val injector = Guice.createInjector(new SecurityModule(c))

      val ping = injector.getInstance(classOf[Enabled])

    }

    "be disabled when set " in {
      val disabled = Map(enabled -> false)
      val c = Configuration.from(disabled)
      val injector = Guice.createInjector(new SecurityModule(c))

      an[ConfigurationException] should be thrownBy injector.getInstance(classOf[Enabled])
    }

  }
}