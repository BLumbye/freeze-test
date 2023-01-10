package org.example;

import org.jspace.RemoteSpace;
import org.jspace.SequentialSpace;
import org.jspace.SpaceRepository;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String PORT = "131313";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type \"host\" to host a space and \"join\" to join a space.");
        if (scanner.next().equals("host")) {
            System.out.println("Type in your local IPv4 address");
            String ip = scanner.next();
            SpaceRepository repository = new SpaceRepository();
            repository.addGate("tcp://" + ip + ":" + PORT + "/?keep");
            repository.add("space", new SequentialSpace());
        } else {
            System.out.println("Type the ip to join");
            String ip = scanner.next();
            RemoteSpace space = new RemoteSpace("tcp://" + ip + ":" + PORT + "/space?keep");
            System.out.println("Press enter to leave space");
            scanner.next();
            System.out.println("Leaving...");
            space.close();
            System.out.println("Left");
        }
    }
}