package game.airplane;

import game.Fire;
import game.Fly;
import game.app;
import 窗口.panel;

import java.util.Random;

public class Ep extends Fly {
    public Ep(int grade)
    {
        panel p=new panel();
        Random rd=new Random();
//        System.out.println(grade);
        switch (grade/100)
        {
            case 0:
                index= (int) (Math.random() * 5 + 1);
                break;
            case 1:
            case 2:index= (int) (Math.random() * 11 + 1);break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:index= (int) (Math.random() * 14 + 1);break;
            case 10:index= (int) (Math.random() * 15 + 1);break;
        }
        speed=17-index;
        if (index<=7)
        {
            hp=2;
        }
        else if (index<=10)
        {
            hp=3;
        }
        else if (index<=13)
        {
            hp=5;
        }
        else
        {
            hp=40;
        }
        img=index<10? app.getImg("/img/ep0" +index+".png"):app.getImg("/img/ep" +index+".png");
        w= img.getWidth();
        x=rd.nextInt(512-w);
        y=0;
        h=img.getHeight();

    }
    public void ToMove()
    {
        if (index==5)
        {
            x-=4;
            y=y+speed;
        }
        else if (index==6)
        {
            x+=4;
            y=y+speed;
        }
        else
        {
            y+=speed;
        }
    }
    public boolean shootBy(Fire f)
    {
        boolean hit=x<=f.x+f.w&&
                x>=f.x-w&&
                y<=f.y+f.h&&
                y>=f.y-h;
        return hit;
    }
}
