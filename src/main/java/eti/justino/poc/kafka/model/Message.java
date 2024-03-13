package eti.justino.poc.kafka.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

import static eti.justino.poc.kafka.util.StringUtils.encapulaCampoString;

@Builder
@Data
public class Message {

    private String id;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;

    public final String toJson() {
        return "{" +
                encapulaCampoString("id", getId()) + "," +
                encapulaCampoString("timestamp", getTimestamp().toString()) + "," +
                encapulaCampoString("message", message) +
                "}";

    }
}
