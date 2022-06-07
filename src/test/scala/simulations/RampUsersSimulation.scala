package simulations

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

class RampUsersSimulation extends Simulation {

  private val config = http.baseUrl("https://en.wikipedia.org/wiki/Main_Page")

  private val test = scenario("get Home page").exec(http("Home").get("/").check(status is 200))

  setUp(test.inject(rampUsers(Integer.parseInt(System.getProperty("userCount"))) during 1.minute)).protocols(config)
}
