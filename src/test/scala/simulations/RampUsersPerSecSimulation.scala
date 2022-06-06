package simulations

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt


class RampUsersPerSecSimulation extends Simulation {

  private val config = http.baseUrl("https://wikipedia.org")
    .header("Connection", value = "keep-alive")

  private val test =
    scenario("get wiki search result page")
      .exec(http("get search page").get("/search-redirect.php")
        .queryParam("family", "wikipedia")
        .queryParam("language", "en")
        .queryParam("search", "teeeeeeeeeeeeeest")
        .queryParam("language", "en")
        .queryParam("go", "GO"))

  setUp(test.inject(rampUsersPerSec(100).to(200).during(5.minutes))).protocols(config)
}
