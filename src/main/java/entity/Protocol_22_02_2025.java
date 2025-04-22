package entity;

import javax.persistence.*;

@Entity
@Table(name = "protocol_22_02_2025", schema = "bmx")
public class Protocol_22_02_2025 {
    @Column(name = "run")
    private int run;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "racer_id")
    private Racer racer;
    @Column(name = "lane_1")
    private int lane1;
    @Column(name = "lane_2")
    private int lane2;
    @Column(name = "lane_3")
    private int lane3;

    public Protocol_22_02_2025() {
    }

    public Protocol_22_02_2025(int run, Racer racer, int lane1, int lane2, int lane3) {
        this.run = run;
        this.racer = racer;
        this.lane1 = lane1;
        this.lane2 = lane2;
        this.lane3 = lane3;
    }
}

