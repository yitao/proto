package com.proto.commons.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by and on 2016/7/24.
 */
public class URLUtils {
    private static Logger log = LoggerFactory.getLogger(URLUtils.class);
    private static final Pattern ipPattern = Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
    private static final String domainPatternString1 = "aero|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel";
    private static final String domainPatternString2 = "ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cu|cv|cx|cy|cz|de|dj|dk|dm|do|dz|ec|ee|eg|er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|mk|ml|mm|mn|mo|mp|mq|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ro|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|st|su|sv|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|yu|za|zm|zw";
    private static final String domainPatternString = "[\\w-]+(\\.(aero|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel)(\\.(ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cu|cv|cx|cy|cz|de|dj|dk|dm|do|dz|ec|ee|eg|er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|mk|ml|mm|mn|mo|mp|mq|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ro|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|st|su|sv|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|yu|za|zm|zw))|\\.(aero|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cu|cv|cx|cy|cz|de|dj|dk|dm|do|dz|ec|ee|eg|er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|mk|ml|mm|mn|mo|mp|mq|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ro|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|st|su|sv|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|yu|za|zm|zw))$";
    private static final Pattern domainPattern = Pattern.compile("[\\w-]+(\\.(aero|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel)(\\.(ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cu|cv|cx|cy|cz|de|dj|dk|dm|do|dz|ec|ee|eg|er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|mk|ml|mm|mn|mo|mp|mq|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ro|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|st|su|sv|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|yu|za|zm|zw))|\\.(aero|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|ax|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cd|cf|cg|ch|ci|ck|cl|cm|cn|co|cr|cu|cv|cx|cy|cz|de|dj|dk|dm|do|dz|ec|ee|eg|er|es|et|eu|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gg|gh|gi|gl|gm|gn|gp|gq|gr|gs|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|im|in|io|iq|ir|is|it|je|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|mk|ml|mm|mn|mo|mp|mq|mr|ms|mt|mu|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|ps|pt|pw|py|qa|re|ro|rs|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|st|su|sv|sy|sz|tc|td|tf|tg|th|tj|tk|tl|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|uz|va|vc|ve|vg|vi|vn|vu|wf|ws|ye|yt|yu|za|zm|zw))$", 2);
    private static final String HTTP_PROTOCAL = "http://";
    private static final String HTTPS_PROTOCAL = "https://";
    private static final String FTP_PROTOCAL = "ftp://";
    private static final String PREFIX = "\\u";

    public URLUtils() {
    }

    public static final boolean isIpAddress(String str) {
        return ipPattern.matcher(str).matches();
    }

    public static final boolean hasDomainName(String str) {
        Matcher matcher = domainPattern.matcher(str);
        return matcher.find();
    }

    public static final String getDomainName(String url) {
        if(null != url && !"".equals(url)) {
            String website = getWebsite(url);
            if(isIpAddress(website)) {
                return website;
            } else {
                Matcher matcher = domainPattern.matcher(website);
                return matcher.find()?matcher.group():url;
            }
        } else {
            return null;
        }
    }

    public static final String getWebsite(String str) {
        String str1 = str.toLowerCase();
        boolean beginIndex = false;
        int beginIndex1;
        if(!str1.startsWith("http://") && !str1.startsWith("https://") && !str1.startsWith("ftp://")) {
            beginIndex1 = str1.indexOf("://");
        } else {
            beginIndex1 = str1.startsWith("http://")?"http://".length():(str1.startsWith("https://")?"https://".length():"ftp://".length());
        }

        if(beginIndex1 < 0) {
            beginIndex1 = 0;
        }

        int endIndex = str1.indexOf("/", beginIndex1);
        if(endIndex <= 0) {
            endIndex = str1.length();
        }

        String website = str1.substring(beginIndex1, endIndex);
        website = website.split("@")[0];
        endIndex = website.indexOf(":");
        return endIndex > 0?website.substring(0, endIndex):website;
    }

    public static final String decode(String urlString) {
        String decodeCharset = "GBK";

        try {
            if(isUtf8Url(urlString)) {
                decodeCharset = "UTF-8";
            }

            if(isUtf16Url(urlString)) {
                String e = "http://www.hahabei.com/SearchPlayFile.aspx?%u6D4B%u8BD5";
                e = e.replaceAll("%u", "\\\\u");
                return ascii2native(e);
            } else {
                return URLDecoder.decode(urlString, decodeCharset);
            }
        } catch (Exception var3) {
            log.error("decodeCharset:" + decodeCharset + " urlString:" + urlString, var3);
            return urlString;
        }
    }

    public static final String encode(String text) {
        StringBuffer result = new StringBuffer();

        for(int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            if(c >= 0 && c <= 255) {
                result.append(c);
            } else {
                byte[] b = new byte[0];

                try {
                    b = Character.toString(c).getBytes("UTF-8");
                } catch (Exception var7) {
                    ;
                }

                for(int j = 0; j < b.length; ++j) {
                    int k = b[j];
                    if(k < 0) {
                        k += 256;
                    }

                    result.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }

        return result.toString();
    }

    private static final boolean isUtf8Charset(String text) {
        String sign = "";
        if(text.startsWith("%e")) {
            for(int p = 0; p != -1; sign = sign + p) {
                p = text.indexOf("%", p);
                if(p != -1) {
                    ++p;
                }
            }
        }

        return sign.equals("147-1");
    }

    public static final boolean isUtf8Url(String text) {
        text = text.toLowerCase();
        int p = text.indexOf("%");
        if(p != -1 && text.length() - p >= 9) {
            text = text.substring(p, p + 9);
        }

        return isUtf8Charset(text);
    }

    public static final boolean isUtf16Url(String text) {
        text = text.toLowerCase();
        int p = text.indexOf("%u");
        return p != -1 && text.length() - p >= 6;
    }

    public static final void native2ascii(String dir, String srcFile, String desFile, String encoding) {
        File folder = new File(dir);
        if(!folder.isDirectory()) {
            System.out.println("dir: " + dir + " is not valid directory");
        } else {
            BufferedReader reader = null;
            BufferedWriter writer = null;

            try {
                if(encoding != null) {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(dir + "/" + srcFile), encoding));
                } else {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(dir + "/" + srcFile)));
                }

                writer = new BufferedWriter(new FileWriter(dir + "/" + desFile));

                String e;
                while((e = reader.readLine()) != null) {
                    writer.write(native2ascii(e));
                    writer.newLine();
                }

                System.out.println("native2ascii successful [" + dir + "/" + desFile + "]");
            } catch (IOException var20) {
                System.out.println("native2ascii failed: " + var20);
            } finally {
                if(reader != null) {
                    try {
                        reader.close();
                    } catch (IOException var19) {
                        ;
                    }
                }

                if(writer != null) {
                    try {
                        writer.close();
                    } catch (IOException var18) {
                        ;
                    }
                }

            }

        }
    }

    public static final String native2ascii(String str) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < chars.length; ++i) {
            sb.append(char2ascii(chars[i]));
        }

        return sb.toString();
    }

    private static final String char2ascii(char c) {
        if(c > 255) {
            StringBuilder sb = new StringBuilder();
            sb.append("\\u");
            int code = c >> 8;
            String tmp = Integer.toHexString(code).toUpperCase();
            if(tmp.length() == 1) {
                sb.append("0");
            }

            sb.append(tmp);
            code = c & 255;
            tmp = Integer.toHexString(code).toUpperCase();
            if(tmp.length() == 1) {
                sb.append("0");
            }

            sb.append(tmp);
            return sb.toString();
        } else {
            return Character.toString(c);
        }
    }

    public static final String ascii2native(String str) {
        StringBuilder sb = new StringBuilder();
        int begin = 0;

        for(int index = str.indexOf("\\u"); index != -1; index = str.indexOf("\\u", begin)) {
            sb.append(str.substring(begin, index));
            sb.append(ascii2char(str.substring(index, index + 6)));
            begin = index + 6;
        }

        sb.append(str.substring(begin));
        return sb.toString();
    }

    private static final char ascii2char(String str) {
        if(str.length() != 6) {
            throw new IllegalArgumentException("Ascii string of a native character must be 6 character.");
        } else if(!"\\u".equals(str.substring(0, 2))) {
            throw new IllegalArgumentException("Ascii string of a native character must start with \"\\u\".");
        } else {
            String tmp = str.substring(2, 4);
            int code = Integer.parseInt(tmp, 16) << 8;
            tmp = str.substring(4, 6);
            code += Integer.parseInt(tmp, 16);
            return (char)code;
        }
    }

    public static String appendParam(String url, String keyValPair) {
        if(!StringUtils.isEmpty(url) && !StringUtils.isEmpty(keyValPair)) {
            String fUrl = url.replace('?', '&');
            int pos = fUrl.indexOf(38);
            if(pos < 0) {
                pos = fUrl.indexOf(35);
                return pos > -1?fUrl.substring(0, pos) + "?" + keyValPair + fUrl.substring(pos):fUrl + "?" + keyValPair;
            } else {
                return fUrl.substring(0, pos) + "?" + keyValPair + fUrl.substring(pos);
            }
        } else {
            return url;
        }
    }
}

