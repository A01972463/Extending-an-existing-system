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
        val inputs = mutableListOf<String>()    // Stores display items

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
                if (userInput != null) {
                    val result = userInput.uppercase()
                    inputs.add(result)
                }

                // Read the index.html file
                var htmlContent = File("ChatGPT.html").readText()

                // Replace the placeholder with the accumulated results
                val results = inputs.joinToString(separator = "<br>") { it }
                htmlContent = htmlContent.replace("test", results)

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