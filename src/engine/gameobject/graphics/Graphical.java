package engine.gameobject.graphics;


public interface Graphical {

    /**
     * Returns the graphical representation of the object in the view.
     */
    public Graphic getGraphic ();


    /**
     * Sets the Graphic of the object
     * 
     * @param myGraphic
     */
    public void setGraphic (Graphic myGraphic);

}
