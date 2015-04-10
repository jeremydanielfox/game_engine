package engine.goals;

import View.ChangeableSpeed;

/**
 * This goal is satisfied when its changeable speed object can increase its speed.
 * 
 * @author Sierra
 * @author Cosette
 *
 */
public class CanIncSpeedGoal extends ChangeableSpeedGoal {

    public CanIncSpeedGoal(ChangeableSpeed changer){
        super(changer);
    }
    
    @Override
    public void updateSatisfied () {
        setIsSatisfied(getChangeableSpeed().canIncSpeed());
    }
}
