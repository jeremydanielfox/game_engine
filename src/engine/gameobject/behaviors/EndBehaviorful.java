package engine.gameobject.behaviors;

public interface EndBehaviorful {
    
    /**
     * Add an on death behavior
     * @param behavior
     */
    public void addOnDeathBehavior(Behavior behavior);
    
    /**
     * Add an end of path behavior
     * @param behavior
     */
    public void addEndOfPathBehavior(Behavior behavior);
    
    /**
     * Clear all death behaviors, including explosion
     */
    public void clearDeathBehavior();
    
    /**
     * Clear all end of path behaviors.
     */
    public void clearEndOfPathBehavior();
}
