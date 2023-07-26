package game.airplane;

import game.Fly;
import game.app;
import game.prop.Prop;

public class ClientHero extends Fly {
    public static int x=550;
    public static int y=600;

    public ClientHero()
    {
        state=0;
        img= app.getImg("/img/clientHero.png");
        w= img.getWidth();
        h=img.getHeight();
        hp=3;
    }
    public void ToMove(int mx,int my)
    {
        x=mx;
        y=my;
    }
    public void Upgrade(){
        img=app.getImg("/img/clientHero2.png");
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
