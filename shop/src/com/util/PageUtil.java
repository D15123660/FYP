package com.util;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * Paging tools
 */
public class PageUtil {
	
	/**
	 * Get the pagination code
	 * @param total total page
	 * @param page Current page
	 * @param size Product number per page
	 * @return
	 */
	public static String getPageHtml(HttpServletRequest request, long total, int page, int size){
		if (total <= 0) {
			return null;
		}
		// Calculate the total number of pages
		int pages = (int) (total % size ==0 ? total/size : total /size + 1);
		pages = pages == 0 ? 1 : pages;
		// Request address
		String url = request.getRequestURL().toString();
		// Request parameter
		StringBuilder paramBuilder = new StringBuilder();
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			if(param.indexOf("page") > -1) {
				continue;
			}
			paramBuilder.append("&").append(param).append("=").append(request.getParameter(param));
		}
		
		// Paging string
		StringBuilder pageBuilder = new StringBuilder();
		pageBuilder.append("<div class='page text-center clearfix'><span class='pro_link'>");
		// 上一页
		if (page <= 1) { // If it is already the first page, the previous button is disabled
			pageBuilder.append("<a href='javascript:;' title='Is the homepage'><<</a>");
			pageBuilder.append("<a href='javascript:;' title='Is the homepage'><</a>");
		}else{
			pageBuilder.append("<a title='Homepage' href='").append(url).append("?").append("page=").append(page-1)
			.append(paramBuilder).append("'><<</a>");
			pageBuilder.append("<a title='Previous page' href='").append(url).append("?").append("page=").append(page-1)
			.append(paramBuilder).append("'><</a>");
		}
		// Middle digital page number
		if (pages <= 7) { // Show all
			for (int i = 1; i <= pages; i++) {
				pageBuilder.append(packPageItem(url, paramBuilder.toString(), page, i));
			}
		}else{ // Show some
			if (page<4 || page>pages-3) { // 1 2 3 ... pages-2 pages-1 pages
				pageBuilder.append(packPageItem(url, paramBuilder.toString(), page, 1));
				pageBuilder.append(packPageItem(url, paramBuilder.toString(), page, 2));
				pageBuilder.append(packPageItem(url, paramBuilder.toString(), page, 3));
				pageBuilder.append(" ... ");
				pageBuilder.append(packPageItem(url, paramBuilder.toString(), page, pages-2));
				pageBuilder.append(packPageItem(url, paramBuilder.toString(), page, pages-1));
				pageBuilder.append(packPageItem(url, paramBuilder.toString(), page, pages));
			}else{	// 1 2 ... page-1 page page+1 ... pages-1 pages
				pageBuilder.append(packPageItem(url, paramBuilder.toString(), page, 1));
				pageBuilder.append(packPageItem(url, paramBuilder.toString(), page, 2));
				pageBuilder.append(" ... ");
				pageBuilder.append(packPageItem(url, paramBuilder.toString(), page, page-1));
				pageBuilder.append(packPageItem(url, paramBuilder.toString(), page, page));
				pageBuilder.append(packPageItem(url, paramBuilder.toString(), page, page+1));
				pageBuilder.append(" ... ");
				pageBuilder.append(packPageItem(url, paramBuilder.toString(), page, pages-1));
				pageBuilder.append(packPageItem(url, paramBuilder.toString(), page, pages));
			}
		}
		// Next page
		if (page >= pages) { // If it is the last page, the next page button is disabled
			pageBuilder.append("<a href='javascript:;' title='Is the last page'>></a>");
			pageBuilder.append("<a href='javascript:;' title='Is the last page'>>></a>");
		}else{
			pageBuilder.append("<a title='Next page' href='").append(url).append("?").append("page=").append(page+1)
			.append(paramBuilder).append("'>></a>");
			pageBuilder.append("<a title='last page' href='").append(url).append("?").append("page=").append(page+1)
			.append(paramBuilder).append("'>>></a>");
		}
		pageBuilder.append("</div>");
		return pageBuilder.toString();
	}

	/**
	 * Encapsulate pagination items
	 * @param url
	 * @param params
	 * @param page
	 * @param i
	 * @return
	 */
	private static String packPageItem(String url, String params, int page, int i) {
		StringBuilder pageBuilder = new StringBuilder();
		if (i == page) {
			pageBuilder.append("<a class='on'>").append(i).append("</a>");
		}else{
			pageBuilder.append("<a class='num' title='Is").append(i).append("page' href='").append(url).append("?").append("page=").append(i)
				.append(params).append("'>");pageBuilder.append(i).append("</a>");
		}
		return pageBuilder.toString();
	}
	
	/**
	 * Get the pagination code
	 * @param total total
	 * @param page The current page
	 * @param size Product number per page
	 * @return
	 */
	public static String getPageTool(HttpServletRequest request, long total, int page, int size){
		long pages = total % size ==0 ? total/size : total /size + 1;
		pages = pages==0 ? 1 : pages;
		String url = request.getRequestURL().toString();
		StringBuilder queryString = new StringBuilder();
		Enumeration<String> enumeration = request.getParameterNames();
		try { // Assembly request parameters
			while (enumeration.hasMoreElements()) {
				String element = (String) enumeration.nextElement();
				if(!element.contains("page")) { // Skip page parameter
					queryString.append("&").append(element).append("=").append(java.net.URLEncoder.encode(request.getParameter(element),"UTF-8"));
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// Assemble the paging code
		StringBuilder buf = new StringBuilder();
		buf.append("<div style='text-align:center;'>\n");
		if (page <= 1) {
			buf.append("<a class='btn btn-info' disabled >Home page</a>\n");
		}else{
			buf.append("<a class='btn btn-info' href='").append(url).append("?page=").append(1).append(queryString).append("'>Homepage</a>\n");
		}
		if (page <= 1) {
			buf.append("<a class='btn btn-info' disabled >Previous page</a>\n");
		}else {
			buf.append("<a class='btn btn-info' href='").append(url).append("?page=").append(page>1 ? page-1 : 1).append(queryString).append("'>Previous page</a>\n");
		}
		buf.append("<h2 style='display:inline;'>[").append(page).append("/").append(pages).append("]</h2>\n");
		buf.append("<h2 style='display:inline;'>[").append(total).append("]</h2>\n");
		if (page >= pages) {
			buf.append("<a class='btn btn-info' disabled >Next page</a>\n");
		}else {
			buf.append("<a class='btn btn-info' href='").append(url).append("?page=").append(page<pages ? page+1 : pages).append(queryString).append("'>Next page</a>\n");
		}
		if (page >= pages) {
			buf.append("<a class='btn btn-info' disabled >Last page</a>\n");
		}else {
			buf.append("<a class='btn btn-info' href='").append(url).append("?page=").append(pages).append(queryString).append("'>Last page</a>\n");
		}
		buf.append("<input type='text' class='form-control' style='display:inline;width:60px;' value=''/>");
		buf.append("<a class='btn btn-info' href='javascript:void(0);' onclick='location.href=\"").append(url).append("?page=").append("\"+(this.previousSibling.value)+\"").append(queryString).append("\"'>GO</a>\n");
		buf.append("</div>\n");
		return buf.toString();
	}

}
