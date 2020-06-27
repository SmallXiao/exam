package com.alvis.exam.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author wufeng
 * @since 2019-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Son implements Serializable {


    private String id;
    private String name;

    public static void main(String[] args) {
        String a = "s\"1dabda";
        System.out.println(a);
        if(a.contains("\"")){
            a = a.replace("\"","\\"+"\"");
            System.out.println(a);
        }

        /*String[] split = a.split("");
        System.out.println(split[1]);
        boolean numeric = StringUtils.isNumeric(split[1]);
        System.out.println(numeric);*/
        /*String s = StringUtils.substringBetween("[aasabcd]","[","]");
        String s1 = "as[ds[s";
        String[] split = s1.split("\\[");
        List<String> aaa = new ArrayList<>(Arrays.asList(split));
        System.out.println(aaa);
        System.out.println(aaa.toString());


        String s2 = new StringBuilder(s1).reverse().toString();
        System.out.println(s2);


        String s3 = s1.replaceAll("\\[", "");
        System.out.println(s3);*/
    }

}
