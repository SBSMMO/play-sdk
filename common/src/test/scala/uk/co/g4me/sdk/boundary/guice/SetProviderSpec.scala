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

package uk.co.g4me.sdk.boundary.guice

import org.scalatest.{ MustMatchers, WordSpec }
import com.google.inject._
import java.util.{ Set => JSet, HashSet => JHashSet }
import scala.collection.{ immutable => im }
import net.codingwell.scalaguice.ScalaModule
import net.codingwell.scalaguice.SetProvider

class SetProviderSpec extends WordSpec with MustMatchers {

  private val testSet = newSet(1, 3)

  "A Set Provider" should {

    "allow binding a Java Set" in {
      val module = new AbstractModule with ScalaModule {
        def configure() = {
          bind[JSet[B]].toInstance(new JHashSet[B]())
          bind[im.Set[B]].toProvider(new SetProvider(Key.get(typeLiteral[JSet[B]])))
        }
      }
      Guice.createInjector(module).getInstance(Key.get(typeLiteral[im.Set[B]])) must be('empty)
    }

    "allow binding a Java Set with a Java annotation" in {
      import name.Named
      val module = new AbstractModule with ScalaModule {
        def configure() = {
          bind[JSet[B]].annotatedWith[Named].toInstance(new JHashSet[B]())
          bind[im.Set[B]].annotatedWith[Named].toProvider(new SetProvider(Key.get(typeLiteral[JSet[B]], classOf[Named])))
        }
      }
      Guice.createInjector(module).getInstance(Key.get(typeLiteral[im.Set[B]], classOf[Named])) must be('empty)
    }

    "allow binding a Java Set with data" in {
      val module = new AbstractModule with ScalaModule {
        def configure() = {
          bind[JSet[Int]].toInstance(testSet)
          bind[im.Set[Int]].toProvider(new SetProvider(Key.get(typeLiteral[JSet[Int]])))
        }
      }
      val set = Guice.createInjector(module).getInstance(Key.get(typeLiteral[im.Set[Int]]))
      set must have size 2
      set must contain(1)
      set must contain(3)
    }
  }

  private def newSet[T](elems: T*): JSet[T] = {
    val result = new java.util.HashSet[T]()
    for (t <- elems) {
      result.add(t)
    }
    result
  }
}
