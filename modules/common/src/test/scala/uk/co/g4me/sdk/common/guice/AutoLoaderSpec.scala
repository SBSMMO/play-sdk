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

package uk.co.g4me.sdk.common.guice

import uk.co.g4me.sdk.common.test.AbstractSpec
import play.api.Configuration
import com.google.inject.Guice
import uk.co.g4me.sdk.common.modules.CommonEnabled

/**
 * @author nshaw
 * 22 Jul 2016
 */
class AutoLoaderSpec extends AbstractSpec {

  "The AutoLoader " should {

    def config(data: (String, Any)*) = Configuration.from(data.toMap)

    "Automatically install modules on the class path " in {
      val injector = Guice.createInjector(new AutoLoader(config()))

      val ping = injector.getInstance(classOf[CommonEnabled])
    }

  }

}