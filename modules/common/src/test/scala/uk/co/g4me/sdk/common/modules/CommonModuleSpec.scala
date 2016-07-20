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

import uk.co.g4me.sdk.common.test.AbstractSpec
import play.api.Configuration
import com.google.inject.Guice
import com.google.inject.ProvisionException
import com.google.inject.ConfigurationException

/**
 * @author nshaw
 * 29 Jun 2016
 */
class CommonModuleSpec extends AbstractSpec with CommonConfig {

  "The CommonConfig trait " should {

    "provide a Map of default settings and " should {

      "be enabled by default " in {

        assert(enabled == "play.sdk.enabled")
        assert(settings.contains(enabled))
        assert(settings.get(enabled) == Some(true))
      }
    }
  }

  "The CommonModule " should {
    "Automatically install modules on the class path " in {
      pending
    }

    "be enabled by default " in {
      val c = Configuration.empty
      val injector = Guice.createInjector(new CommonModule(c))

      val ping = injector.getInstance(classOf[Enabled])
    }

    "be disabled when set " in {
      val disabled = Map(enabled -> false)
      val c = Configuration.from(disabled)
      val injector = Guice.createInjector(new CommonModule(c))

      an[ConfigurationException] should be thrownBy injector.getInstance(classOf[Enabled])
    }

  }

}