package com.backstage.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import org.apache.commons.lang3.StringUtils;

/**
 * 拼音工具类
 */
public class PinyinUtils {
    private static final HanyuPinyinOutputFormat OUTPUT_FORMAT = new HanyuPinyinOutputFormat();

    static {
        OUTPUT_FORMAT.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        OUTPUT_FORMAT.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }


    public static String getPinyinLetter(String input) {
        if (StringUtils.isBlank(input)) {
            return null;
        }
        //input = StringUtils.strip(input, " ");
        try {
            final char[] chars = input.toCharArray();
            final StringBuffer sb = new StringBuffer();
            for (char c : chars) {
                if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] str = PinyinHelper.toHanyuPinyinStringArray(c, OUTPUT_FORMAT);
                    if (str != null) {
                        sb.append(str[0]);
                    }
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 用逗号分隔拼音
     * 非中文字符不会出现在结果中
     *
     * @param input
     * @return
     */
    public static String getPinyinLetterWithSep(String input) {
        if (StringUtils.isBlank(input)) {
            return null;
        }
        try {
            final char[] chars = input.toCharArray();
            final StringBuffer sb = new StringBuffer();
            String result = "";
            for (char c : chars) {
                if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] str = PinyinHelper.toHanyuPinyinStringArray(c, OUTPUT_FORMAT);
                    if (str != null) {
                        sb.append(str[0]);
                        sb.append(",");
                    }
                } else {
//					sb.append(c);
                }

            }

            result = sb.toString();
            if (result.endsWith(",")) {
                result = result.substring(0, result.length() - 1);
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 取中文词首字母，如张三:ZS
     * 非中文字符不会出现在结果中
     *
     * @param input
     * @return
     */
    public static String getPinyinCapLetter(String input) {
        if (StringUtils.isBlank(input)) {
            return null;
        }
        try {
            final char[] chars = input.toCharArray();
            final StringBuffer sb = new StringBuffer();
            String result = "";
            for (char c : chars) {
                if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] str = PinyinHelper.toHanyuPinyinStringArray(c, OUTPUT_FORMAT);
                    if (str != null) {
                        sb.append(str[0].charAt(0));
                    }
                } else {
//					sb.append(c);
                }

            }

            result = sb.toString();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
//		System.out.println(PinyinUtils.getPinyinLetterWithSep("微位(fdsaf上海)fsdaf网fsdafsda络型技jdsaf有限公司"));
//		System.out.println(PinyinUtils.getPinyinLetterWithSep("扒"));
        System.out.println(PinyinUtils.getPinyinCapLetter("张三"));
    }
}
