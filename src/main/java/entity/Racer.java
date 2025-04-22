package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "racer", schema = "bmx")
public class Racer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "racer_number")
    private int racerNumber;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "sports_category")
    private String category;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "club_id")
    private Club club;
    @Column(name = "sex")
    private String sex;

    public Racer() {
    }

    public Racer(String lastName, String firstName, int racerNumber, LocalDate birthday
            , SportsCategory category, Sex sex, Club club) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.racerNumber = racerNumber;
        this.birthday = birthday;
        this.category = category.toString();
        this.sex = sex.toString();
        this.club = club;
    }
}



