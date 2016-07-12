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

/**
 * @author nshaw
 * 29 Jun 2016
 */
class CommonModuleSpec extends AbstractSpec with CommonConfig {

  "The CommonConfig trait " should {

    "provide a Map of default settings and " should {

      "be enabled" in {
        val e = Add(enabled)

        assert(e == "play.sdk.enabled")
        assert(local.contains(e))
        assert(local.get(e) == Some(true))
      }
    }
  }

}