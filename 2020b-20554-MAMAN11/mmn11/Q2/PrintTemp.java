package com.company;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

/* extends JPanel and print temperature focusing on the colors and coordinates
 */
public class PrintTemp extends JPanel{
    private static String months[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    private SetTemp myTemp;

    public PrintTemp(SetTemp myTemp)
    {
        this.myTemp = myTemp;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        final int C_X = width/16; //16:9 ratio
        final int C_Y = height/9;

        final int tempRange = C_X/5; //divided by 5 level/step at coordinate Y
        int showTemp = 0;

        int year;
        int temp;
        int maxTemp;
        int minTemp;

        //set white background and title
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        year = myTemp.getTemp(12);
        g.drawString("The average Temperature for the year " + year + ":", C_X*6, height-C_Y*8);

        //the max/min temp in the graph
        maxTemp = myTemp.getMaxTemp();
        minTemp = myTemp.getMinTemp();

        //the coordinate's line (X/Y)
        g.setColor(Color.BLACK);
        g.drawLine(C_X*2, height-C_Y*2, width-C_X*2, height-C_Y*2); //line X
        g.drawLine(C_X*2, C_Y*2, C_X*2, height-C_Y*2); //line Y

        //the graph coordinate Y (temp)
        g.setColor(Color.BLACK);
        for (int i=0; i<5; i++)//divided by 5 level/step at coordinate Y
        {
            g.drawString("" + showTemp, C_X, height-C_Y*(i+2));
            showTemp += tempRange;
        }

        //the graph coordinate X (months)
        g.setColor(Color.BLACK);
        for (int i=0; i<12; i++)
        {
            g.drawString("" + months[i], C_X*(i+2)+C_X/9, height-C_Y);
        }

//set color red if is max temp and blue if is min else grey
        for (int i=0; i<months.length; i++)
        {
            g.setColor(Color.GRAY);
            temp = myTemp.getTemp(i);
            if (temp == maxTemp)
                g.setColor(Color.RED);
            if (temp == minTemp)
                g.setColor(Color.BLUE);
            int barHeight =
                    (int) (((double) temp / (double) maxTemp) * (height -C_Y*4));//bar height
            g.fillRect(C_X*(i+2), (height-C_Y*2)-barHeight, C_X/2, barHeight);
        }
    }

}
