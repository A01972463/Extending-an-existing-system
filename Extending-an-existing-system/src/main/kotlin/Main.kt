package org.example

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080) {
        routing {
//            Actual Homework Stuff
            get("/") {
                call.respondText(File("index.html").readText(), ContentType.Text.Html)
            }

            post("/data") {
                val data = call.receiveText()
                println(data)
                call.respondText { "Yay" }
            }

//            ChatGPT stuff
            get("/gpt") {
                // Get the user input from query parameters
                val userInput = call.request.queryParameters["userInput"]

                // Manipulate the input (for example, converting to uppercase)
                val result = userInput?.uppercase() ?: ""

                // Read the index.html file and replace the placeholder with the result
                val htmlContent = File("ChatGPT.html").readText().replace("\${'$'}result", result)

                // Respond with the modified HTML content
                call.respondText(htmlContent, ContentType.Text.Html)
            }

            post("/gptData") {
                val data = call.receiveText()
                println(data)
                call.respondText("Yay")
            }

        }
    }.start(wait = true)
}