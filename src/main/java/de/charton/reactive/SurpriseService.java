package de.charton.reactive;

import reactor.core.publisher.Mono;

public class SurpriseService {

  private static final String FISH = "fish";

  Mono<String> getSurprise() {
    System.out.println("Thx for calling surprise service, here we are: " + FISH);
    return Mono.just(FISH);
  }

}
