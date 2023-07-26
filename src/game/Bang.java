package game;

public class Bang extends Fly {
    public Bang(int bx, int by){
      img=app.getImg("/img/baozha.png").getSubimage(167,0,95,96);
      x=bx;
      y=by;
      w=img.getWidth()/2;
      h=img.getHeight()/2;
    }
}
