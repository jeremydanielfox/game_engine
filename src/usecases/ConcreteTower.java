package usecases;

import gameobject.Tower;
import gameobject.Weapon;
import gameworld.GraphicsComponent;
import gameworld.MovementComponent;
import gameworld.PhysicsComponent;

import java.util.Collection;
import java.util.Collections;

import javafx.geometry.Point2D;


public class ConcreteTower implements Tower {

    private Collection<Weapon> weapons;

    private MovementComponent mover;
    private PhysicsComponent physics;
    private GraphicsComponent graphics;

    public ConcreteTower (MovementComponent mover,
                          PhysicsComponent physics,
                          GraphicsComponent graphics) {
        this.mover = mover;
        this.physics = physics;
        this.graphics = graphics;
    }

    @Override
    public void changeHealth (double amount) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isDead () {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void updatePosition () {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePhysics () {
        physics.update(this);

    }

    @Override
    public void updateGraphics () {
        // TODO Auto-generated method stub

    }

    @Override
    public void addWeapon (Weapon weapon) {
        // TODO Auto-generated method stub

    }

    @Override
    public Collection<Weapon> getWeapons () {
        return Collections.unmodifiableCollection(weapons);
    }

    @Override
    public String getImage () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Point2D getLocation () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onBorn () {
        // TODO Auto-generated method stub

    }

	@Override
	public String getIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

}
