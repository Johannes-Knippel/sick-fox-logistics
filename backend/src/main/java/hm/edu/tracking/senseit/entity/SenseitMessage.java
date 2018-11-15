package hm.edu.tracking.senseit.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * This entity represents a book.
 * For demo purposes -> persistence.
 * <p>
 * Will be persisted in the table "book".
 * Will be handled by JPA.
 */
@Entity
@Table(name = "senseit")
@Data
@XmlRootElement
@NoArgsConstructor // Necessary for JPA
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SenseitMessage {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private Date eventTimestamp;
    private String longitude;
    private String latitude;
    private Double temperature;
    private Double humidity;

}
