package in.jd.main.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Player {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String role;
    private double netWorth;
    private int totalMatches;
    private int totalRuns;
    private String bio;
    private String photo;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

}
