package com.artemisa.sandbox.sandbox.services;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.util.UUID;

@Service
public class AwsService {

    private final SqsClient sqsClient;
    private final String queueUrl = "https://sqs.us-east-1.amazonaws.com/asdasdasd/MiColaDePrueba.fifo";

    public AwsService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void enviarMensaje(String mensaje) {
        sqsClient.sendMessage(
                SendMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .messageBody(mensaje)
                        .messageGroupId("grupo-1")
                        .messageDeduplicationId(UUID.randomUUID().toString())
                        .build()
        );

        System.out.println("Mensaje enviado a SQS: " + mensaje);
    }
}
