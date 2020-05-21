package test.baiduTranslate;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import test.FileChoose.FanyiV3Demo;
import util.CollectionUtil;
import util.GsonUtil;
import util.StringUtil;

public class TransApi {
    private static final String TRANS_API_HOST = "https://fanyi-api.baidu.com/api/trans/vip/translate";
    private static Logger log = Logger.getLogger(TransApi.class);
    private String appid;
    private String securityKey;

    public TransApi(String appid, String securityKey) {
        this.appid = appid;
        this.securityKey = securityKey;
    }

    public void getTransResult(String query, String from, String to) throws IOException {
        Map<String, String> params = buildParams(query, from, to);
        /** 创建HttpClient */
        CloseableHttpClient httpClient = HttpClients.createDefault();

        /** httpPost */
        HttpPost httpPost = new HttpPost(TRANS_API_HOST);
        List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
        Iterator<Map.Entry<String, String>> it = params.entrySet()
            .iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> en = it.next();
            String key = en.getKey();
            String value = en.getValue();
            paramsList.add(new BasicNameValuePair(key, value));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(paramsList, "UTF-8"));
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try {
            Header[] contentType = httpResponse.getHeaders("Content-Type");
            log.info("Content-Type:" + contentType[0].getValue());
            /** 显示结果 */
            HttpEntity httpEntity = httpResponse.getEntity();
            String json = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
            EntityUtils.consume(httpEntity);
            Result result = GsonUtil.json2Obj(json, Result.class);
            log.info(result);
            String source = null;
            String content = null;
            if (result != null) {
                List<TranslateResult> translateResultList = result.getTrans_result();
                if (CollectionUtil.isNotEmpty(translateResultList)) {
                    TranslateResult translateResult = translateResultList.get(0);
                    source = translateResult.getSrc();
                    content = translateResult.getDst();
                }
            }
            if (StringUtil.isNotBlank(source) && StringUtil.isNotBlank(content)) {

                log.info(content);
                BufferedWriter out = null;
                BufferedWriter out1 = null;
                try {
                    // /** 追加生成结果文件 */
                    // out = new BufferedWriter(
                    //     new OutputStreamWriter(new FileOutputStream(new File(result), true), StandardCharsets.UTF_8));
                    // out.write("\n");
                    // out.write("    ");
                    // out.write(content);
                    //
                    // /** 追加生成对比文件*/
                    // out1 = new BufferedWriter(
                    //     new OutputStreamWriter(new FileOutputStream(new File(compare), true), StandardCharsets.UTF_8));
                    // out1.write("\n");
                    // out1.write("    ");
                    // out1.write(source);
                    // out1.write("\n");
                    // out1.write("    " + "翻译：");
                    // out1.write(content);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        out.close();
                        out1.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                log.error("## release resouce error ##" + e);
            }
        }
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }

}
