package yaoshun.com.AndroidTest;

public class ndkTools {

    static {
        System.loadLibrary("ndkdemotest-jni");
    }

    public static native String getStringFromNDK();
}
