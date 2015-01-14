/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package uiUtilitiesBulychevPI;

import java.io.Serializable;
import utilitiesBulychevPI.Timer;



/**
 *
 * @author HP
 */
public class SavingObject implements Serializable {
SnakeField savingField;
Timer savingTimer;
int score, apples;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getApples() {
        return apples;
    }

    public void setApples(int apples) {
        this.apples = apples;
    }

    public SavingObject(SnakeField s,Timer t) {
        this.savingField=s;
        this.savingTimer=t;
        
        
    }
    public SnakeField getField(){
        return savingField;    
    }
    public Timer getTimer(){
        return savingTimer;    
    }

    
}
