package simulations

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

class AtOnceUserSimulation extends Simulation {

  //config
  private val config = http.baseUrl("https://en.wikipedia.org")
    .header("Accept", value = "application/json")
    .header("Content-type", value = "application/json")


  //scenario
  private val test =
    scenario("login wiki page")
      .exec(http("login wiki page").post("/w/index.php?title=Special:UserLogin&returnto=Main+Page")
        .queryParam("wpName", "JMeter.Test.Mentoring")
        .queryParam("wpPassword", "JMeter.Test.Mentoring123")
        .queryParam("wploginattempt", "Log in")
        .queryParam("wpEditToken", "+\\")
        .queryParam("title", "Special:UserLogin")
        .queryParam("authAction", "login").check(status is 200))

  //set up
  setUp(test.inject(atOnceUsers(Integer.parseInt(System.getProperty("userCount"))))).protocols(config)
}