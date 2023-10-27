package com.gatling.tests

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class ComputerDataBase extends Simulation {

	val httpProtocol = http
		.baseUrl("https://computer-database.gatling.io")
		.inferHtmlResources()
		.acceptHeader("text/css,*/*;q=0.1")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-PE,es;q=0.9,en-US;q=0.8,en;q=0.7,es-419;q=0.6")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36")

	val scn = scenario("ComputerDataBase")
		.exec(http("NewComputersPage")
			.get("/computers/new")
			.resources(http("request_1")
				.get("/assets/css/bootstrap.min.css"),
				http("request_2")
					.get("/assets/css/main.css")))
		.pause(57) // Pausa agregada a nivel de escenario
		.exec(http("ComputersDataBasePage")
			.post("/computers")
			.formParam("name", "MyComputer1")
			.formParam("introduced", "2010-01-01")
			.formParam("discontinued", "2020-01-01")
			.formParam("company", "2")
			.resources(http("request_4")
				.get("/assets/css/bootstrap.min.css"),
				http("request_5")
					.get("/assets/css/main.css")))
		.pause(11) // Pausa agregada a nivel de escenario
		.exec(http("FilterComputer")
			.get("/computers?f=MyComputer1"))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
