package test.baiduTranslate;

import java.util.List;

import lombok.Data;

/**
 * @author lih@yunrong.cn
 * @version V2.1
 * @since 2.1.0 2019/12/6 11:50
 */
@Data
public class Result {

    /**
     * 来源语言
     */
    private String from;

    /**
     * 结果语言
     */
    private String to;

    /**
     * 结果
     */
    private List<TranslateResult> trans_result;

    private String error_code;
}
