package io.priyak.app.core.domain;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * Annotation to bind the number of @{@link io.priyak.app.core.domain.spot.Spot}s in @{@link ParkingLot}
 *
 * @author priyakdey
 */
@BindingAnnotation
@Target({PARAMETER, METHOD})
@Retention(RUNTIME)
public @interface Count {
}
