package simulations
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps

class WMSimulation extends Simulation {

  val httpConf = http.baseUrl("http://localhost:8080")
    .check(status.is(200))

  val scn = scenario("WireMock Load Test")
    .repeat(5) {
      exec(http("GET Request")
        .get("/?region=44"))
    }

  setUp(
    scn.inject(
      rampUsers(100) during (10 seconds)
    )
  ).protocols(httpConf)
}