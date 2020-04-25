package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8682)
                .addService(new TestServiceImpl()).build();

        server.start();
        server.awaitTermination();
    }
}
