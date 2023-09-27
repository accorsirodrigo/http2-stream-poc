package com.stream.http2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Http2Application

fun main(args: Array<String>) {
	runApplication<Http2Application>(*args)
}
