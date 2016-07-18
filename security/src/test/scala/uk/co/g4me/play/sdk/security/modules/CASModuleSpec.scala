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
import play.api.Configuration
import com.google.inject.{ ConfigurationException, Guice }
import uk.co.g4me.sdk.common.modules.Enabled

/**
 * @author nshaw
 * 15 Jul 2016
 */
class CASModuleSpec extends AbstractSpec with CASConfig {

  "The CASConfig trait " should {

    "provide a Map of default settings and " should {

      "be disabled by default " in {

        assert(enabled == "play.sdk.security.cas.enabled")
        assert(local.contains(enabled))
        assert(local.get(enabled) == Some(false))
      }
    }
  }

  "The CASModule " should {

    "be disabled by default " in {
      val c = Configuration.empty
      val injector = Guice.createInjector(new CASModule(c))

      an[ConfigurationException] should be thrownBy injector.getInstance(classOf[Enabled])
    }

    "be enabled when set " in {
      val settings = Map(enabled -> true)
      val c = Configuration.from(settings)
      val injector = Guice.createInjector(new CASModule(c))

      assert(settings.contains(enabled))
      assert(settings.get(enabled) == Some(true))

      val ping = injector.getInstance(classOf[Enabled])
    }

  }
}