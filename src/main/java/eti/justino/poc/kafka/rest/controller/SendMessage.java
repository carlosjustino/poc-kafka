package eti.justino.poc.kafka.rest.controller;

import eti.justino.poc.kafka.mapper.MessageMapper;
import eti.justino.poc.kafka.model.Message;
import eti.justino.poc.kafka.model.MessageDto;
import eti.justino.poc.kafka.rest.api.KafkaApi;
import eti.justino.poc.kafka.services.JustinoSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class SendMessage implements KafkaApi {


    @Autowired
    private JustinoSend service;

    @Autowired
    private MessageMapper mapper;

    @Override
    public ResponseEntity<Object> _sendMessageKafka(String type, MessageDto messageDto) {
        Message m = mapper.messageDtoToMessage(messageDto);
        switch (type){
            case "sync" -> service.sendToKafkaSync(m);
            case "async" -> service.sendToKafkaAsync(m);
            default -> throw new RuntimeException(String.format("Não tratado o tipo: %s", type));

        }
        return ResponseEntity.accepted().body("");
    }

}
