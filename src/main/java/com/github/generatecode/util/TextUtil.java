package com.github.generatecode.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件解析和生成
 *
 * @author luofeng
 */
public class TextUtil {

    /**
     * 写文件
     */
    public static void write(String path, String text) {
        //打开文件
        File wFile = new File(path);
        //创建FileWriter
        //使用BufferedWriter加速
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(wFile));
            //写入
            bufferedWriter.write(text);
            //刷新缓冲区
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭BufferedReader
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 将文件读取到内存里 - 行处理
     *
     * @param path
     */
    public static List<String> reader(String path) {
        List<String> list = new ArrayList<>();
        //打开文件
        //创建FileReader
        //使用BufferedReader加速
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(path)));
            //逐行读取文本
            String lineString = null;
            while ((lineString = bufferedReader.readLine()) != null) {
                list.add(lineString);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭BudderedReader
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }
    }
    /**
     * 将文件读取到内存里 - 行处理
     *
     * @param path
     */
    public static String readerStr(String path) {
        StringBuilder list = new StringBuilder();
        //打开文件
        //创建FileReader
        //使用BufferedReader加速
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(path)));
            //逐行读取文本
            String lineString = null;
            while ((lineString = bufferedReader.readLine()) != null) {
                list.append(StringUtils.concat(lineString,"  \n"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭BudderedReader
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list.toString();
        }
    }


    /**
     * 获取路径下的所有文件/文件夹
     * @param directoryPath 需要遍历的文件夹路径
     * @param isAddDirectory 是否将子文件夹的路径也添加到list集合中
     * @return
     */
    public static List<String> getAllFile(String directoryPath,boolean isAddDirectory) {
        List<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if(isAddDirectory){
                    list.add(file.getAbsolutePath());
                }
                list.addAll(getAllFile(file.getAbsolutePath(),isAddDirectory));
            } else {
                list.add(file.getAbsolutePath());
            }
        }
        return list;
    }
}
