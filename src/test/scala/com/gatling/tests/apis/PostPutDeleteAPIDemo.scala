package com.gatling.tests.apis

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class PostPutDeleteAPIDemo extends Simulation {
  // Protocol
  val httpProtocol = http.baseUrl("https://reqres.in/api")

  // Escenario para crear un usuario
  val createUserScn = scenario("Create User")
    .exec(
      http("create user req")
        .post("/users")
        .header("Content-Type", "application/json")
        .asJson
        .body(RawFileBody("data/user.json")).asJson
//        .body(StringBody(
//          """{
//            |    "name": "Jakson",
//            |    "job": "leader"
//            |}
//          """.stripMargin)).asJson

        .check(
          status is 201,
          jsonPath("$.name").is("Cesarh")
        )
    )
    .pause("1")



    val updateUserScn = scenario("Update User")
    .exec(
      http("update user")
        .put("/users/2")
        .body(RawFileBody("data/user.json")).asJson
      .check(
        status is 200,
        jsonPath("$.name").is("Cesarh")
      )
    )
    .pause("1")

  //Setup
setUp(
  createUserScn.inject(rampUsers(10).during(5)),
  updateUserScn.inject(rampUsers(5).during(3))
).protocols(httpProtocol)

}