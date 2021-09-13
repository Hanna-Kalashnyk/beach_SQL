package software.sagax.baywatch.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@ToString(of = "name")
public class Beach {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "beach", fetch = FetchType.EAGER)
    private Set<Lifeguard> lifeguards;

}
