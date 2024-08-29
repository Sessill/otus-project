package ru.homework.otusproject.utils.rest.webclient;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.homework.otusproject.entity.Transport;

@Component
public class TransportWebClientBuilder {

    private static final String baseUrl = "http://localhost:8765/logistic-transport/transport/";
    private static final String baseUrlData = "http://localhost:8765/logistic-delivery/delivery/";


    public boolean cargoExists(Long transportId) {

        try {

            Transport transport = WebClient.create(baseUrl)
                    .get()
                    .uri(String.join("", "/",""+transportId))
  //                  .uri("id",  cargoId)
  //                  .bodyValue(cargoId)
                    .retrieve()
                    .bodyToFlux(Transport.class)
                    .blockFirst(); // блокирует поток до получения 1й записи

            if (transport != null) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public Flux<Transport> transportExistsAsync(Long transportId) {

        Flux<Transport> fluxTransport = WebClient.create(baseUrl)
                .get()
                .uri(String.join("", "/",""+transportId))
                .retrieve()
                .bodyToFlux(Transport.class);
//                .post()
//                .uri("id")
//                .bodyValue(transportId)
//                .retrieve()
//                .bodyToFlux(Cargo.class);

        return fluxTransport;

    }

    public Mono<Transport> getTrack(Long count, Jwt token) {


        Mono<Transport> idTrack= WebClient.create(baseUrl)
                .get()
                .uri(String.join("", "gettrack/",""+count))
                .headers(h -> h.setBearerAuth(token.getTokenValue()))
//                .post()
//                .uri("free-track")
//                .bodyValue(count)
                .retrieve()
                .bodyToMono(Transport.class);

        return idTrack;

    }
}
