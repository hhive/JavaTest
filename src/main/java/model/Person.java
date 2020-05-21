package model;
/**
 * @author lih@yunrong.cn
 * @version V2.1
 * @since 2.1.0 2019/7/31 15:21
 */

public class Person {
    private int pageNum;
    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
