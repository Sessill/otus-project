package ru.homework.otusproject.utils.rest.webclient;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import ru.homework.otusproject.entity.Cargo;

@Component
public class CargoWebClientBuilder {

    private static final String baseUrl = "http://localhost:8765/logistic-stock/cargo/";
    private static final String baseUrlData = "http://localhost:8765/logistic-orders/order/";


    public boolean cargoExists(Long cargoId, Jwt token) {

        try {

            Cargo cargo = WebClient.create(baseUrl)
                    .get()
                    .uri(String.join("", "/",""+cargoId))
                    .headers(h -> h.setBearerAuth(token.getTokenValue()))
  //                  .uri("id",  cargoId)
  //                  .bodyValue(cargoId)
                    .retrieve()
                    .bodyToFlux(Cargo.class)
                    .blockFirst(); // блокирует поток до получения 1й записи

            if (cargo != null) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    public Flux<Cargo> cargoExistsAsync(Long cargoId) {

        Flux<Cargo> fluxCargo = WebClient.create(baseUrl)
                .post()
                .uri("id")
                .bodyValue(cargoId)
                .retrieve()
                .bodyToFlux(Cargo.class);

        return fluxCargo;

    }

    public Flux<Boolean> initCargoData(Long cargoId) {

        Flux<Boolean> fluxCargo = WebClient.create(baseUrlData)
                .post()
                .uri("init")
                .bodyValue(cargoId)
                .retrieve()
                .bodyToFlux(Boolean.class);

        return fluxCargo;

    }
}
