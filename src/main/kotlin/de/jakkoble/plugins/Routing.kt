package de.jakkoble.plugins

import de.jakkoble.Fruit
import de.jakkoble.Fruits
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// This extension Function is used to confige the routing (API Endpoints)
fun Application.configureHTTP() {
   routing {

      // Get-Request without parameter => Main Page
      get {
         call.respondText(this::class.java.classLoader.getResource("index.html")!!.readText(), ContentType.Text.Html)
      }

      // Get-Request with the companion object parameter of Fruit Class
      get(Fruit.path) {
         if (isInvalidKey(call.parameters["key"].toString())) return@get // Check if the Key is WRONG => Stop Here
         call.respond(Fruits.list) // Respond with the Fruit List (because of installed ContentNegotiation Plugin in JSON)
      }

      // Post-Request with the companion object parameter of Fruit Class
      post(Fruit.path) {
         if (isInvalidKey(call.parameters["key"].toString())) return@post // Check if the Key is WRONG => Stop Here
         val fruit = call.receive<Fruit>() // Receive Content of Request as Fruit
         if (!Fruits.list.none { it.name == fruit.name }) { // Check if incomming Fruit is already in List
            println("[Failed] Adding Fruit '${fruit.name}' - Fruit already exists.")
            return@post
         }
         Fruits.list.add(fruit) // Add Fruit to Fruit List
         call.respond(HttpStatusCode.OK) // Respond Status Code for clean Communication
         println("[Success] Added Fruit '${fruit.name}' to Fruit List.")
      }

      // Delete-Request with the companion object parameter of Fruit Class
      delete(Fruit.path) {
         if (isInvalidKey(call.parameters["key"].toString())) return@delete // Check if the Key is WRONG => Stop Here
         val name = call.receive<Fruit>().name // Receive Content of Request as Fruit
         Fruits.list.removeIf { it.name == name } // Removes the Element with the name of the Request item
         call.respond(HttpStatusCode.OK) // Respond Status Code for clean Communication
         println("[Success] Deleted Fruit '$name' from Fruit List.")
      }
   }
}

// Methode to check if Key is valid
// In reality, you should never write the key in the source code, but better save it in a Text File or a Database and read it out.
fun isInvalidKey(key: String) = key != "Usw98ayVeR"
