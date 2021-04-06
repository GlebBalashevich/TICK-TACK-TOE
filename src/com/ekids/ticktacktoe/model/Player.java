package com.ekids.ticktacktoe.model;

import java.util.Objects;

public class Player {

    private String name;
    private ActionType type;

    public Player(String name, ActionType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActionType getType() {
        return type;
    }

    public void setType(ActionType type) {
        this.type = type;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;

        return Objects.equals(name, player.name) && type == player.type;
    }

    @Override public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
