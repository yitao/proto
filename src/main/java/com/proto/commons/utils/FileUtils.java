package com.proto.commons.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by and on 2016/6/27.
 */

public class FileUtils extends org.apache.commons.io.FileUtils {
    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);
    protected static byte[] buf = new byte[1024];

    public FileUtils() {
    }

    public static final boolean isValid(String filename) {
        String regex = "(.*[\\\\|/]{1})*(.+\\.+.+)$";
        Pattern pattern = Pattern.compile(regex, 2);
        Matcher match = pattern.matcher(filename);
        if(match.find() && match.groupCount() == 2) {
            String name = match.group(2);
            String illegalRegex = "\\\\+|/+|:+|\\*+|\\?+|\"+|<+|>+";
            pattern = Pattern.compile(illegalRegex, 2);
            match = pattern.matcher(name);
            return !match.find();
        } else {
            return false;
        }
    }

    public static final String replaceInvalidFileChars(String fileName) {
        StringBuffer out = new StringBuffer();

        for(int i = 0; i < fileName.length(); ++i) {
            char ch = fileName.charAt(i);
            switch(ch) {
                case '\"':
                case '*':
                case '/':
                case ':':
                case '<':
                case '>':
                case '?':
                case '\\':
                case '|':
                    out.append('_');
                    break;
                default:
                    out.append(ch);
            }
        }

        return out.toString();
    }

    public static final String filePathToURL(String fileName) {
        String fileUrl = (new File(fileName)).toURI().toString();
        return fileUrl;
    }

    public static final void writeToFile(String filename, InputStream in) throws IOException {
        writeToFile(new File(filename), in);
    }

    public static final void writeToFile(File file, InputStream in) throws IOException {
        FileOutputStream out = null;

        try {
            out = org.apache.commons.io.FileUtils.openOutputStream(file);
            IOUtils.copy(in, out);
        } finally {
            IOUtils.closeQuietly(out);
        }

    }

    public static final String getFilenameExtension(String filename) {
        return filename.indexOf(".") < 0?null:filename.substring(filename.lastIndexOf(".") + 1, filename.length()).toLowerCase();
    }

    private static final void deleteFolder(File oPath) {
        File[] dirs = oPath.listFiles();
        if(dirs != null) {
            File[] arr$ = dirs;
            int len$ = dirs.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                File oSubPath = arr$[i$];
                if(oSubPath.isDirectory()) {
                    deleteFolder(oSubPath);
                }
            }
        }

        oPath.delete();
    }

    public static final void deleteFolder(String sPath) {
        File oPath = new File(sPath);
        if(oPath.exists() && oPath.isDirectory()) {
            deleteFolder(oPath);
        }
    }

    public static final void deleteFile(String filename) {
        File file = new File(filename);
        if(file.exists() && file.isFile()) {
            file.delete();
        }

    }

    public static final boolean createFolder(String sPath) {
        try {
            File e = new File(sPath);
            if(!e.exists()) {
                e.mkdirs();
            }

            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    public static final void copyFile(String sourceFile, String targetFile) throws IOException {
        File oFile1 = new File(sourceFile);
        if(oFile1.exists()) {
            String sPath = targetFile.substring(0, targetFile.lastIndexOf(92));
            createFolder(sPath);
            File oFile2 = new File(targetFile);
            RandomAccessFile inData = new RandomAccessFile(oFile1, "r");
            RandomAccessFile opData = new RandomAccessFile(oFile2, "rw");
            FileChannel inChannel = inData.getChannel();
            FileChannel opChannel = opData.getChannel();
            inChannel.transferTo(0L, inChannel.size(), opChannel);
            inChannel.close();
            inData.close();
            opChannel.close();
            opData.close();
        }

    }

    public static final String getRealPath(ServletContext servletContext, String path) {
        File file = new File(path);
        return file.isAbsolute()?path:servletContext.getRealPath(path);
    }

    public static final String getRelationPath(String basePath, String filename) {
        int beginIndex = filename.indexOf(basePath);
        int endIndex = filename.lastIndexOf(File.separator);
        return filename.substring(beginIndex, endIndex + 1);
    }

    public static final String getClassRootPath(Class<?> clazz) {
        String path = getDirFromClassLoader(clazz);
        if(path == null) {
            path = System.getProperty("user.dir");
        }

        return path;
    }

    private static final String getDirFromClassLoader(Class<?> clazz) {
        try {
            String e = clazz.getName().replace(".", "/");
            e = "/" + e + ".class";
            URL url = clazz.getResource(e);
            String classPath = url.getPath();
            if(classPath.startsWith("file:")) {
                if(classPath.length() > 5) {
                    classPath = classPath.substring(5);
                }

                classPath = classPath.split("!")[0];
            } else {
                classPath = clazz.getResource("/").toString().substring(5);
            }

            return classPath;
        } catch (Exception var4) {
            return null;
        }
    }

    public static final InputStream getInputStreamFromJar(String jarPath, String filePath) throws IOException {
        JarFile jarFile = new JarFile(jarPath);
        JarEntry jarEntry = jarFile.getJarEntry(filePath);
        InputStream in = jarFile.getInputStream(jarEntry);
        return in;
    }

    public static final void closeIO(Closeable... io) {
        Closeable[] arr$ = io;
        int len$ = io.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            Closeable aIO = arr$[i$];
            if(aIO != null) {
                try {
                    aIO.close();
                } catch (Exception var6) {
                    log.error("Close io failed!", var6);
                }
            }
        }

    }
}
