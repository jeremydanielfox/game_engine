package engine.goals;

import View.ChangeableSpeed;

/**
 * This goal is satisfied when its changeable speed object can decrease its speed.
 * 
 * @author Sierra
 * @author Cosette
 *
 */
public class CanDecSpeedGoal extends ChangeableSpeedGoal {

    public CanDecSpeedGoal(ChangeableSpeed changer){
        super(changer);
    }
    
    @Override
    public void updateSatisfied () {
        setIsSatisfied(getChangeableSpeed().canDecSpeed());
    }
    
}
