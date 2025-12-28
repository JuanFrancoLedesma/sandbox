package com.artemisa.sandbox.sandbox.listener;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.concurrent.Executors;

@Service
public class SqsListener {
    private final SqsClient sqsClient;
    private final String queueUrl = "https://sqs.us-east-1.amazonaws.com/asdasdasd/MiColaDePrueba.fifo";

    public SqsListener(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

//    @PostConstruct
    public void init() {
        System.out.println("INICIANDO CONEXION CON AWS SQS");
        Executors.newSingleThreadExecutor().submit(() -> {
            while (true) {
                var messages = sqsClient.receiveMessage(ReceiveMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .waitTimeSeconds(10)
                        .maxNumberOfMessages(5)
                        .build()).messages();

                for (var m : messages) {
                    System.out.println("RECIBI: " + m.body());

                    sqsClient.deleteMessage(DeleteMessageRequest.builder()
                            .queueUrl(queueUrl)
                            .receiptHandle(m.receiptHandle())
                            .build());
                }
            }


        });
    }
}
