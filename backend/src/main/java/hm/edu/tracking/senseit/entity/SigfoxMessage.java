package hm.edu.tracking.senseit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * This entity represents a book.
 * For demo purposes -> persistence.
 * <p>
 * Will be persisted in the table "book".
 * Will be handled by JPA.
 */
@Data
@XmlRootElement
@NoArgsConstructor // Necessary for JPA
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SigfoxMessage {

    private Long id;
    private String name;
    private String date;
    private String time;
    private String temperature;
    private String humidity;
    private int vibrationCount;
    private double latitude;
    private double longitude;

    public SenseitMessage toSenseitMessage() {
        SenseitMessage message = new SenseitMessage();

        message.setName(name);
        int year = Integer.valueOf(date.substring(6, 10));
        int month = Integer.valueOf(date.substring(3, 5));
        int day = Integer.valueOf(date.substring(0, 2));
        int hours = Integer.valueOf(time.substring(0, 2));
        int minutes = Integer.valueOf(time.substring(3, 5));
        Date date = new Date(year, month, day, hours, minutes);
        message.setEventTimestamp(date);

        Double temp = Double.valueOf(temperature.replaceAll(",", "."));
        message.setTemperature(temp);
        Double humid = Double.valueOf(humidity.replaceAll(",", "."));
        message.setHumidity(humid);

        message.setVibrationCount(vibrationCount);

        message.setLatitude(String.valueOf(latitude));
        message.setLongitude(String.valueOf(longitude));

        return message;
    }

}
