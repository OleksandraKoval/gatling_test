package simulations

import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._


class RampUsersPerSecSimulation extends Simulation {

  private val config = http.baseUrl("https://wikipedia.org")
    .header("Connection", value = "keep-alive")

  val csvFeeder = csv("data/data.csv").circular

  def getResultOfWikiSearch() = {
    feed(csvFeeder)
      .exec(http("get search page").get("/search-redirect.php")
        .queryParam("family", "wikipedia")
        .queryParam("language", "en")
        .queryParam("search", "${contentToSearch}")
        .queryParam("language", "en")
        .queryParam("go", "GO").check(status is 200)
      )
  }

  val test = scenario("get wiki search result page").exec(getResultOfWikiSearch())

  setUp(test.inject(rampUsersPerSec(100).to(200).during(5.minutes))).protocols(config)
}
