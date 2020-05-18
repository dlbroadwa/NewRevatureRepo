package com.ex.model;

import javax.persistence.*;
import java.util.List;

/**
 * This class allows for a difficulty level validation to be applied to database
 * read/writes on the GeoCashe::difficulty variable.
 */
@Entity
@Table(name="\"DifficultyLevels\"", schema = "\"that-team_schema\"")
public class DifficultyLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "difficulty_level_id")
    private int difficultyLevelID;

    @Column(name= "difficulty_level")
    private String difficultyLevel;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "difficultyLevel", cascade = CascadeType.ALL)
    private List<GeoCashe> cashes;


    public DifficultyLevel(){}

    public DifficultyLevel(int difficultyLevelID) {
        this.difficultyLevelID = difficultyLevelID;
    }

    public int getDifficultyLevelID() {
        return difficultyLevelID;
    }

    public void setDifficultyLevelID(int difficultyLevelID) {
        this.difficultyLevelID = difficultyLevelID;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    //    Easy,
//    Medium,
//    Hard,
//    Impossible
}
