package de.charton.reactive.supplier;

import java.util.Optional;
import java.util.function.Supplier;

public class SpaetiShop {

  public Supplier buyBeer(Supplier flavor) {
    return (() -> {
      System.out.println("Produce beer.");
      return createPack(flavor);
    });
  }

  private String createPack(Supplier flavor) {
    var toppic = Optional.ofNullable(new ThreadLocal<String>().get());
    toppic.ifPresent(it -> System.out.println(it));

    System.out.println("Create a pack.");
    return "6 bottles of " + flavor.get();
  }

}
