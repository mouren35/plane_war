package game;

public class Fire extends Fly {
    public Fire(int hx, int hy, int person){
        if (person==1){
            img=app.getImg("/img/fire.png");
            w= img.getWidth()/3;
            h=img.getHeight()/3;
        }
        else{
            img=app.getImg("/img/fire2.png");
            w= img.getWidth();
            h=img.getHeight();
        }
        x=hx;
        y=hy;

    };
    public void fireMove(int mx,int my)
    {
        x=mx;
        y=my;
    }
}
