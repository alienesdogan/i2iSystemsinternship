package org.example;

import org.voltdb.client.Client;
import org.voltdb.client.ClientFactory;
import org.voltdb.client.ClientResponse;


public class Main {

    public static void main(String[] args) {

        try{
            Client client = ClientFactory.createClient();
            client.createConnection("localhost", 32769);

            ClientResponse response = client.callProcedure("@AdHoc", "SELECT * FROM subscriber");
            System.out.println(response.getResults()[0]);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}