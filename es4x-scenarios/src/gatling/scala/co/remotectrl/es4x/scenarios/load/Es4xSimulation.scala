package co.remotectrl.es4x.scenarios.load

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Es4xSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:3000")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .upgradeInsecureRequestsHeader("1")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0")

  val scn = scenario("Es4xSimulation")
    .exec(http("request_0")
      .get("/"))

  setUp(scn.inject(atOnceUsers(1000))).protocols(httpProtocol).assertions(
    global.successfulRequests.percent.gt(99)
  )
}