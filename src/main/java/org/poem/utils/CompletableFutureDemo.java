package org.poem.utils;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author poem
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Long start = System.currentTimeMillis();
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Random().nextInt(10);
        });


        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 3;
        });


        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 5;
        });


        System.out.println("start");
        CompletableFuture anyOf = CompletableFuture.anyOf(completableFuture, completableFuture1, completableFuture2);
        System.out.println(anyOf.get());
        System.out.println("execute time:" + (System.currentTimeMillis() - start));
        System.out.println(completableFuture.isDone());
        System.out.println(completableFuture1.isDone());
        System.out.println(completableFuture2.isDone());
        CompletableFuture allOf = CompletableFuture.allOf(completableFuture, completableFuture1, completableFuture2);

        System.out.println(allOf.get());
        System.out.println("execute time:" + (System.currentTimeMillis() - start));
        System.out.println(completableFuture.isDone());
        System.out.println(completableFuture1.isDone());
        System.out.println(completableFuture2.isDone());
        //
        Integer s = completableFuture2.thenCompose(resInt -> CompletableFuture.supplyAsync(() -> resInt + 123)).get();
        Integer s2 = completableFuture1.thenCombine(completableFuture2, (int1, int2) -> int1 + int2).get();
        System.out.println(s2);
        System.out.println(s);
    }
}
