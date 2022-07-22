package de.jakkoble.plugins

import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*

// This extension Funciton to the Main File is used to install need Plugins
fun Application.configureRouting() {

    // Adds Functionality for Get-, Put-, and Delete-HTTP Request
    install(CORS) {
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        anyHost()
    }

    // Adds Functionality to Display the Fruits in JSON Format
    install(ContentNegotiation) {
        jackson()
    }
}