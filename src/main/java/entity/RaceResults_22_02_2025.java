package entity;

import javax.persistence.*;

@Entity
@Table(name = "race_results_22_02_2025", schema = "bmx")
public class RaceResults_22_02_2025 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "category")
    private String category;
    @Column(name = "place")
    private int place;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "racer_id")
    private Racer racer;

    public RaceResults_22_02_2025() {
    }

    public RaceResults_22_02_2025(AgeCategory category, int place, Racer racer) {
        this.category = category.toString();
        this.place = place;
        this.racer = racer;
    }
}
