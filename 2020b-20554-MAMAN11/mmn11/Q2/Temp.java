package com.company;

/* Temp.java - The app contains high and low temperatures from 2015 and 2019 as
 * the user enters the year and receives a histogram graph of the average monthly temperature of the same year
 * Written 04/2020 Emmanuel Fhal for MMN11-Q2.
 */

import javax.swing.*;

/**
 *
 * The main purpose of the program is to show the user a monthly average graph of temperature in the
 * year the from the user input. The user can only enter a year between 2015 and 2019 and if not he will receive
 * an error message with the option to write a correct year.
 * The dialog ends when the user doesn't tap any year(and leave it blank). The user can also exit the program using X at the windows corner.
 */


public class Temp {

    private static int[] tempHigh = {11, 13, 15, 21, 25, 27, 29, 35, 33, 31, 17, 14};//High temp values
    private static int[] tempLow = {10, 12, 14, 20, 22, 24, 26, 34, 32, 30, 16, 18};//Low temp values

    public static void main(String[] args) {

        int year;
        int temp;

        SetTemp myTemp = new SetTemp();

        JOptionPane.showMessageDialog(null, "Welcome to the 2015-2019 Average Monthly Temperatures!", "Average Temps Graph", JOptionPane.PLAIN_MESSAGE);//promo


        while ((year = Integer.parseInt(JOptionPane.showInputDialog("Enter a year between 2015 to 2019"))) != 0) {//stop the loop only when the int input is empty (0)

            boolean isValidYear = false;

            if (year >= 2015 && year <= 2019) {//Is valid only if the year between 2015 to 2019
                isValidYear = true;
            } else {
                JOptionPane.showMessageDialog
                        (null, "Please choose a year between 2015 to 2019");
            }
            if (isValidYear) {//continue only if the year is valid
                myTemp.setYear(year);
                for (int i = 0; i < 12; i++) {
                    temp = (int) (Math.random() * ((tempHigh[i] - tempLow[i]) + 1)) + tempLow[i];//set random value from range of hightemp to lowtemp
                    myTemp.setTemp(i, temp);//Add the temp to myTemp array by month (i) number
                    System.out.println(temp);
                }

                //JFrame
                JFrame frame = new JFrame("Average Monthly Temperatures");//JFrame name
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Allow of exit on close
                frame.setSize(720, 540);//Allow of exit on close
                PrintTemp P = new PrintTemp(myTemp);//Print the temperature JPanel
                frame.add(P);
                frame.setVisible(true);
            }
        }

    }
}