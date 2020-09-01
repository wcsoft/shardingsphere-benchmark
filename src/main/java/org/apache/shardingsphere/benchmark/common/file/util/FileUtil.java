package org.apache.shardingsphere.benchmark.common.file.util;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtil {
    
    public static void createDirs(String dirsPath) {
        File dirs = new File(dirsPath);
        dirs.mkdirs();
    }
    
    public static void copyFile(String sourcePath, String destPath) {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(sourcePath).getChannel();
            outputChannel = new FileOutputStream(destPath).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                inputChannel.close();
                outputChannel.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            
        }
    }
    
    public static boolean isExisted(String filePath) {
        File file = new File(filePath);
        if (file.exists()){
            return true;
        }
        return false;
    }
    
    public static void test() {
        
    }
    
    
    public static void deleteFileByRecursion(File file) {
        File[] fs = file.listFiles();
        for (int i = 0; i < fs.length; i++) {
            File f = fs[i];
            if (f.isFile()) {
                f.delete();
            } else if (f.isDirectory()) {
                deleteFileByRecursion(f);
                f.delete();
            }
        }
        file.delete();
    }
    
    public static void deleteFile(File file) {
        File[] fileList = file.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            File f = fileList[i];
            f.delete();
        }
    }
    
    public static void decompressFile(String sourceFilePath, String destFilePath){
        
        TarArchiveEntry entry;
        File sourceFile = new File(sourceFilePath);
        try {
            TarArchiveInputStream fin = new TarArchiveInputStream(new GzipCompressorInputStream(new FileInputStream(sourceFile)));
            File extraceFolder = new File(destFilePath);
            while ((entry = fin.getNextTarEntry()) != null) {
                if (entry.isDirectory()) {
                    continue;
                }
                File curfile = new File(extraceFolder, entry.getName());
                File parent = curfile.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                IOUtils.copy(fin, new FileOutputStream(curfile));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    
    public static void deleteFileBySuffix(String filePath, String suffix){
        File file = null;
        File director = new File(filePath);
        File[] filelist = file.listFiles();
        for (int i = 0; i < filelist.length; i++) {
            file = filelist[i];
            if (file.getName().endsWith("apk") && !file.getName().endsWith(suffix))
            {
                file.delete();
            }
        }
    }
}
