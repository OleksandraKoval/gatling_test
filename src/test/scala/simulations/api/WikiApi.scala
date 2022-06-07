package api

import io.gatling.core.Predef._
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder

object WikiApi {

  val wikiConfig: HttpProtocolBuilder = http.baseUrl("https://en.wikipedia.org")
    .header("Accept", value = "application/json")
    .header("Content-type", value = "application/json")
}
