package uiUtilitiesBulychevPI;


import utilitiesBulychevPI.BodyPart;
import utilitiesBulychevPI.Food;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import javax.swing.JPanel;

// Main class of the game.
public class SnakeField extends JPanel implements Serializable, KeyListener {

    private static final long serialVersionUID = 1L;
    public transient static final int MAX_SLEEP = 200, MIN_SLEEP = 20;
    private int xInc, yInc;
    private int bodySize, speed, apples;
    private boolean play, stop, wall;

    private transient Graphics dbi;
    private transient Image img;
    private Color headColor;
    private Color bodyColor;
    private BodyPart head;
    private ArrayList<BodyPart> body;
    private ArrayList<Food> food;
    private transient Snake parent;

    public SnakeField(Color headColor, Color bodyColor, Snake parent) {
        this.xInc = 8;

        this.yInc = 0;
        this.bodySize = 2;
        this.speed = 80;
        this.play = false;
        this.stop = false;
        this.headColor = headColor;
        this.bodyColor = bodyColor;
        this.head = new BodyPart(40, 40, 8, 8, this.headColor);
        this.food = new ArrayList<Food>();
        Food food1 = new Food();
        this.food.add(food1);
        this.parent = parent;
        this.body = new ArrayList<BodyPart>(2);
        this.apples = 1;
        this.wall = false;

        setFocusable(true);
        addKeyListener(this);
    }

    // Accessor and modifier methods.
    public void load(SnakeField temp) {
        this.xInc = temp.xInc;
        this.yInc = temp.yInc;
        this.bodySize = temp.bodySize;
        this.speed = temp.speed;
        this.play = false;
        this.stop = false;

        this.head = temp.head;

        this.body = temp.body;
        this.setApples(temp.apples);
        this.wall = temp.wall;
    }

    public void startGame() {
        play = true;
        stop = false;
    }

    public void pauseGame() {
        play = false;
    }

    public void stopGame() {
        stop = true;
    }

    public boolean isPaused() {
        return (!play);
    }

    public void setHeadColor(Color c) {
        headColor = c;
    }

    public void setBodyColor(Color c) {
        bodyColor = c;
    }

    public Color getHeadColor() {
        return headColor;
    }

    public Color getBodyColor() {
        return bodyColor;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int ms) {
        speed = ms;
    }

    public int getApples() {
        return apples;
    }

    public boolean foodIntersect(Food nf) {
        if (food.size() != 0) {
            Rectangle newRectangle = new Rectangle(nf.getX(), nf.getY(), nf.getWidth(), nf.getHeight());
            for (Food foo : food) {
                if (newRectangle.intersects(new Rectangle(foo.getX(), foo.getY(), foo.getWidth(), foo.getHeight()))) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setApples(int n) {
        apples = n;
        food.clear();

        for (int f = 0; f < n ; f++) {
            Food newFood;
            do {
                newFood = new Food();

            } while (foodIntersect(newFood));

            food.add(newFood);

             ////////////////////////////
//		for ( int f = 0; f < n; f++ ) {
//			Food foo = new Food();
//			food.add( foo );
//		}
        }
    }

    public void activeWalls() {
        wall = true;
    }

    public void disableWalls() {
        wall = false;
    }

    public boolean wallsEnabled() {
        return wall;
    }

    public void reset() {
        head.setX(40);
        head.setY(40);
        xInc = 8;
        yInc = 0;
        bodySize = 2;
        body.clear();
    }

    // Double buffering.
    public void paint(Graphics g) {
        img = createImage(getWidth(), getHeight());
        dbi = img.getGraphics();

        paintComponent(dbi);
        g.drawImage(img, 0, 0, this);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (stop) {
            g.setFont(new java.awt.Font("Comic Sans MS", 0, 20));
            g.drawString("...Game Over...", 140, 150);
        } else {
            if (play) {
                sleep(speed);
                g.setColor(Color.RED);
                for (int f = 0; f < food.size(); f++) {
                    g.drawImage(food.get(f).getImage(), food.get(f).getX(), food.get(f).getY(), null);
                }
                checkFoodCollision();
                if (checkBodyCollision()) {
                    endGame();
                }
                if (body.size() > 2) {
                    body = new ArrayList<BodyPart>(body.subList(body.size() - bodySize, body.size()));
                    g.setColor(bodyColor);
                    for (int i = body.size() - 1; i >= body.size() - bodySize; i--) {
                        g.fillRect(body.get(i).getX(), body.get(i).getY(), body.get(i).getWidth(), body.get(i).getHeight());
                    }
                }
                g.setColor(headColor);
                g.fillRect(head.getX(), head.getY(), head.getWidth(), head.getHeight());
                BodyPart tempB = new BodyPart(head.getX(), head.getY(), head.getWidth(), head.getHeight(), bodyColor);
                body.add(tempB);

                if (!checkWallHits());
                {
                    head.setX(head.getX() + xInc);
                    head.setY(head.getY() + yInc);
                }
            } else if (!play) {
                g.setFont(new java.awt.Font("Comic Sans MS", 0, 20));
                g.drawString("...Game paused...", 125, 150);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        switch (arg0.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (yInc != 8) {
                    xInc = 0;
                    yInc = -8;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (yInc != -8) {
                    xInc = 0;
                    yInc = 8;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (xInc != 8) {
                    xInc = -8;
                    yInc = 0;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (xInc != -8) {
                    xInc = 8;
                    yInc = 0;
                }
                break;
            case KeyEvent.VK_S:
                if (speed != MAX_SLEEP) {
                    speed += 10;
                }
                break;
            case KeyEvent.VK_D:
                if (speed != MIN_SLEEP) {
                    speed -= 10;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void endGame() {
        stop = true;
        reset();

    }

    /**
     * Checks whether the snake hits a wall.
     */
    private boolean checkWallHits() {
        if (!wall) {
            if (head.getX() + xInc > 384) {
                head.setX(-8);
                //head.setY(head.getY() + yInc);
                return true;
            } else if (head.getX() + xInc < -8) {
                head.setX(392 + xInc);
                //head.setY(head.getY() + yInc);
                return true;
            } else if (head.getY() + yInc > 344) {
                //head.setX(head.getX() + xInc);
                head.setY(-8);
                return true;
            } else if (head.getY() + yInc < 0) {
                //head.setX(head.getX() + xInc);
                head.setY(352 + yInc);
                return true;
            } else {
                return false;
            }
        } else {
            if (head.getX() + xInc > 388) {
                endGame();
                return true;
            } else if (head.getX() + xInc < -7) {
                endGame();
                return true;
            } else if (head.getY() + yInc > 343) {
                endGame();
                return true;
            } else if (head.getY() + yInc < 0) {
                endGame();
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Checks whether the snake has eaten the food.
     */
    private void checkFoodCollision() {
        for (int f = 0; f < food.size(); f++) {
            Rectangle headRectangle = new Rectangle(head.getX(), head.getY(), head.getWidth(), head.getHeight());
            Rectangle food1Rectangle = new Rectangle(food.get(f).getX(), food.get(f).getY(), food.get(f).getWidth(), food.get(f).getHeight());
            if (headRectangle.intersects(food1Rectangle)) {

                Food nf;
                do{
                    nf=new Food();
                }while(foodIntersect(nf));
                food.remove(f);
                food.add(nf);
                bodySize++;
                break;
            }
        }
    }

    /**
     * Checks whether the snake has hit itself.
     */
    private boolean checkBodyCollision() {
        if (body.size() > 2) {
            for (int i = body.size() - 1; i >= body.size() - bodySize; i--) {
                if (head.getX() == body.get(i).getX() && head.getY() == body.get(i).getY()) {
                    return true;
                }
            }
        }
        return false;
    }
}
