package models.subscriptions;

import lombok.*;
import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    private int id;
    private int userId;

}
