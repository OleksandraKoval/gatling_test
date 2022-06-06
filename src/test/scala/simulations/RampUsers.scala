package simulations

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

class RampUsers extends Simulation {

  private val config = http.baseUrl("https://computer-database.gatling.io")

  private val test = scenario("get Home page").exec(http("Home").get("/"))

  setUp(test.inject(rampUsers(100) during 1.minute)).protocols(config)
}
