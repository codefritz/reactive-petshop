package de.charton.reactive;

import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

public class PetShop {

  public static final String TOPPIC = "topping";


  public Mono<?> buyCatFood(Mono<String> cat) {
    return cat
        .map(it -> {
          System.out.println("it -> addV");
          return addVitamin(it.length());
          }
        )
        .map(it -> {
          System.out.println("flatMap");
          return it;
        });
  }

  @NonNull
  private Mono<String> addVitamin(int size) {
    var toppic = Mono.subscriberContext().map(ctx -> ctx.getOrEmpty(TOPPIC))
        .map(it -> {
          System.out.println("add toppic: " + it);
          return it.map(Mono::just).orElse(Mono.empty());
        });
    return toppic.map(it -> size + " apples");
    // return size + " apples";
  }

}
