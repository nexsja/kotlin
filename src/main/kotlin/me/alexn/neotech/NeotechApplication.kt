package me.alexn.neotech

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NeotechApplication

fun main(args: Array<String>) {
	runApplication<NeotechApplication>(*args)
}
