package org.example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.Scanner;




public class Main {

    public static void main(String[] args)  {
        //Configuring remote access and port
        Config config = ConfigFactory.load("application.conf");

        // Create the actor system
        ActorSystem system = ActorSystem.create("SenderSystem", config);

        // Create the sender actor
        ActorRef sender = system.actorOf(Props.create(SenderActor.class), "sender");

        // Send a message from the sender actor to the receiver actor in akkaDemo2
        sender.tell("Hi from Actor1", ActorRef.noSender());

        System.out.println("You can write 0 to quit anytime");
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        while (!input.equals("0")){
            input = scan.next();
        }
        system.terminate();

    }

}









