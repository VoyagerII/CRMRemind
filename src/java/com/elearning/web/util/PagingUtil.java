package com.elearning.web.util;

import java.util.Map;

/**
 * 
 * TODO 分页
 * 
 * @author xinglt
 * @date 2014年7月25日 下午5:15:55
 *
 */
public class PagingUtil {

	private static final int PAGE_NO = 1;

	private static final int PAGE_COUNT = 0;

	private static final int TOTAL_COUNT = 0;

	private static final int TOTAL_PAGES = 1;

	/**
	 * 计算总页数
	 * 
	 * @return
	 */
	private static int getTotalPages(int totalCount, int pageCount) {

		if (totalCount > 0 && pageCount > 0) {
			if ((totalCount % pageCount) > 0) {
				return ((int) (totalCount / pageCount)) + 1;
			} else {
				return ((int) (totalCount / pageCount));
			}
		}

		return TOTAL_PAGES;
	}

	/**
	 * 页数列表
	 * 
	 * @param totalCount
	 * @param pageSize
	 * @return
	 */
	private static int[] getAllPages(int totalCount, int pageCount) {

		int totalPages = TOTAL_PAGES;

		if (totalCount > 0 && pageCount > 0) {
			if ((totalCount % pageCount) > 0) {
				totalPages = ((int) (totalCount / pageCount)) + 1;
			} else {
				totalPages = ((int) (totalCount / pageCount));
			}
		}

		//
		int[] allPages = new int[totalPages];
		if (totalPages > 0) {
			for (int i = 0; i < totalPages; i++) {
				allPages[i] = (i + 1);
			}
		}

		return allPages;
	}

	/**
	 * SQL limit
	 * 
	 * @param pageTotal
	 * @return
	 */
	private static int limit(int pageCount) {

		if (pageCount > 0) {
			return pageCount;
		}
		return PAGE_COUNT;
	}

	/**
	 * SQL offset
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	private static int offset(int pageNo, int pageCount) {

		if (pageNo > 1) {
			return (pageNo - 1) * pageCount;
		}

		return 0;
	}

	/**
	 * 
	 * 获取分页参数
	 * 
	 * @param paramterMap
	 */
	public static void getPaging(PageInfo pinfo, Map<String, Object> paramMap) {

		int pageNo = PAGE_NO, pageCount = PAGE_COUNT, totalCount = TOTAL_COUNT, totalPages = TOTAL_PAGES;

		//
		pageNo = pinfo.getPageNo();
		pageCount = pinfo.getPageCount();
		totalCount = pinfo.getTotalCount();

		// 页码列表
		if (totalCount > 0 && pageCount > 0) {
			totalPages = getTotalPages(totalCount, pageCount);
		}

		// 判断页码是否小于0
		if (pageNo <= PAGE_NO) {
			pageNo = PAGE_NO;
		}

		// 判断页码是否总页数
		if (pageNo > totalPages) {
			pageNo = totalPages;
		}

		//
		paramMap.put("pageIndex", pageNo);
		paramMap.put("total", totalCount);
		paramMap.put("totalPages", totalPages);
		pinfo.setTotalPages(totalPages);

		paramMap.put("totalpageArray", getAllPages(totalCount, pageCount));
		//
		paramMap.put("limit", limit(pageCount));
		paramMap.put("offset", offset(pageNo, pageCount));
	}

	public static void main(String[] args) {

		System.out.println(getTotalPages(100, 10) + " :: " + getAllPages(100, 10).length);
	}
}