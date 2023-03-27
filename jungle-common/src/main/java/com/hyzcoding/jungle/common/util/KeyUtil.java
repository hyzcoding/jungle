package com.hyzcoding.jungle.common.util;

import java.util.Random;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @date 2019/3/26
 * @since 1.0.0
 */
public class KeyUtil {

    public static String getUniqueKey(int n){
        String val = "";
        Random random = new Random();
        for ( int i = 0; i < n; i++ )
        {
            String str = random.nextInt( 2 ) % 2 == 0 ? "num" : "char";
            if ( "char".equalsIgnoreCase( str ) )
            { // 产生字母
                int nextInt = random.nextInt( 2 ) % 2 == 0 ? 65 : 97;
                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
                val += (char) ( nextInt + random.nextInt( 26 ) );
            }
            else if ( "num".equalsIgnoreCase( str ) )
            { // 产生数字
                val += String.valueOf( random.nextInt( 10 ) );
            }
        }
        return val;
    }
}
