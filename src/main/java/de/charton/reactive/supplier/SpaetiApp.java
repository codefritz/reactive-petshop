package de.charton.reactive.supplier;

import java.util.function.Supplier;

public class SpaetiApp {

  public static void main(String[] args) {

    var spaetiShop = new SpaetiShop();

    System.out.println("I go spaeti!");

    var beer = spaetiShop.buyBeer(() -> "Luebzer");
    System.out.println("back from spaeti!");

    new ThreadLocal<String>().set("Extra");
    System.out.println(beer.get());
  }
}
