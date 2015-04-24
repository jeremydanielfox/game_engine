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

    public CanIncSpeedGoal () {
        setIsSatisfied(true);
    }

    public CanIncSpeedGoal (ChangeableSpeed changer) {
        super(changer);
    }

    @Override
    public boolean isSatisfied () {
        return getChangeableSpeed().canIncSpeed();
    }
}
