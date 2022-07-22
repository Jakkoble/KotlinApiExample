package de.jakkoble.plugins

import de.jakkoble.Fruit
import de.jakkoble.Fruits
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// This extension Funciton to the Main File is used to confige the routing (API Endpoints)
fun Application.configureHTTP() {
    routing {

        // Get-Request without parameter
        get {
            call.respondText(this::class.java.classLoader.getResource("index.html")!!.readText(), ContentType.Text.Html)
        }

        // Get-Request with the companion object parameter of Fruit Class
        get(Fruit.path) {
            call.respond(Fruits.list) // Respond with the Fruit List (because of ContentNegotiation Plugin in JSON)
        }

        // Post-Request with the companion object parameter of Fruit Class
        post(Fruit.path) {
            val fruit = call.receive<Fruit>() // Receive Content of Request as Fruit
            if (!Fruits.list.none { it.name == fruit.name }) error("Name already in use") // If name is already in the List throw IllegalStateException
            Fruits.list.add(fruit) // Add Fruit to Fruit List
            call.respond(HttpStatusCode.OK) // Respond Status Code for clean Communication
        }

        // Delete-Request with the companion object parameter of Fruit Class
        delete(Fruit.path) {
            val name = call.receive<Fruit>().name // Receive Content of Request as Fruit
            Fruits.list.removeIf { it.name == name } // Removes the Element with the name of the Request item
            call.respond(HttpStatusCode.OK) // Respond Status Code for clean communication
        }
    }
}
