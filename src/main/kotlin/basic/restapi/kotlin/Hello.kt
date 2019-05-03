package basic.restapi.kotlin

import io.javalin.Javalin

fun main(args: Array<String>) {
    val app = Javalin.create().start(7000)
    app.get("/") { ctx -> ctx.result("Hello World") }
}

data class User(val name: String, val email: String, val id: Int)