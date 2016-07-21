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
class CASModuleSpec extends AbstractSpec {

  import CASConfiguration._

  "The CASConfiguration object " should {

    def config(data: (String, Any)*) = CASConfiguration.fromConfiguration(Configuration.from(data.toMap))

    val enabledSetting = rootPath + "." + enabledPath

    "provide a default config and " should {

      "be disabled by default " in {
        config().enabled mustBe false
      }

      "be disabled if set " in {
        config(enabledSetting -> false).enabled mustBe false
      }

      "be enabled if set " in {
        config(enabledSetting -> true).enabled mustBe true
      }

    }

  }
}