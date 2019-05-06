package basic.restapi.kotlin

import io.javalin.Javalin

fun main() {
    val userDao = UserDao()
    val app = Javalin.create().apply {
        exception(Exception::class.java) { e, _ -> e.printStackTrace() }
        error(404) { ctx -> ctx.json("not found") }
    }.start(7000)

    app.get("/users") {
        it.json(userDao.users)
    }

    app.get("/users/:user-id") {
        it.json(userDao.findById(it.pathParam("user-id").toInt())!!)
    }

    app.get("/users/email/:email") {
        it.json(userDao.findByEmail(it.pathParam("email"))!!)
    }

    app.post("/users"){
        val user = it.body<User>()
        userDao.save(name = user.name, email = user.email)
        it.status(201)
    }

    app.patch("/users/:user-id"){
        val user = it.body<User>()
        userDao.update(
            id = it.pathParam("user-id").toInt(),
            user = user
        )
        it.status(204)
    }

    app.delete("/users/:user-id"){
        userDao.delete(it.pathParam("user-id").toInt())
    }
}




