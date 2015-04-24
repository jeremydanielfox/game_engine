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

    public CanDecSpeedGoal () {
        super();
    }

    public CanDecSpeedGoal (ChangeableSpeed changer) {
        super(changer);
    }

    @Override
    public boolean isSatisfied () {
        return getChangeableSpeed().canDecSpeed();
    }

}
