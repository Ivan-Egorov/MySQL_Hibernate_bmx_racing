package entity;

import javax.persistence.*;

@Entity
@Table(name = "club", schema = "bmx")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "club_name")
    private String clubName;
    @Column(name = "city", length = 30)
    private String city;

    public Club() {
    }

    public Club(String clubName, String city) {
        this.clubName = clubName;
        this.city = city;
    }
}
