package engine.fieldsetting;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * This annotation is used to mark a Settable method that should take in a number that is
 * an element of a certain interval. For instance, if you have a setRadius method, and you don't
 * want the
 * radius to be negative, you could set the lowerBound to zero. The game authoring environment will
 * then be able
 * to grab the interval at runtime and restrict the user to setting a value in that interval.
 * 
 * @author Jeremy
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Interval {
    public int lowerBound = 0;
    public int upperBound = 1;
}
