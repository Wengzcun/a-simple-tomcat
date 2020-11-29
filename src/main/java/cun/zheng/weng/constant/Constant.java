package cun.zheng.weng.constant;

import java.io.File;

public final class Constant {

    public static final String WEB_ROOT = System.getProperty("user.dir")
            + File.separator + "target/classes/webroot";

    public static final String CLASS_PATH = System.getProperty("user.dir") + File.separator + "target/classes/";

    public static final String PACKAGE_NAME = "cun.zheng.weng.servlet";
}
