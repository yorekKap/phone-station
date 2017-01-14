package com.phone.station.dao.annotations;

import com.phone.station.dao.annotations.enums.DateType;

public @interface DateSQL {
	DateType value() default DateType.DATE_TIME;
}
