package com.ubs.opsit.interviews;

/**
 * Created by PDeb on 12/17/2017.
 */
public class BerlinClock implements TimeConverter
{
    @Override
    public String convertTime(String aTime)
    {
        String[] timeInString = aTime.split(":");
        Integer hour = Integer.valueOf(timeInString[0]);
        Integer minute = Integer.valueOf(timeInString[1]);
        Integer sec = Integer.valueOf(timeInString[2]);

        boolean isYellowLightOn = checkYellowLight(sec);
        int numberOfRedLightsInFirstRow = calculateRedLightsOfRow1(hour);
        int numberOfRedLightsInSecRow = calculateRedLightsOfRow2(hour);
        int numLightsInThirdRow = calculateLightsOfRow3(minute);
        int numLightsInFourthRow = calculateLightOfRow4(minute);

        String result = resultString(isYellowLightOn, numberOfRedLightsInFirstRow, numberOfRedLightsInSecRow, numLightsInThirdRow, numLightsInFourthRow);
        return result;
    }

    private String resultString(boolean isYellowLightOn, int numberOfRedLightsInFirstRow, int numberOfRedLightsInSecRow, int numLightsInThirdRow, int numLightsInFourthRow)
    {
        String result = "";
        result += printFirstRow(isYellowLightOn);
        result += printSecondRow(numberOfRedLightsInFirstRow);
        result += printThirdRow(numberOfRedLightsInSecRow);
        result += printFourthRow(numLightsInThirdRow);
        result += printFifthRow(numLightsInFourthRow);
        return result;
    }

    private String printFifthRow(int minute) {
        String result = "";
        for (int i = 1; i <= 4; i++) {
            if(i<=minute%5)
                result+=("Y");
            else
                result+=("O");
        }
        return result;
    }

    private String printFourthRow(int numLightsIn4thRow) {
        String result = "";
        for (int i = 1; i <= 11; i++) {
            if(i<=numLightsIn4thRow)
            {
                if(i%3==0)
                    result+=("R");
                else
                    result+=("Y");
            }
            else
                result+=("O");
        }
        return result + "\r\n";
    }

    private String printThirdRow(int numLightsInThirdRow) {
        String result = "";
        for (int i = 0; i < 4; i++) {
            if(i < numLightsInThirdRow)
                result+=("R");
            else
                result+=("O");
        }
        return result + "\r\n";
    }

    private String printSecondRow(int numberOfRedLightsInFirstRow) {
        String result = "";
        for (int i = 0; i < 4; i++) {
            if(i < numberOfRedLightsInFirstRow)
                result+=("R");
            else
                result+=("O");
        }
        return result + "\r\n";
    }

    private String printFirstRow(boolean isYellowLighton) {
        String result = "";
        if(isYellowLighton)
            result+=("Y");
        else
            result+=("O");
        return result  + "\r\n";
    }

    private int calculateLightOfRow4(Integer minute) {
        return minute%5;
    }

    private int calculateLightsOfRow3(Integer minute) {
        return minute/5;
    }


    private int calculateRedLightsOfRow2(Integer hour) {
        return hour%5;
    }

    private int calculateRedLightsOfRow1(Integer hour) {
        return hour/5;
    }

    private boolean checkYellowLight(Integer sec) {
        if(sec%2==0)
            return true;
        else
            return false;
    }
}
