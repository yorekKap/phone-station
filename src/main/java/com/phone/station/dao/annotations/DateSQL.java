package com.phone.station.dao.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.phone.station.dao.annotations.enums.DateType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DateSQL {
	DateType value() default DateType.DATE_TIME;
}
