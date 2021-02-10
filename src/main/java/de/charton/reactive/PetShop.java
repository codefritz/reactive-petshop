package de.charton.reactive;

import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

public class PetShop {

  static final String TOPPING = "topping";

  private final SurpriseService surpriseService = new SurpriseService();

  public Mono<?> buyCatFood(Mono<String> cat) {
    return cat
        .map(it -> {
          System.out.println("it -> M says: addVitamin, now!");
          return addVitamin(it.length());
          }
        )
        // TODO add surprise from surprise service
        .flatMap(it -> {
          return surpriseService.getSurprise().map(sIt -> sIt + it);
        })
        .map(it -> {
          System.out.println("use map, because I can.");
          return it;
        });
  }

  @NonNull
  private Mono<String> addVitamin(int size) {
    var toppic = Mono.subscriberContext().map(ctx -> ctx.getOrEmpty(TOPPING))
        .map(it -> {
          System.out.println("add toppic: " + it);
          return it.map(Mono::just).orElse(Mono.empty());
        });
    // TODO get some fruits (apples, brokkoli, tomato, fenel form external provider!)
    return toppic.map(it -> size + " apples");
    // return size + " apples";
  }

}
