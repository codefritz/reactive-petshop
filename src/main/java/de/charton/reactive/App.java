package de.charton.reactive;

import static de.charton.reactive.PetShop.TOPPING;

import reactor.core.publisher.Mono;

/**
 * Hello world!
 *
 */
public class App 
{

    private static final String KASPER = "Kasper";

    public static void main( String[] args )
    {
        System.out.println( "Welcome to reactive pet shop!" );

        var petShop = new PetShop();
        System.out.println("I will buy cat food.");

        var catFood = petShop.buyCatFood(Mono.just(KASPER));
        System.out.println("I bought cat food.");

        Object food = catFood.contextWrite(ctx -> {
            System.out.println("Let me add something from bottom.");
            return ctx.put(TOPPING, "cream");
        }).block();

        System.out.println("Eating: " + food);
        System.out.println("Happy meal.");

    }
}
