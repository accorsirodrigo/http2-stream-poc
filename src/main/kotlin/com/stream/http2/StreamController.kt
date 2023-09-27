package com.stream.http2

import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.http.codec.ServerSentEvent
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/flux")
class StreamController {

    @GetMapping
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    suspend fun get(): Flux<ServerSentEvent<GenericComponent>> {
        return getText(500)
    }


    suspend fun getText(delay: Long): Flux<ServerSentEvent<GenericComponent>> {
        return Flux.range(1, 10).map {
            runBlocking {
                delay(delay)
                return@runBlocking ServerSentEvent.builder(
                    GenericComponent(
                        name = "Componente $it",
                        type = "tipo $it"
                    )
                ).build()
            }
        }
    }
}