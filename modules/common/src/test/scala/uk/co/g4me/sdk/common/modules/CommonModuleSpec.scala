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
import com.google.inject.{ Guice, ProvisionException, ConfigurationException }
import play.api.{ Configuration, PlayException }

/**
 * @author nshaw
 * 29 Jun 2016
 */
class CommonModuleSpec extends AbstractSpec {

  import CommonConfiguration._

  val enabledSetting = rootPath + "." + enabledPath

  "The CommonConfiguration object " should {

    def config(data: (String, Any)*) = CommonConfiguration.fromConfiguration(Configuration.from(data.toMap))

    "provide a default config and " should {

      "be enabled by default " in {
        config().enabled mustBe true
      }
    }

    "get settings from an injected config and " should {

      "be disabled if set " in {
        config(enabledSetting -> false).enabled mustBe false
      }

      "be enabled if set " in {
        config(enabledSetting -> true).enabled mustBe true
      }

      "throw an exception if null " in {
        an[PlayException] should be thrownBy config(enabledSetting -> null).enabled
      }

    }

  }

  "The CommonModule " should {

    def config(data: (String, Any)*) = Configuration.from(data.toMap)

    "Automatically install modules on the class path " in {
      pending
    }

    "be enabled by default " in {
      val c = config()
      val injector = Guice.createInjector(new CommonModule(c))

      val ping = injector.getInstance(classOf[CommonEnabled])
    }

    "be disabled when set " in {
      val c = config(enabledSetting -> false)
      val injector = Guice.createInjector(new CommonModule(c))

      an[ConfigurationException] should be thrownBy injector.getInstance(classOf[CommonEnabled])
    }

  }
}