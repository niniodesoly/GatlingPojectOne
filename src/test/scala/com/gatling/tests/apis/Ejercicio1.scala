package com.gatling.tests.apis

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Ejercicio1 extends Simulation {
  // Protocol
  val httpProtocol = http.baseUrl("https://reqres.in/api")

  // Escenario para crear un usuario
  val scn1 = scenario("Create User")
    .exec(
      http("Create User Request")
        .post("/users")
        .header("Content-Type", "application/json")
        .body(StringBody(
          """{
            |    "name": "John",
            |    "job": "leader"
            |}
          """.stripMargin))
        .check(
          status is 201,
          jsonPath("$.name").is("John")
        )
    )

  // Escenario para actualizar un usuario
  val scn2 = scenario("Update User")
    .exec(
      http("Update User Request")
        .put("/users/2")
        .header("Content-Type", "application/json")
        .body(StringBody(
          """{
            |    "name": "UpdatedName",
            |    "job": "developer"
            |}
          """.stripMargin))
        .check(
          status is 200,
          jsonPath("$.name").is("UpdatedName")
        )
    )

  // Escenario para eliminar un usuario
  val scn3 = scenario("Delete User")
    .exec(
      http("Delete User Request")
        .delete("/users/3")
        .check(
          status is 204
        )
    )

  // Escenario para listar usuarios
  val scn4 = scenario("List Users")
    .exec(
      http("List Users Request")
        .get("/users")
        .check(
          status is 200
        )
    )

  // Escenario para obtener detalles de un usuario
  val scn5 = scenario("Get User Details")
    .exec(
      http("Get User Details Request")
        .get("/users/1")
        .check(
          status is 200,
          jsonPath("$.data.first_name").is("George")
        )
    )

  // Configuración para ejecutar los escenarios
  setUp(
    scn1.inject(rampUsers(5).during(5)),
    scn2.inject(rampUsers(5).during(5)),
    scn3.inject(rampUsers(5).during(5)),
    scn4.inject(rampUsers(5).during(5)),
    scn5.inject(rampUsers(5).during(5))
  ).protocols(httpProtocol)
}
