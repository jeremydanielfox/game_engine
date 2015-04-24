//package engine.gameobject;
//
//import engine.pathfinding.EndOfPathException;
//
//public class MoverGravity extends BasicMover {
//private static final double GRAVITATIONAL_ACCELERATION = 1;//9.08665;
//private double myInitialAngle;
//private double myXPosition;
//private double myYPosition;
//private double myXVelocity;
//private double myYVelocity;
//
//public MoverGravity() {
//    inherentSpeed = 10;
//    myInitialAngle = 45;
//}
//
//    public MoverGravity(double angle) {
//        myInitialAngle = angle;
//        myXVelocity=this.inherentSpeed*Math.cos(myInitialAngle);
//        myYVelocity = this.inherentSpeed*Math.sin(myInitialAngle);
//    }
//
//    @Override
//    public PointSimple move (PointSimple current) throws EndOfPathException {
//        myYVelocity = calculateYVelocity(myYVelocity);
//        myXPosition = calculateXPosition(current.getX());
//        myYPosition = calculateYPosition(current.getY());
//        return new PointSimple(myXPosition,myYPosition);
//    }
//
//    @Override
//    public Mover clone () {
//        // TODO Auto-generated method stub
//        return null;
//    }
//    
//    private double calculateXPosition(double currentX) {
//        return currentX + myXVelocity;
//    }
//    
//    private double calculateYPosition(double currentY) {
//        return currentY + myYVelocity;
//    }
//    
//    private double calculateYVelocity(double currentY) {
//        return currentY + GRAVITATIONAL_ACCELERATION;
//    }
//    
//    
//
//}
