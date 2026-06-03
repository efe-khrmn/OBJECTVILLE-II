package model;

public class IndustrialZone extends Zone {

    public IndustrialZone(int row, int col) {
        super(row, col, 'I');
    }

    @Override
    public void update() {
        boolean hasBasicUtilities =
                electricityReceived > 0 &&
                        waterReceived > 0;

        if (!hasBasicUtilities) {
            level = 0;
            currentOutput = 0;
            nextDemand = 1;
            System.out.println("Industrial at (" + row + "," + col + ") generated " + currentOutput + " goods");
            resetReceivedValues();
            return;
        }

        boolean canBeLevel1 =
                populationReceived > 0 &&
                        hasBasicUtilities;

        boolean canBeLevel2 =
                canBeLevel1 &&
                        hasSecurity;

        boolean canBeLevel3 =
                canBeLevel2 &&
                        populationReceived > 1;

        int targetLevel;

        if (canBeLevel3) {
            targetLevel = 3;
        } else if (canBeLevel2) {
            targetLevel = 2;
        } else if (canBeLevel1) {
            targetLevel = 1;
        } else {
            targetLevel = 0;
        }

        if (targetLevel > level) {
            increaseLevelByOne();
        } else if (targetLevel < level) {
            decreaseLevelByOne();
        }

        int m = minUtility(electricityReceived, waterReceived);

        if (level == 0) {
            currentOutput = 0;
        } else if (level == 1) {
            currentOutput = m;
        } else if (level == 2) {
            currentOutput = 2 * m;
        } else {
            currentOutput = 2 * m + populationReceived;
        }

        nextDemand = Math.max(1, currentOutput);

        resetReceivedValues();
    }
    @Override
    public String getTypeName() { return "Industrial"; }
}