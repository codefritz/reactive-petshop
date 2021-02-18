package de.charton.reactive;

import lombok.extern.java.Log;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

@Log
public class PetShop {

  static final String TOPPING = "topping";

  private final SurpriseService surpriseService = new SurpriseService();

  public Mono<?> buyCatFood(Mono<String> cat) {
    return cat
        .flatMap(it -> {
          System.out.println("it -> M says: addVitamin, now!");
          return addVitamin(it.length());
          }
        )
        // TODO add surprise from surprise service
        .flatMap(it -> {
          System.out.println("check for surprise");
          return surpriseService.getSurprise().map(sIt -> sIt + " and " + it);
        })
        .map(it -> {
          System.out.println("use map, because I can.");
          return it;
        });
  }

  @NonNull
  private Mono<String> addVitamin(int size) {
    var toppic = Mono.subscriberContext()
        .map(ctx -> ctx.getOrEmpty(TOPPING))
        .flatMap(it -> {
          System.out.println("add toppic: " + it);
          return it.map(Mono::just).orElse(Mono.empty());
        });
    // TODO get some fruits (apples, brokkoli, tomato, fenel form external provider!)
    return toppic.map(it -> size + " apples with " + it);
    // return size + " apples";
  }

}
