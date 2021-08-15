
package com.company;
/* Here we actually set the temperatures by months and the maximum and minimum temperature and the returns of temps
 */
public class SetTemp {

    final int M_SIZE = 13; //number of months in a year +1
    private int[] avgTemp = new int[M_SIZE]; ;
    private int maxTemp = -100;
    private int minTemp = 100;

    public void setYear(int year)
    {
        avgTemp[M_SIZE-1] = year;
    }

    public void setTemp(int month, int temp)
    {
        avgTemp[month] = temp;
        if (temp > maxTemp)
            maxTemp = temp;

        if (temp < minTemp)
            minTemp = temp;
    }

    public int getTemp(int month)
    {
        return avgTemp[month];
    }

    public int getMaxTemp()
    {
        return maxTemp;
    }

    public int getMinTemp()
    {
        return minTemp;
    }
}