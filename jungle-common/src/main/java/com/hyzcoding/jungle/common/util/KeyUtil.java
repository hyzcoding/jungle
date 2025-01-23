package com.hyzcoding.jungle.common.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 *  2019/3/26
 * @since 1.0.0
 */
public class KeyUtil {

    public static String getUniqueKey(int n){
        StringBuilder val = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for ( int i = 0; i < n; i++ )
        {
            String str = random.nextInt( 2 ) % 2 == 0 ? "num" : "char";
            if ( "char".equalsIgnoreCase( str ) )
            { // 产生字母
                int nextInt = random.nextInt( 2 ) % 2 == 0 ? 65 : 97;
                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
                val.append((char) (nextInt + random.nextInt(26)));
            }
            else { // 产生数字
                val.append(random.nextInt(10));
            }
        }
        return val.toString();
    }
}
