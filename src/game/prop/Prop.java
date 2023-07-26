package game.prop;

import game.Fly;
import game.app;

import java.util.Random;

public class Prop extends Fly {
    public Prop(){
        img= app.getImg("/img/prop.png").getSubimage(0,0,62,68);
        Random rd=new Random();
        w=img.getWidth();
        h=img.getHeight();
        x=rd.nextInt(512-w);
        y=0;
        direction=0;
    }
    public void ToMove(int px,int py){
        if (px<0)
            direction=1;
        else if(px>512-w){
            direction=0;
        }
        if (direction==0){
            x=px-5;
            y+=5;
        }
        else {
            x=px+5;
            y+=5;
        }
    }
}
