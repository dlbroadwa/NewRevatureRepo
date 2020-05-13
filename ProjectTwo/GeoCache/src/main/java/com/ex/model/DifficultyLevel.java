package com.ex.model;

import com.sun.javafx.beans.IDProperty;
import sun.java2d.loops.GeneralRenderer;

import javax.persistence.*;
import java.util.List;

/**
 * This class allows for a difficulty level validation to be applied to database
 * read/writes on the GeoCashe::difficulty variable.
 */
@Entity
@Table(name="\"DifficultyLevel\"", schema = "\"that-team_schema\"")
public class DifficultyLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "difficulty_level_id")
    private int difficultyLevelID;

    @Column(name= "difficulty_level")
    private String difficultyLevel;

    @OneToMany(mappedBy = "difficultyLevel", cascade = CascadeType.ALL)
    private List<GeoCashe> cashes;


    public DifficultyLevel(){}

    public DifficultyLevel(int difficultyLevelID, String difficultyLevel) {
        this.difficultyLevelID = difficultyLevelID;
        this.difficultyLevel = difficultyLevel;
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
