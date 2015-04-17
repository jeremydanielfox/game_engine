package gameworld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javafx.scene.Node;
import javafx.scene.shape.Circle;
import engine.fieldsetting.Settable;
import engine.gameobject.GameObject;
import engine.gameobject.GameObjectSimpleTest;
import engine.gameobject.PointSimple;
import engine.gameobject.test.TestTower;
import engine.gameobject.units.Buffable;
import engine.gameobject.weapon.firingstrategy.Buffer;
import engine.grid.Grid;
import engine.grid.GridFree;
import engine.interactions.InteractionEngine;
import engine.pathfinding.EndOfPathException;
import engine.pathfinding.Path;


public class FixedWorld extends AbstractWorld {
	private double myPathWidth = 10; //TODO settable
	private Path myPath;
	
    @Override
    public boolean isPlacable (Node n, PointSimple pixelCoords) {
    	//System.out.println(pixelCoords);
    	int i = 0;
    	Circle c = new Circle(myPathWidth);
    	while(true){
    		try {
				PointSimple pathPoint = myPath.getNextLocation(i);
				c.setCenterX(pathPoint.getX());
				c.setCenterY(pathPoint.getY());
//				System.out.printf("%f, %f, %f", pathPoint.getX(), pathPoint.getY(), PointSimple.distance(pathPoint, myPath.getNextLocation(i+1)));
//				if(c.getBoundsInParent().intersects(n.getBoundsInParent())){
//				System.out.println(PointSimple.distance(pathPoint, pixelCoords));

				if(pathPoint.withinRange(pixelCoords, 100)){
					System.out.println("false");
					return false;
				}
			} catch (EndOfPathException e) {
				break;
			}
    		i++;
    	}
    	System.out.println("true");
    	return true;
    }
    
    @Settable
    public void setPath(Path p){
    	myPath = p;
    }
    
    @Settable
    public void setPathWidth(double width){
    	myPathWidth = width;
    }

}
