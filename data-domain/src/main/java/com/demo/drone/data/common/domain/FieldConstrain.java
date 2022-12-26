package com.demo.drone.data.common.domain;

public abstract class FieldConstrain {

    public static final int FOLIO_LENGTH = 10;

    public static final int CODE = 16;

    public static final int CODEBIT = 32;

    public static final int UUID = 36;

    public static final int SMALL_TEXT_LENGTH = 32;

    public static final int SHORT_TEXT_LENGTH = 50;

    public static final int MEDIUM_TEXT_LENGTH = 150;

    public static final int BIG_TEXT_LENGTH = 1000;

    public static final String DATE_DATA_TYPE = "TIMESTAMP";
//    public static final String DATE_DATA_TYPE = "DATETIME";

    public static final String TEXT_DATA_TYPE = "TEXT";

    public static final String ENUM_DATA_TYPE = "TINYINT(1)";

    public static final String TINY_IMAGE_DATA_TYPE = "TINYBLOB"; // 256b

    public static final String NORMAL_IMAGE_DATA_TYPE = "BLOB"; // 65kb

    public static final String MEDIUM_IMAGE_DATA_TYPE = "MEDIUMBLOB"; // 16MB

    public static final String LONG_IMAGE_DATA_TYPE = "LONGBLOB"; // 4gb
    
    public static final String DATE_FORMAT = "dd/MM/yyyy h:mm a";

    public static final String BIT_BOOLEAN = "";
//    public static final String BIT_BOOLEAN = "BIT(1) NOT NULL DEFAULT 1";
}
