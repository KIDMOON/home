package com.example.home.util;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.WeightRandom;
import cn.hutool.core.lang.WeightRandom.WeightObj;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机工具类
 *
 * @author xiaoleilu
 */
public class RandomUtil {

    /**
     * 用于随机选的数字
     */
    public static final String BASE_NUMBER = "0123456789";
    /**
     * 用于随机选的字符
     */
    public static final String BASE_CHAR = "abcdefghijklmnopqrstuvwxyz";
    /**
     * 用于随机选的字符和数字
     */
    public static final String BASE_CHAR_NUMBER = BASE_CHAR + BASE_NUMBER;

    /**
     * 获取随机数生成器对象<br>
     * ThreadLocalRandom是JDK 7之后提供并发产生随机数，能够解决多个线程发生的竞争争夺。
     *
     * @return {@link ThreadLocalRandom}
     * @since 3.1.2
     */
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    /**
     * 获取{@link SecureRandom}，类提供加密的强随机数生成器 (RNG)
     *
     * @return {@link SecureRandom}
     * @since 3.1.2
     */
    public static SecureRandom getSecureRandom() {
        try {
            return SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            throw new UtilException(e);
        }
    }


    /**
     * 获得指定范围内的随机数 [0,limit)
     *
     * @param limit 限制随机数的范围，不包括这个数
     * @return 随机数
     */
    public static int randomInt(int limit) {
        return getRandom().nextInt(limit);
    }


    /**
     * 随机数字，数字为0~9单个数字
     *
     * @return 随机数字字符
     * @since 3.1.2
     */
    public static char randomNumber() {
        return randomChar(BASE_NUMBER);
    }


    /**
     * 随机字符
     *
     * @param baseString 随机字符选取的样本
     * @return 随机字符
     * @since 3.1.2
     */
    public static char randomChar(String baseString) {
        return baseString.charAt(getRandom().nextInt(baseString.length()));
    }


    /**
     * 带有权重的随机生成器
     *
     * @param weightObjs 带有权重的对象列表
     * @return {@link WeightRandom}
     * @since 4.0.3
     */
    public static <T> WeightRandom<T> weightRandom(WeightObj<T>[] weightObjs) {
        return new WeightRandom<>(weightObjs);
    }


    public static void main(String[] args) {
        File file = new File("/Users/kid/Downloads/pass.txt");


//        int[] listX =  {4, 9, 4, 1, 4, 9, 4};

//        int[] listXX = {9, 9, 2, 3, 3, 0, 5};

//        int[] listXXX= {1, 7, 0, 4, 8, 3, 8};

//        int[] list =   {7, 2, 9, 8, 3, 9, 7}
//        int[] list =   {0, 9, 6, 0, 1, 2, 4};


//        int[] list = {8, 4, 5, 4, 5, 3, 7};

//        int[] list = {7, 8, 5, 0, 9, 9, 1};


        for (int i = 0; i < 30; i++) {
            StringBuffer s = new StringBuffer();

            int ox = 0;

            while (ox < 7) {
                int number = RandomUtil.randomInt(10);
//
                s.append(number);
                ox++;
//                    }

            }
            s.append("\n");
            FileUtil.appendUtf8String(s.toString(), file);

        }


    }
}
