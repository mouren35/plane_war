package game.airplane;

import game.Fly;
import game.app;
import game.prop.Prop;

public class Hero extends Fly {
    public Hero()
    {
        img= app.getImg("/img/hero.png");
        img = img.getSubimage(117, 0, 80, 87);
        x=250;
        y=600;
        w= img.getWidth();
        h=img.getHeight();

        hp=3;
    }
    public void ToMove(int mx,int my)
    {
        x=mx;
        y=my;
    }
    public void HeroMove(int state)
    {
        if (state==1){
            img=app.getImg("/img/hero.png");
//            img = img.getSubimage(52, 0, 66, 87);
            img = img.getSubimage(0, 0, 54, 87);
        }
        else if (state==2){
            img=app.getImg("/img/hero.png");
            img = img.getSubimage(262, 0, 56, 87);
        }
        else {
            img=app.getImg("/img/hero.png");
            img = img.getSubimage(117, 0, 80, 87);
        }
    }
    public boolean shootBy(Ep e)
    {
        boolean bool=x-55<=e.x+e.w&&
                x-55>=e.x-w&&
                y-55<=e.y+h&&
                y-55>=e.y-h;
        return bool;
    }
    public boolean shootBy(Prop p)
    {
        boolean bool=x-55<=p.x+p.w&&
                x-55>=p.x-w&&
                y-55<=p.y+h&&
                y-55>=p.y-h;
        return bool;
    }
}
