package server;

import io.grpc.stub.StreamObserver;
import test.TestRequest;
import test.TestResponse;
import test.TestServiceGrpc;

public class TestServiceImpl extends TestServiceGrpc.TestServiceImplBase {

    @Override
    public void hello(TestRequest request, StreamObserver<TestResponse> responseObserver) {

        final String greeting = String.format("Test, %s %s", request.getFirstName(), request.getLastName());
        System.out.println(greeting);
        TestResponse response = TestResponse.newBuilder()
                                            .setGreeting(greeting)
                                            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void hellos(final TestRequest request, final StreamObserver<TestResponse> responseObserver) {

        for (int i = 0; i < 10; i++) {
            final String greeting = String.format("Hello %s %s %s", request.getFirstName(), request.getLastName(), i);
            System.out.println(greeting);
            responseObserver.onNext(TestResponse.newBuilder()
                                                .setGreeting(greeting)
                                                .build());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        responseObserver.onCompleted();
    }
}