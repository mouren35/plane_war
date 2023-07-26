package 窗口;

import game.*;
import game.airplane.ClientHero;
import game.airplane.Ep;
import game.airplane.Hero;
import game.prop.Prop;
import online.Client.Client;
import game.airplane.Ep;
import online.Client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class panel extends JPanel {
    int grade = 0;
    int x = 0;
    int firepower = 1;
    BufferedImage bg;
    BufferedImage bg0 = app.getImg("/img/bg0.png");
    int chose = 0;
    Hero hero = new Hero();
    ClientHero clientHero = new ClientHero();
    List<Ep> eps = new ArrayList<Ep>();
    List<Bang> Bs = new ArrayList<Bang>();
    List<Fire> fires = new ArrayList<Fire>();
    List<Prop> props = new ArrayList<Prop>();
    Client client = new Client();


    public panel() {

    }


    public void action() {
        new Thread(() -> {
            while (hero.hp != 0) {

                fireEnter();
                fireMove();
                epEnter();
                epmove();
                propEnter();
                propMove();
                try {
                    shootEp();
                    getProp();
                    repaint();
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getProp() throws InterruptedException {
        for (int i = 0; i < props.size(); i++) {
            Prop p = props.get(i);
            if (hero.shootBy(p)) {
                props.remove(p);
                hero.state = 1;
                repaint();
                new Thread(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(3000);
                        hero.state = 0;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();

            } else if (clientHero.shootBy(p)) {
                props.remove(p);
                hero.state = 1;
                repaint();
                new Thread(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(3000);
                        hero.state = 0;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }

    private void shootEp() throws InterruptedException {
        for (int i = 0; i < fires.size(); i++) {
            Fire f = fires.get(i);
            shootfire(f);
        }
    }

    private void shootfire(Fire f) throws InterruptedException {
        for (int i = 0; i < eps.size(); i++) {
            Ep e = eps.get(i);
            if (hero.shootBy(e)) {
                eps.remove(e);
                if (hero.state == 0)
                    hero.hp--;
                repaint();
            }
            if (e.shootBy(f)) {
                switch (firepower) {
                    case 1:
                        e.hp--;
                        break;
                    case 2:
                        e.hp -= 2;
                        break;
                    case 3:
                        e.hp -= 3;
                        break;
                }
                fires.remove(f);
                if (e.hp <= 0) {
                    grade += e.index * 3;
                    Bang b = new Bang(e.x, e.y);
                    Bs.add(b);
                    eps.remove(e);
                    repaint();
                    TimeUnit.MILLISECONDS.sleep(60);
                    Bs.remove(b);
                    if (e.index == 10 && firepower < 3) {
                        firepower++;
                        clientHero.Upgrade();
                    }
                }


            }
        }
    }

    private void fireEnter() {
        if (x % 8 == 0) {
            Fire fire = new Fire(hero.x - 15, hero.y - 80, 1);
            Fire fire2 = new Fire(clientHero.x - 15, clientHero.y - 80, 2);
            fires.add(fire);
            fires.add(fire2);
        }

    }

    private void fireMove() {
        for (Fire fire : fires) {
            fire.fireMove(fire.x, fire.y - 40);
            repaint();
        }

    }

    private void epmove() {
        for (Ep ep : eps) {
            ep.ToMove();
            repaint();
        }
    }

    public void epEnter() {
        switch (grade / 100) {
            case 0:
                x++;
                if (x % 6 == 0) {
                    Ep e = new Ep(grade);
                    eps.add(e);

                }
                break;
            case 1:
            case 2:
                x++;
                if (x % 15 == 0) {
                    Ep e = new Ep(grade);
                    eps.add(e);

                }
                break;
            case 3:
            case 4:
                x++;
                if (x % 10 == 0) {
                    Ep e = new Ep(grade);
                    eps.add(e);
                }
                break;
            case 5:
            case 6:
                x++;
                if (x % 8 == 0) {
                    Ep e = new Ep(grade);
                    eps.add(e);
                }
                break;
            case 7:
            case 8:
            case 9:
            case 10:
                x++;
                if (x % 5 == 0) {
                    Ep e = new Ep(grade);
                    eps.add(e);
                }
                break;
        }

    }

    public void propEnter() {
        if (x % 200 == 0) {
            Prop prop = new Prop();
            props.add(prop);
        }
    }

    public void propMove() {
        for (Prop prop : props) {
            prop.ToMove(prop.x, prop.y);
        }
        repaint();

    }

    public panel(Frame frame) {
        bg = app.getImg("/img/bg3.jpg");
        KeyAdapter key = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int KeyCode = e.getKeyCode();
                if (KeyCode == 16) {
                    chose++;
                }
                MouseAdapter adapter = new MouseAdapter() {
                    @Override
                    public void mouseMoved(MouseEvent e) {
                        if (chose % 2 == 1) {
                            int mx = e.getX();
                            int my = e.getY();
                            hero.ToMove(mx, my);
                            repaint();
                        }
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (hero.hp == 0) {
                            hero.hp = 3;
                            action();
                        }

                    }
                };
                addMouseListener(adapter);
                addMouseMotionListener(adapter);
                if (KeyCode == 38) {
                    if (hero.y - 10 > 0) {
                        hero.ToMove(hero.x, hero.y - 10);
                        hero.HeroMove(0);
                    }
                } else if (KeyCode == 40) {
                    if (hero.y + 10 < 726) {
                        hero.ToMove(hero.x, hero.y + 10);
                        hero.HeroMove(0);
                    }
                } else if (KeyCode == 37) {
                    if (hero.x - 60 > 0) {
                        hero.ToMove(hero.x - 10, hero.y);
                        hero.HeroMove(1);
                    }

                } else if (KeyCode == 39) {
                    if (hero.x + 60 < 512) {
                        hero.ToMove(hero.x + 10, hero.y);
                        hero.HeroMove(2);
                    }
                }
//                try {
//                    client.talk(KeyCode);
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }
                repaint();

            }

        };
        frame.addKeyListener(key);

    }

    public void paint(Graphics g) {
        super.paint(g);
        BackGround backGround = new BackGround(grade);
        g.drawImage(backGround.img, 0, 0, 512, 726, null);
        g.drawImage(hero.img, hero.x - 55, hero.y - 55, 100, 80, null);
        if (hero.state == 1)
            g.drawImage(app.getImg("/img/Shield.png"), hero.x - 75, hero.y - 75, 140, 120, null);
        g.drawImage(clientHero.img, ClientHero.x - 55, ClientHero.y - 55, 100, 80, null);
        for (int i = 0; i < eps.size(); i++) {
            Ep e = eps.get(i);
            g.drawImage(e.img, e.x, e.y, e.w, e.h, null);
            if (e.hp > 0) {
                BufferedImage hp = Objects.requireNonNull(app.getImg("/img/hp.png")).getSubimage(20, 48, 20 * e.hp, 34);
                g.drawImage(hp, e.x + 10, e.y + e.h + 1, hp.getWidth(), hp.getHeight() / 2, null);
            }
        }
        for (int i = 0; i < Bs.size(); i++) {
            Bang b = Bs.get(i);
            g.drawImage(b.img, b.x, b.y, b.w, b.h, null);
        }

        for (int i = 0; i < props.size(); i++) {
            if (props.get(i) != null) {
                Prop p = props.get(i);
                if (p.y > 726)
                    props.remove(p);
                g.drawImage(p.img, p.x, p.y, p.w, p.h, null);
            }

        }

        switch (firepower) {
            case 1:
                for (int i = 0; i < fires.size(); i++) {
                    Fire f = fires.get(i);
                    g.drawImage(f.img, f.x, f.y, f.w, f.h, null);
                }
                break;
            case 2:
                for (int i = 0; i < fires.size(); i++) {
                    Fire f = fires.get(i);
                    g.drawImage(f.img, f.x - 25, f.y - 10, f.w, f.h, null);
                    g.drawImage(f.img, f.x + 30, f.y - 10, f.w, f.h, null);
                }
                break;
            case 3:
                for (Fire f : fires) {
                    g.drawImage(f.img, f.x - 25, f.y - 10, f.w, f.h, null);
                    g.drawImage(f.img, f.x + 30, f.y - 10, f.w, f.h, null);
                    g.drawImage(f.img, f.x, f.y - 20, f.w, f.h, null);
                }
                break;
            default:
                break;
        }
        g.setColor(Color.white);
        g.setFont(new Font("楷体", Font.BOLD, 25));
        g.drawString("分数:" + grade, 10, 30);
        for (int i = 0; i < hero.hp; i++) {
            g.drawImage(app.getImg("/img/hero.png").getSubimage(117, 0, 80, 87), i * 30 + 400, 10, hero.w / 4, hero.h / 4, null);
        }
        if (hero.hp == 0) {
            grade = 0;
            firepower = 1;
            eps.clear();
            fires.clear();
            g.drawImage(bg0, 0, 0, bg0.getWidth() * 2, bg0.getHeight() * 2, null);
            g.setColor(Color.red);
            g.setFont(new Font("楷体", Font.BOLD, 50));
            g.drawString("Game Over", 140, 300);
            g.setColor(Color.red);
            g.setFont(new Font("楷体", Font.BOLD, 20));
            g.drawString("是兄弟就点鸡屏幕", 170, 400);

        }

    }

}
