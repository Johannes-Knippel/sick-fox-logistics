package hm.edu.tracking.example.entity;

import hm.edu.tracking.example.boundary.HelloWorldApi;
import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This entity represents a message.
 * <p>
 * It is being used by {@link HelloWorldApi} to automatically
 * serialize the message to JSON or XML.
 */
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class Message {
    @Getter
    private String message;
}
