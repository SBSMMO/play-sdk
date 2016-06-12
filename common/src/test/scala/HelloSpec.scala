import org.scalatest._
import com.example.Hello

class HelloSpec extends FlatSpec with Matchers {

  "Object Hello" should "return correct greeting" in {
    Hello.sayHello == "Hello, world!"
  }
}
