package client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import test.TestRequest;
import test.TestResponse;
import test.TestServiceGrpc;

import java.util.Iterator;

import static java.lang.System.out;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                                                      .usePlaintext()
                                                      .build();

        TestServiceGrpc.TestServiceBlockingStub stub
                = TestServiceGrpc.newBlockingStub(channel);

        TestResponse helloResponse = stub.hello(TestRequest.newBuilder()
                                                           .setFirstName("Baeldung")
                                                           .setLastName("gRPC")
                                                           .build());
        out.println(helloResponse.getGreeting());

        final Iterator<TestResponse> hellos = stub.hellos(TestRequest.newBuilder()
                                                                     .setFirstName("Baeldung")
                                                                     .setLastName("gRPC")
                                                                     .build());
        hellos.forEachRemaining(hellosResponse -> out.println(hellosResponse.getGreeting()));

        channel.shutdown();
    }
}

