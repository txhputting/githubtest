package yaoshun.com.AndroidTest;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {
    private static final String TAG = "FileUtils";
    /**
     * 获得指定目录下的所有的图片
     * @return
     */
    public static final StringBuilder getAllPicture(File dir) {
        ArrayList<File> fileList =  getAllFile(dir);
        StringBuilder result = new StringBuilder("def");
        getFileFromDir(dir, fileList);
        ArrayList<File> imgList = new ArrayList<>();
        if (fileList == null || fileList.equals("")){
            return result;
        }
        for (File file : fileList) {
            if (file.getName().endsWith(".png") || file.getName().endsWith(".jpg")){
                imgList.add(file);
                result.append(file.getName()).append("/--");
            }
        }
        return result;
    }

    private static ArrayList<File> getAllFile(File dir) {
        File[] files = dir.listFiles();
        ArrayList<File> fileList = new ArrayList<>();
//        fileList.addAll(Arrays.asList(files));
        for (File file : files) {
            Log.d(TAG, "getFileFromDir: file = " + file.getName());
            fileList.add(file);
        }
        return fileList;
    }

    /**
     * 递归遍历文件夹的方法
     */
    public static final void getFileFromDir(File dir, ArrayList<File> fileList) {
        Log.d(TAG, "getFileFromDir: ");
        File[] files = dir.listFiles();
        Log.d(TAG, "getFileFromDir: ");
        if (files == null || files.equals("")){
            Log.d(TAG, "getFileFromDir: files == null");
            return;
        }
        Log.d(TAG, "getFileFromDir:files =  " + files);
        for (File file : files) {
            Log.d(TAG, "getFileFromDir: file name = " + file.getName());
            if (file.isDirectory()){
                Log.d(TAG, "getFileFromDir111111: file name = " + file.getName());
                getFileFromDir(file, fileList);
            }
            Log.d(TAG, "getFileFromDir: file = " + file.getName());
            fileList.add(file);
        }
    }

    /**
     * 获得根目录下的所有图片
     */
    public static String getAllPicture() {
        return getAllPicture(Environment.getExternalStorageDirectory()).toString();
    }

}
