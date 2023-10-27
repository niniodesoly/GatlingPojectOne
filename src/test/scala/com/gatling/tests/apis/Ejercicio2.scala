package com.gatling.tests.apis

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Ejercicio2 extends Simulation {
  // Protocol
  val httpProtocol = http.baseUrl("https://pokeapi.co/api/v2")

  // Escenario para obtener la lista de Pokémon
  val scn1 = scenario("List Pokemon")
    .exec(
      http("List Pokemon Request")
        .get("/pokemon")
        .check(
          status is 200
        )
    )

  // Escenario para obtener detalles de un Pokémon específico (por ejemplo, Pikachu)
  val scn2 = scenario("Get Pikachu Details")
    .exec(
      http("Get Pikachu Details Request")
        .get("/pokemon/pikachu")
        .check(
          status is 200,
          jsonPath("$.name").is("pikachu")
        )
    )

  // Escenario para buscar Pokémon por tipo (por ejemplo, tipo "electric")
  val scn3 = scenario("Search Electric Pokemon")
    .exec(
      http("Search Electric Pokemon Request")
        .get("/type/electric")
        .check(
          status is 200
        )
    )

  // Escenario para obtener detalles de una habilidad de Pokémon (por ejemplo, "overgrow")
  val scn4 = scenario("Get Overgrow Ability")
    .exec(
      http("Get Overgrow Ability Request")
        .get("/ability/overgrow")
        .check(
          status is 200,
          jsonPath("$.name").is("overgrow")
        )
    )

  // Escenario para obtener detalles de una ubicación en el mundo Pokémon (por ejemplo, "pallet-town")
  val scn5 = scenario("Get Pallet Town Details")
    .exec(
      http("Get Pallet Town Details Request")
        .get("/location-area/pallet-town")
        .check(
          status is 200,
          jsonPath("$.name").is("pallet-town")
        )
    )

  // Escenario para obtener detalles de un movimiento de Pokémon (por ejemplo, "tackle")
  val scn6 = scenario("Get Tackle Move Details")
    .exec(
      http("Get Tackle Move Details Request")
        .get("/move/tackle")
        .check(
          status is 200,
          jsonPath("$.name").is("tackle")
        )
    )

  // Configuración para ejecutar los escenarios
  setUp(
    scn1.inject(rampUsers(5).during(5)),
    scn2.inject(rampUsers(5).during(5)),
    scn3.inject(rampUsers(5).during(5)),
    scn4.inject(rampUsers(5).during(5)),
    scn5.inject(rampUsers(5).during(5)),
    scn6.inject(rampUsers(5).during(5))
  ).protocols(httpProtocol)
}
