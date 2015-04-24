package gae.editor;

/**
 *
 * @author Eric Saba
 *
 *         Classes that create and edit game engine objects must implement this interface.
 */
public interface Edits {
    Object createObject (Class<?> c);
}
