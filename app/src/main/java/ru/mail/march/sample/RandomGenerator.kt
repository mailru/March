package ru.mail.march.sample

object RandomGenerator {
    private val errors = listOf(
        "Bad Request",
        "Unauthorized",
        "Payment Required",
        "Forbidden",
        "Not Found",
        "Request Timeout"
    )

    private val words = listOf(
        "time",
        "person",
        "year",
        "way",
        "day",
        "thing",
        "man",
        "world",
        "life",
        "hand",
        "part",
        "child",
        "eye",
        "woman",
        "place",
        "work",
        "week",
        "case",
        "point",
        "company",
        "number"
    )

    fun generateError(): String {
        return errors.random()
    }

    fun generateWord(): String {
        return words.random()
    }
}