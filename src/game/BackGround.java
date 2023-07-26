package game;

import java.awt.image.BufferedImage;

public class BackGround {
    public BufferedImage img;
    public static int distance=250;
    public BackGround(int grade){

        switch (grade/100)
        {
            case 0:img=app.getImg("/img/bg1.jpg").getSubimage(0,distance,512,476);
                distance-=1;
                if (distance==0)
                    distance+=250;

//                g.setColor(Color.gray);
//                g.setFont(new Font("楷体",Font.BOLD,20));
//                g.drawString("第一关夹缝中生存",170,20);
                break;
            case 1:
            case 2:img=app.getImg("/img/bg2.jpg").getSubimage(0,distance,512,476);
                distance-=1;
                if (distance==0)
                    distance+=250;break;
            case 3:
            case 4:img=app.getImg("/img/bg3.jpg").getSubimage(0,distance,512,476);
                distance-=1;
                if (distance==0)
                    distance+=250;break;
            case 5:
            case 6:img=app.getImg("/img/bg4.jpg").getSubimage(0,distance,512,476);
                distance-=1;
                if (distance==0)
                    distance+=250;break;
            case 7:
            case 8:
            case 9:
            case 10:img=app.getImg("/img/bg5.jpg").getSubimage(0,distance,512,476);
                distance-=1;
                if (distance==0)
                    distance+=250;break;

        }
    }
}
