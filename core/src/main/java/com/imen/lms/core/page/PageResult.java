package com.imen.lms.core.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @autor LIGANG
 * @data 2018/10/13 13:20
 * @description 分页查询
 */
@Getter
@Setter
@ToString
public class PageResult {
    private Integer currentPage;//当前页
    private Integer pageSize;//每页条数
    private Integer totalCount;//总条数
    private List listResult;//结果集
    private int totalPage;//总页数
    private int firstPage;//第一页
    private int prevPage;//上一页
    private int nextPage;//下一页
    private int lastPage;//最后一页

    public PageResult(int totalCount, List listResult,int currentPage,int pageSize) {
        this.totalCount = totalCount;
        this.listResult = listResult;
        this.currentPage=currentPage;
        this.pageSize=pageSize;
        this.init();
    }

    private void init(){
        this.firstPage=1;
        this.totalPage=this.totalCount%this.pageSize==0?this.totalCount/this.pageSize:this.totalCount/this.pageSize+1;
        this.prevPage=this.currentPage<2?1:this.currentPage-1;
        this.nextPage=this.currentPage+1>this.totalPage?this.totalPage:this.currentPage+1;
        this.lastPage=this.totalPage;
    }
}
