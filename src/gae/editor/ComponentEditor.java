package gae.editor;

import gae.openingView.UIObject;

/**
 * Interface for all single component editors. To be implemented by TextEditor, SliderEditor, etc.
 * Subclasses would be used to represent an editor for a single property of an object.
 * 
 * @author Brandon Choi
 *
 */

public interface ComponentEditor extends UIObject {
    
    /**
     * clears field 
     */
    void clear ();
    
    /**
     * sets field to default
     */
    void defaultField();
}
