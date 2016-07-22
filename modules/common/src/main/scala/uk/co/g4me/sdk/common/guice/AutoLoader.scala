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

import uk.co.g4me.sdk.common.modules.BaseModule
import javax.inject.Inject
import play.api.Configuration
import scala.collection.JavaConverters._
import com.google.inject.Module
import net.codingwell.scalaguice.ScalaModule
import uk.co.g4me.sdk.common.modules.CommonEnabled
import uk.co.g4me.sdk.common.modules.CommonEnabledImpl
import net.codingwell.scalaguice.InjectorExtensions._
import uk.co.g4me.sdk.common.util.ServiceLoader

/**
 * @author nshaw
 * 22 Jul 2016
 */
class AutoLoader @Inject() (val configuration: Configuration) extends BaseModule {

  def configure() {
    val classLoader = Thread.currentThread().getContextClassLoader()
    val modules = ServiceLoader.load(classOf[ScalaModule], classLoader, configuration).asScala
    modules.foreach { module => install(module) }
  }

}

class AutoLoadTestImpl @Inject() (val configuration: Configuration) extends ScalaModule {

  def configure() {
    bind[CommonEnabled].to[CommonEnabledImpl]
  }
}