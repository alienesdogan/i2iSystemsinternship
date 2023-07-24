package org.example;

import akka.actor.AbstractActor;

public class ReceiverActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, message -> {
                    System.out.println("Received message: "+ message);

                    // Send a message back to the sender actor in akkaDemo1
                    getSender().tell("Hi from Actor2", getSelf());
                })
                .build();
    }
}