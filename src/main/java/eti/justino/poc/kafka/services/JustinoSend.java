package eti.justino.poc.kafka.services;

import eti.justino.poc.kafka.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class JustinoSend {

    @Autowired
    private KafkaTemplate<String, String> template;

    @Value("${justino.kafka.topico}")
    private String topico;

    @Value("${justino.kafka.particao}")
    private Integer particao;

    public void sendToKafkaAsync(final Message data) {


        CompletableFuture<SendResult<String, String>> future = template.send(topico,particao,"async",data.toJson());
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                handleSuccess(data, result);
            }
            else {
                handleFailure(data, ex);
            }
        });
    }

    private void handleFailure(Message data, Throwable ex) {
        System.out.println("faio");
    }

    private void handleSuccess(Message data, SendResult<String, String> record) {
        System.out.println("foi");
    }


    public void sendToKafkaSync(final Message data) {

        try {
            var result = template.send(topico,particao,"sync",data.toJson()).get(10, TimeUnit.SECONDS);;
            handleSuccess(data, result);
        }
        catch (ExecutionException e) {
            handleFailure(data, e.getCause());
        }
        catch (TimeoutException | InterruptedException e) {
            handleFailure(data, e);

        }
    }



}
