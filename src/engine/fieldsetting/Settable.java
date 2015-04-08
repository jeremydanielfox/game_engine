package engine.fieldsetting;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/**
 * 
 * @author Jeremy
 * @author Eric
 * 
 * Any method defined by this annotation must comply to the following conditions:
 * The method's name must start with "set" and then must follow with the property name which is being set.
 * The method must only take one parameter.
 *
 */
//@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Settable {

}
