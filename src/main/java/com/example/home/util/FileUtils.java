/*
 * Copyright 2006-2020 FacilityONE Inc. All Rights Reserved
 *
 * 注意：
 * 本软件内容仅限于费哲软件内部传阅，禁止外泄以及用于其他商业目的
 * 费哲软件(FacilityONE) : www.facilityone.cn
 */

package com.example.home.util;

import com.example.home.constant.SystemConstant;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description goes here.
 *
 * @author kid.bian
 * @date 2020/10/30 12:25 下午
 * @since 1.0
 **/
public class FileUtils {

    private static final String FILE_PATH_DATE_PATTERN = "yyyyMMdd";
    private static final String RELATIVE_PATH_DATE_PATTERN = "yyyy/MM/dd/";
    /**
     * 文件后缀，后期可以删除zip和导出的excel
     */
    public static final String FONEFILENAME = "fonefilegen";

    private static int MAX_MAP_SIZE = 2000;
    private static Map<String, String> datePathMap = new ConcurrentHashMap<>(MAX_MAP_SIZE);
    private static Map<String, String> hashNameMap = new ConcurrentHashMap<>(MAX_MAP_SIZE);

    /**
     * 获取文件的静态映射路径
     *
     * @param staticPath
     * @param hashName
     * @param date
     * @return
     */
    public static String getStaticPath(String staticPath, String hashName, Date date) {
        if (hashNameMap.containsKey(hashName)) {
            return hashNameMap.get(hashName);
        }

        SimpleDateFormat sdf = new SimpleDateFormat(RELATIVE_PATH_DATE_PATTERN);
        staticPath = staticPath.endsWith(SystemConstant.SLASH) ? staticPath : staticPath + SystemConstant.SLASH;

        StringBuilder sb = new StringBuilder();
        sb.append(staticPath);
        sb.append(sdf.format(date));
        sb.append(hashName);

        if (hashNameMap.size() > MAX_MAP_SIZE) {
            hashNameMap.clear();
        }
        hashNameMap.put(hashName, sb.toString());

        return sb.toString();
    }

    /**
     * 获取文件在disk上的绝对路径
     *
     * @param basePath
     * @param date
     * @return
     */
    public static String getAbsolutePath(String basePath, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(FILE_PATH_DATE_PATTERN);
        String ds = sdf.format(date);

        String key = ds + basePath;
        if (datePathMap.containsKey(key)) {
            return datePathMap.get(key);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(basePath);

            if (!basePath.endsWith(File.separator) && !basePath.endsWith(SystemConstant.SLASH)) {
                sb.append(File.separator);
            }

            sb.append(ds, 0, 4);
            sb.append(File.separator);
            sb.append(ds, 4, 6);
            sb.append(File.separator);
            sb.append(ds, 6, 8);
            sb.append(File.separator);

            if (datePathMap.size() > MAX_MAP_SIZE) {
                datePathMap.clear();
            }
            datePathMap.put(key, sb.toString());

            return sb.toString();
        }
    }

    /**
     * 创建目录，支持多级
     *
     * @param path
     * @return
     */
    public static boolean mkdirs(String path) {
        boolean bFlag = false;
        try {
            File file = new File(path);
            if (!file.exists()) {
                bFlag = file.mkdirs();
            } else {
                bFlag = true;
            }
        } catch (Exception e) {
        }
        return bFlag;
    }

    /**
     * 获取文件前缀（即不带后缀的文件名）
     *
     * @param fileName
     * @return
     */
    public static String getFilePrefix(String fileName) {
        if (fileName == null) {
            return null;
        }

        int index = fileName.lastIndexOf(".");
        if (index <= 0) {
            return fileName;
        }

        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    /**
     * 获取文件后缀类型
     *
     * @param fileName
     * @param withDot
     * @return
     */
    public static String getFileSuffix(String fileName, boolean withDot) {
        if (fileName == null) {
            return null;
        }

        int index = fileName.lastIndexOf(".");
        if (index < 0) {
            return "";
        }

        if (withDot) {
            return fileName.substring(index, fileName.length());
        } else {
            return fileName.substring(index + 1, fileName.length());
        }
    }


}
