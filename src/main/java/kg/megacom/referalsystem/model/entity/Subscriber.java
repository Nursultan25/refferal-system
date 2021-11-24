package kg.megacom.referalsystem.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_subscriber")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    Long id;

    @Column(name = "phone", nullable = false, unique = true)
    String phone;

    @Column(name = "is_active", nullable = true)
    Boolean isActive;

    @UpdateTimestamp
    @Column(name = "edit_date")
    Date editDate;
}
