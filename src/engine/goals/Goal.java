package engine.goals;

import java.util.Observer;


/**
 * This class observes some GameObject and changes a field once a particular condition is met
 * 
 * @author Cosette Goldstein and Sierra Smith
 *
 */

public abstract class Goal implements Observer {

    private boolean isSatisfied;

    public boolean isSatisfied () {
        return isSatisfied;
    }

    public void setIsSatisfied (boolean satisfied) {
        isSatisfied = satisfied;
        System.out.println("setting isSatisfied to:"+satisfied);
    }

    public boolean getIsSatisfied() {
        return isSatisfied;
    }
}
