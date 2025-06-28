package com.gg.administrative_system_backend.util;

public class RegexPatterns {
    public static final String DECIMAL = "[\\d,]+(\\.\\d+)?";
    public static final String LONG = "\\d+";
    public static final String DOUBLE_QUOTED_VALUE = ".*\"(.*)\".*";
    public static final String QUOTED_VALUE = ".*'(.*)'.*'(.*)'.*";
    public static final String DATE_FORMAT = "[\\d]{2}/[\\d]{2}/[\\d]{4}";
    public static final String DATE = "dd/MM/yyyy";
}
