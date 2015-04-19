package engine.fieldsetting;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * This annotation marks methods that can be trigger by a button via the designer's request.
 * 
 * @author Sierra Smith
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Triggerable {

}
