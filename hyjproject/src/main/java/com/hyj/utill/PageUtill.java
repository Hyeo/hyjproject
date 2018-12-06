package com.hyj.utill;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Data
@Slf4j
public class PageUtill {
	/**
     * 전체 건수
     */
    private long allCount;

    /**
     * 현재 페이지
     */
    private int currentPage;

    /**
     * 화면에 보여질 데이터 수
     */
    private int contentsCount;

    /**
     * 페이징 건수
     */
    private int viewPageCount;

    /**
     * 처음으로
     */
    private String firstPageNavi;

    /**
     * 이전페이지
     */
    private String currentFirstPageNavi;

    /**
     * 다음페이지
     */
    private String currentLastPageNavi;

    /**
     * 마지막으로
     */
    private String lastPageNavi;

    /**
     * 화면에 표시된 페이지 중 선택 X
     */
    private String pageNaviOff;

    /**
     * 화면에 표시된 페이지 중 선택 O
     */
    private String pageNaviOn;

    /**
     * 마지막 페이지
     */
    private int lastPage;

    private int defaultContentsCount = 5;

    private int defaultPageCount = 3;

    /**
     * 페이징 util
     *
     * @param totCount  전체 건수
     * @param currPage  현재 페이지
     * @return
     */
    public String makePageHtml(int totCount, int currPage) {
        return makePageHtml(totCount, currPage, defaultPageCount, defaultContentsCount);
    }

    /**
     * 페이징 util
     *
     * @param totCount  전체 건수
     * @param currPage  현재 페이지
     * @param contentsCount  리스트 갯수
     * @return
     */
    public String makePageHtml(int totCount, int currPage, int contentsCount) {
        return makePageHtml(totCount, currPage, defaultPageCount, contentsCount);
    }

    /**
     * 페이징 util
     *
     * @param totCount  전체 건수
     * @param currPage  현재 페이지
     * @param pageCount 페이징 갯수
     * @param contentsCount  리스트 갯수
     * @return
     */
    public String makePageHtml(int totCount, int currPage, int pageCount, int contentsCount) {
    	
    	//여기서 셋팅을 한다.
        setPageNaviParam();

        if (totCount == 0) {
            return "";
        }

        // 전체 건수
        this.allCount = totCount;

        // 현재 페이지
        this.currentPage = currPage;

        // 화면에 보여질 데이터 수
        this.contentsCount = contentsCount;

        // 화면에 보여질 페이지 최대 건수
        this.viewPageCount = pageCount;

        // 마지막 페이지
        this.lastPage = (int) Math.ceil((double) totCount / (double) contentsCount);

        return makePageHtmlComponent(currentPage, viewPageCount, lastPage);
    }

    /**
     * Mobile 페이징 util
     *
     * @param totCount
     * @param currPage
     * @param contentsCount
     * @return
     */
    public String makeMobilePageHtml(int totCount, int currPage, int contentsCount) {
        setMobilePageNaviParam();

        if (totCount == 0) {
            return "";
        }

        // 전체 건수
        this.allCount = totCount;

        // 현재 페이지
        this.currentPage = currPage;

        // 화면에 보여질 데이터 수
        this.contentsCount = contentsCount;

        // 마지막 페이지
        this.lastPage = (int) Math.ceil((double) allCount / (double) contentsCount);

        return makeMobilePageHtmlComponent(currentPage, lastPage);
    }

    /**
     * 페이징 HTML make
     *
     * @param currentPage
     * @param viewPageCount
     * @param lastPage
     * @return
     */
    private String makePageHtmlComponent(int currentPage, int viewPageCount, int lastPage) {
        String _html = "";

        // 처음으로 
        // viewPageCount 갯수를 넘어가야 처음으로 이동기능이 생기게 만듬
        // 만약 첫페이지를 제외하고 만들고 싶다면 viewPageCount -> 1 처음부터 하고 싶다면 if문을 제거하면 된다.
        if (currentPage - viewPageCount > 0)
            _html += String.format(getFirstPageNavi(), clickScript(1));

        // 이전페이지
        int prevLast = currentPage - 1;
        if (currentPage - 1 > 0)
            _html += String.format(getCurrentFirstPageNavi(), clickScript(prevLast));

        // PageList
        for (int i = ((currentPage - 1) / viewPageCount) * viewPageCount + 1; i < ((currentPage - 1) / viewPageCount) * viewPageCount + 1 + viewPageCount && i <= lastPage; i++) {

            if (i > lastPage) break;

            if (i == currentPage) {
                _html += String.format(getPageNaviOn(), "", i);
            } else {
                _html += String.format(getPageNaviOff(), clickScript(i), i);
            }
        }

        // 다음페이지
        int nextFirst = currentPage + 1;
        if ( currentPage !=  lastPage )
            _html += String.format(getCurrentLastPageNavi(), clickScript(nextFirst));
        
        // 마지막으로
        // 같은 페이징 줄에 있지 않는 이상은 마지막으로를 표시로 바꾼다.
        // 마지막 페이지가 아닌이상 표시한다면 다음페이지에 있는 것을 가지고 오고 처음부터 표시하고 싶다면 if문을 제거하면 된다.
        if (Math.ceil((double)currentPage / (double)viewPageCount) !=  Math.ceil((double)lastPage / (double)viewPageCount) )
            _html += String.format(getLastPageNavi(), clickScript(lastPage));

        return _html;
    }

    /**
     * Mobile 페이징 HTML make
     *
     * @param currentPage
     * @param lastPage
     * @return
     */
    private String makeMobilePageHtmlComponent(int currentPage, int lastPage) {
        String _html = "";
        if (currentPage < lastPage) {
            _html += String.format(getPageNaviOn(), clickScript(currentPage + 1));

        }

        if (currentPage == lastPage) {
            _html += "";
        }
        return _html;
    }

    public void setPageNaviParam() {

        firstPageNavi = "<li><a href=\"javascript:void(0)\" aria-label=\"Previous\" %s><span aria-hidden=\"true\">«</span></a></li>";
        currentFirstPageNavi = "<li><a href=\"javascript:void(0)\" %s><span aria-hidden=\"true\"><</span></a></li>";
        currentLastPageNavi = "<li><a href=\"javascript:void(0)\" %s><span aria-hidden=\"true\">></span></a></li>";
        lastPageNavi = "<li><a href=\"javascript:void(0)\" aria-label=\"Next\" %s><span aria-hidden=\"true\">»</span></a></li>";
        pageNaviOff = "<li><a href=\"javascript:void(0)\" %s>%d</a></li>";
        pageNaviOn = "<li class=\"active\"><a href=\"javascript:void(0)\" %s>%d</a></li>";

    }

    public void setMobilePageNaviParam() {
        pageNaviOn = "<button type=\"button\" class=\"btn btn-dark btn-radius\" %s><em>더보기</em></button>";
    }

    public String clickScript(int page) {
        return "onclick=\"goPage(" + Integer.toString(page) + ")\"";
    }
}
