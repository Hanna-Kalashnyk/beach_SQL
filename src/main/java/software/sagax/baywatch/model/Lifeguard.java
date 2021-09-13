package software.sagax.baywatch.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "name")
public class Lifeguard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Beach beach;

    @ManyToOne
    @JoinColumn
    private Lifeguard boss;

    private String name;

    private Integer speed;
}
