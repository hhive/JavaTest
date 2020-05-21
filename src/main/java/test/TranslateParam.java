package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author lih@yunrong.cn
 * @version V2.1
 * @since 2.1.0 2020/3/5 16:36
 */
public class TranslateParam {

    public static void main(String[] args) {
        String path1 = "C:\\Users\\jax\\Desktop\\HeBaoCreditApplyReqDto.java";
        String path2 = "C:\\Users\\jax\\Desktop\\ApproveMetaDataConstant.java";
        try {
            new TranslateParam().translate(path1, path2);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    /**
     * 翻译Java文件中参数为指定格式
     */
    public void translate(String path1, String path2) throws IOException {
        /** 待筛选文件 */
        File waitFilterFile = new File(path1);
        /** 标准文件 */
        File standardFile = new File(path2);
        BufferedReader waitFilterBr = new BufferedReader(new InputStreamReader(new FileInputStream(waitFilterFile), StandardCharsets.UTF_8));
        BufferedReader standardBr = new BufferedReader(new InputStreamReader(new FileInputStream(standardFile), StandardCharsets.UTF_8));
        String line = null;

        /** 注释和参数 */
        List<String[]> paramList = new ArrayList<>();
        while ((line = waitFilterBr.readLine()) != null) {
            /** 参数注释、参数类型、参数名 */
            String[] params = new String[3];
            StringBuilder paramAnnotation = new StringBuilder();
            if (line.trim().startsWith("/**")) {
                paramAnnotation.append(line);
                while (!line.endsWith("*/")) {
                    line = waitFilterBr.readLine().trim();
                    paramAnnotation.append(line);
                }
                params[0] = paramAnnotation.toString();
                while (!(line = waitFilterBr.readLine()).trim().startsWith("private")) {
                    continue;
                }

                getWaitFilterParam(line, params);
                paramList.add(params);
            }
        }
        Set<String> standardSet = new HashSet<>();
        while ((line = standardBr.readLine()) != null) {
            if (line.trim().startsWith("/**")) { ;
                while (!line.endsWith("*/")) {
                    line = standardBr.readLine().trim();
                }
                while (!(line = standardBr.readLine()).trim().startsWith("public")) {
                    continue;
                }
                standardSet.add(getStandardParam(line));
            }
        }
        List<String[]> resultList = new ArrayList<>();
        dealDataToConstant(resultList, paramList, standardSet);
        // for (String[] results : resultList) {
        //     if (results.length >= 2) {
        //         System.out.println(results[0]);
        //         System.out.println(results[1]);
        //     }
        // }
        List<String[]> enumResultList = new ArrayList<>();
        getEnumResult(resultList, enumResultList);
        for (String[] results : enumResultList) {
            if (results.length >= 2) {
                System.out.println(results[0]);
                System.out.println(results[1]);
            }
        }
    }

    public void getWaitFilterParam(String param, String[] params) {
        String[] strings = param.trim().split(" ");
        /** 参数类型 */
        params[1] = strings[1];
        /** 参数名 */
        params[2] = strings[2];
    }

    public String getStandardParam(String line) {
        String[] params = line.trim().split(" ");
        try {
            String param = params[params.length - 1];
            return param.replace("\"", "");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void dealDataToConstant(List<String[]> resultList, List<String[]> paramList, Set<String> standardSet) {
        if (paramList != null && paramList.size() != 0) {
            for (String[] params : paramList) {
                if (!standardSet.contains(params[2])) {
                    /** 参数注释、 参数名 、大写的参数名、参数类型*/
                    String[] results = new String[4];
                    StringBuilder resultParam = new StringBuilder("public static final String ");
                    params[2] = params[2].replace(";", "");
                    char[] chars = params[2].toCharArray();
                    StringBuilder temp = new StringBuilder();
                    for (char c : chars) {
                        if (Character.isLowerCase(c)) {
                            temp.append(Character.toUpperCase(c));
                        }
                        if (Character.isUpperCase(c)) {
                            temp.append("_").append(c);
                        }
                    }
                    String upper = temp.toString();
                    resultParam.append(upper).append(" = ").append("\"").append(params[2]).append("\"").append(";");
                        /** 参数注释 */
                        results[0] = params[0];
                        /** 参数名 */
                        results[1] = resultParam.toString();
                        /** 大写的参数名 */
                        results[2] = upper;
                        /** 参数类型 */
                        results[3] = params[1];
                        resultList.add(results);
                }
            }
        }
    }
    private void getEnumResult(List<String[]> resultList, List<String[]> enumResultList) {
        String temp = " ZIP_(ApproveMetaDataConstant.ZIP_, \"AAA\", EnumExplainType.NO_EXPLAIN, null),";
        String enumTemp = "ZIP_(ApproveMetaDataConstant.ZIP_, \"AAA\", EnumExplainType.ENUM_EXPLAIN,\n"
            + "        (key, value) -> Enum.find(value) == null ? \"\" : Enum.find(value)\n"
            + "            .getDescription()),";
        for (String[] results : resultList) {
            /** 注释、枚举 */
            String[] enumResults = new String[2];
            if (results.length == 4) {
                String annotation = results[0].replace("/**", "").replace("*/", "").trim();

                if (results[3].startsWith("Enum")) {
                    enumResults[1] = enumTemp.replace("ZIP_", results[2]).replace("AAA", annotation).replace("Enum", results[3]);
                } else {
                    enumResults[1] = temp.replace("ZIP_", results[2]).replace("AAA", annotation);
                }
                enumResults[0] = results[0];
                enumResultList.add(enumResults);
            }
        }
    }
}
