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

/**
 * @author nshaw
 * 28 Jun 2016
 */
class SecurityModuleSpec extends AbstractSpec with SecurityConfig {

  "The SecurityConfig trait " should {

    "provide a Map of default settings and " should {

      "be enabled " in {
        assert(local.contains(Add(enabled)))
        assert(local.get(Add(enabled)) == Some(true))
      }
    }
  }

  //"The SecurityModule" should {
  //
  //    "return a default Configuration and" should {
  //
  //      //val injector = Guice.createInjector(new SecurityModule(Configuration.empty))
  //
  //      "be enabled by default " in {
  //        local must have size 6
  //        local must contain(enabled -> true)
  //      }
  //    }

  //    "throw a ConfigurationException if a CasInfoDAO is requested and not configured" in {
  //
  //      val thrown = the[ConfigurationException] thrownBy Guice.createInjector(defaultModule).getInstance(classOf[DelegableAuthInfoDAO[CasInfo]])
  //    }

  //    "bind the CasInfoDAO if configured" in {
  //      pending
  //    }
  //}
}