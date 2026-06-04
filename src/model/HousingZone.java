package model;

public class HousingZone extends Zone {

    public HousingZone(int row, int col) {
        super(row, col, 'H');
    }

    @Override
    public void update() {
        boolean hasBasicUtilities =
                electricityReceived > 0 &&
                        waterReceived > 0 &&
                        internetReceived > 0;

        if (!hasBasicUtilities) {
            level = 0;
            currentOutput = 0;
            nextDemand = 1;
            System.out.println("House at (" + row + "," + col + ") generated " + currentOutput + " population");
            resetReceivedValues();
            return;
        }

        boolean canBeLevel1 = hasBasicUtilities;

        boolean canBeLevel2 =
                canBeLevel1 &&
                        hasSecurity &&
                        hasHealth &&
                        hasEducation;

        boolean canBeLevel3 =
                canBeLevel2 &&
                        lifestyleReceived > 0;

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

        int futureLevel = level;
        if (targetLevel > level) {
            futureLevel = level + 1;
        } else if (targetLevel < level) {
            futureLevel = level - 1;
        }

        int m = minUtility(electricityReceived, waterReceived, internetReceived);

        if (futureLevel == 0) {
            currentOutput = 0;
        } else if (futureLevel == 1) {
            currentOutput = m;
        } else if (futureLevel == 2) {
            currentOutput = 2 * m;
        } else {
            currentOutput = 2 * m + lifestyleReceived;
        }

        nextDemand = Math.max(1, currentOutput);

        System.out.println("House at (" + row + "," + col + ") generated " + currentOutput + " population");

        int oldLevel = level;
        if (targetLevel > level) {
            increaseLevelByOne();
        } else if (targetLevel < level) {
            decreaseLevelByOne();
        }
        resetReceivedValues();
    }
    @Override
    public String getTypeName() { return "House"; }
}