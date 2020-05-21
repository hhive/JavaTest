package test.baiduTranslate;

import java.io.IOException;

public class Main {

    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20191206000363508";
    private static final String SECURITY_KEY = "FkAwNR0j3CFjMLi0jwOi";

    public static void main(String[] args) throws IOException {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "Welcome to the October 2019 release of Visual Studio Code. As announced in the October iteration plan, we focused on housekeeping GitHub issues and pull requests as documented in our issue grooming guide. Across all of our VS Code repositories, we closed (either triaged or fixed) 4622 issues, which is even more than during our last housekeeping iteration in September 2018, where we closed 3918 issues. While we closed issues, you created 2195 new issues. This resulted in a net reduction of 2427 issues. The main vscode repository now has 2162 open feature requests and 725 open bugs. In addition, we closed 287 pull requests. As part of this effort, we have also tuned our process and updated the issue triaging workflow.";
        api.getTransResult(query, "auto", "zh");
    }

}
