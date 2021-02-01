package com.example.emergence_aids;

public class Sensor {
    private String name;
    private int sensetivity;
    private boolean working;

    public Sensor(String name, int sensetivity, boolean working) {
        this.name = name;
        this.sensetivity = sensetivity;
        this.working = working;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSensetivity() {
        return sensetivity;
    }

    public void setSensetivity(int sensetivity) {
        this.sensetivity = sensetivity;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    @Override
    public String toString() {
        return name + " sensor - " + "  sensetivity: " + sensetivity + "   working: " + working;
    }
}

