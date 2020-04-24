package com.ex.model;

/**
 * Player class extends the person class for more defined data.  Coaches dont belong to this class
 * only children playing little league.
 * @param parent - the name of the parent
 * @param age - the age of the child/player
 * @param position - enum of the position they are assigned to play
 */
public class Player extends Person {
    private String parent;
    private int age;
    private Position position;

    public Player() {
        super();
        this.parent = "";
        this.age = 0;
        this.position = Position.Catcher;
    }

    public Player(String name, String phone, String emergencyphone, String parent, int age, Position position) {
        super(name, phone, emergencyphone);
        this.parent = parent;
        this.age = age;
        this.position = position;
    }

    /* =================    GET & SET   ======================= */
    public String getParent() {
        return parent;
    }
    public void setParent(String parent) {
        this.parent = parent;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + getName() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", emergencyphone='" + getEmergencyPhone() + '\'' +
                "parent='" + parent + '\'' +
                ", age=" + age +
                ", position=" + position +
                '}';
    }
}
