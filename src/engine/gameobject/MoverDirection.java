package engine.gameobject;

import engine.pathfinding.EndOfPathException;

//WE MIGHT NOT NEED THIS CLASS
public class MoverDirection implements Mover{
    private double speed;
    private double distance;
    private double distanceTraveled;
    private double direction;
    
    //TODO: Use hierarchy to remove this constructor and setSpeed method
    public MoverDirection(double distance, double direction, double speed){
        this.distance = distance;
        this.direction = direction;
        this.speed = speed;
        distanceTraveled = 0;
    }
    
    @Override
    public PointSimple move (PointSimple current) throws EndOfPathException {
        distanceTraveled+=speed;
        if(distanceTraveled>distance)
            throw new EndOfPathException();
        double newX = current.getX() + speed * Math.cos(Math.toRadians(direction));
        double newY = current.getY() + speed * Math.sin(Math.toRadians(direction));
        return new PointSimple(newX, newY);
    }

    @Override
    public void setSpeed (double speed) {
        this.speed = speed;    
    }
    
    public Mover clone (){
        return new MoverDirection(distance, direction, speed);
    }

}
