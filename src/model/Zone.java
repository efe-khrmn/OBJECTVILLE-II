package model;

import map.Cell;

public abstract class Zone extends Cell {
    protected int level;

    protected int electricityReceived;
    protected int waterReceived;
    protected int internetReceived;

    protected boolean hasSecurity;
    protected boolean hasHealth;
    protected boolean hasEducation;

    protected int populationReceived;
    protected int goodsReceived;
    protected int lifestyleReceived;

    protected int currentOutput;
    protected int nextDemand;

    public Zone(int row, int col, char symbol) {
        super(row, col, symbol);
        this.level = 0;
        this.nextDemand = 1;
    }

    @Override
    public boolean isZone() {
        return true;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    public int getLevel() {
        return level;
    }

    public int getCurrentOutput() {
        return currentOutput;
    }

    public int getNextDemand() {
        return nextDemand;
    }

    public void receiveElectricity(int amount) {
        electricityReceived += amount;
    }

    public void receiveWater(int amount) {
        waterReceived += amount;
    }

    public void receiveInternet(int amount) {
        internetReceived += amount;
    }

    public void receiveSecurity() {
        hasSecurity = true;
    }

    public void receiveHealth() {
        hasHealth = true;
    }

    public void receiveEducation() {
        hasEducation = true;
    }

    public void receivePopulation(int amount) {
        populationReceived += amount;
    }

    public void receiveGoods(int amount) {
        goodsReceived += amount;
    }

    public void receiveLifestyle(int amount) {
        lifestyleReceived += amount;
    }

    protected int minUtility(int... values) {
        int min = values[0];

        for (int value : values) {
            if (value < min) {
                min = value;
            }
        }

        return min;
    }

    protected void increaseLevelByOne() {
        if (level < 3) {
            System.out.println(getTypeName() + " at (" + row + "," + col + ") levels up from " + level + " to " + (level + 1));
            level++;
        }
    }

    protected void decreaseLevelByOne() {
        if (level > 0) {
            System.out.println(getTypeName() + " at (" + row + "," + col + ") levels down from " + level + " to " + (level - 1));
            level--;
        }
    }

    public abstract String getTypeName();

    public void resetReceivedValues() {
        electricityReceived = 0;
        waterReceived = 0;
        internetReceived = 0;

        hasSecurity = false;
        hasHealth = false;
        hasEducation = false;

        populationReceived = 0;
        goodsReceived = 0;
        lifestyleReceived = 0;
    }

    public abstract void update();

    public boolean hasSecurity() {
        return hasSecurity;
    }

    public boolean hasHealth() {
        return hasHealth;
    }

    public boolean hasEducation() {
        return hasEducation;
    }
}