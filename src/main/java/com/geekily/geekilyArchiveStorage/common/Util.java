package com.geekily.geekilyArchiveStorage.common;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author geekily
 *
 */
public class Util {

	/**
	 * @name 		isEmpty
	 * @date 		2024. 3. 14.
	 * @author		geekily
	 * @description	check if string variable is null.
	 */
	public static boolean isEmpty(String value) {
	    return value == null || value.isEmpty();
	}
	
	/**
	 * @name 		isEmpty
	 * @date 		2024. 11. 10.
	 * @author		geekily
	 * @description	check if map variable is null.
	 */
	public static boolean isEmpty(Map map) {
	    return map == null;
	}	
	
	/**
	 * @name 		isNotEmpty
	 * @date 		2024. 3. 14.
	 * @author		geekily
	 * @description check if string variable isn't null.
	 */
	public static Boolean isNotEmpty(String value) {
	    return value != null && !value.isEmpty();
	}
	
	/**
	 * @name 		isNotEmpty
	 * @date 		2024. 3. 14.
	 * @author		geekily
	 * @description check if map variable isn't null.
	 */
	public static Boolean isNotEmpty(Map map) {
	    return map != null;
	}
	
	/**
	 * @name 		isLogin
	 * @date 		2024. 3. 14.
	 * @author		geekily
	 * @description check if user is logged in or not.
	 */
	public static Boolean isLogin(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		String 		adminUid 	= (String) httpSession.getAttribute("adminUid");
		if(isEmpty(adminUid)) {
			return false;
		}
		return true;
	}
	
	/**
	 * @name 		getSubPath
	 * @date 		2024. 3. 14.
	 * @author		geekily
	 * @description return sub path of url.
	 */
	public static String getSubPath(HttpServletRequest request) {
		return request.getRequestURI().replace(request.getContextPath(), "");
	}
	
	/**
	 * @name 		splitSubPath
	 * @date 		2024. 3. 14.
	 * @author		geekily
	 * @description split sub path by "/".
	 */
	public static String[] splitSubPath(String subPath) {
		String editedSubPath = Arrays.stream(subPath.substring(subPath.indexOf('/') + 1).split("/"))
                .limit(2)
                .collect(Collectors.joining("/"));
		return editedSubPath.split("/");
	}
	
	/**
	 * @name 		parseToInt
	 * @date 		2024. 3. 14.
	 * @author		geekily
	 * @description	after checking type of object variable, convert object to integer.
	 */
	public static int parseToInt(Object object) {
		int result = 0;
		if(object instanceof String) {
			String stringValue = (String) object;
			result = Integer.parseInt(stringValue);
		}else {
			result = (int) object;
		}
		return result;
	}
	
	/**
	 * @name 		generateUID
	 * @date 		2024. 8. 31.
	 * @author		geekily
	 * @description return UID without prefix.
	 */
	public static String generateUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * @name 		generateUID
	 * @date 		2024. 3. 14.
	 * @author		geekily
	 * @description return UID.
	 */
	public static String generateUID(String prefix) {
		return prefix + "-" + UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * @name 		getExtension
	 * @date 		2024. 8. 31.
	 * @author		geekily
	 * @description return file's extension.
	 */
	public static String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	/**
	 * @name 		getExtension
	 * @date 		2024. 8. 31.
	 * @author		geekily
	 * @description return file's extension.
	 */
	public static String getExtensionWithoutDot(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}
	
	/**
	 * @name 		getFileName
	 * @date 		2024. 4. 4.
	 * @author		geekily
	 * @description return file name.
	 */
	public static String getFileName(MultipartFile multipartFile) {
		String origianlFileName = multipartFile.getOriginalFilename();
        String extension 		= getExtension(origianlFileName);
		return generateUID() + extension;
	}
	
	/**
	 * @name 		getFileName
	 * @date 		2024. 4. 4.
	 * @author		geekily
	 * @description return file name.
	 */
	public static String getFileName(String origianlFileName) {
        String extension = getExtension(origianlFileName);
		return generateUID() + extension;
	}
	
	/**
	 * @name 		getFileServerUrl
	 * @date 		2024. 4. 4.
	 * @author		geekily
	 * @description return file server upload url
	 */
	public static String getFileServerUploadUrl() {
		return PropertyUtil.getProperty("file.server.upload.url");
	}
	
	/**
	 * @name 		getFileAccessURL
	 * @date 		2024. 4. 11.
	 * @author		geekily
	 * @description return file access url
	 */
	public static String getFileAccessURL() {
		return PropertyUtil.getProperty("file.access.url");
	}
	
	/**
	 * @name 		getUploadPath
	 * @date 		2024. 4. 9.
	 * @author		geekily
	 * @description return upload path
	 */
	public static String getUploadPath() {
		return PropertyUtil.getProperty("upload.path");
	}

	/**
	 * @name 		getDatePath
	 * @date 		2024. 8. 31.
	 * @author		geekily
	 * @description return date path
	 */
	public static String getDatePath() {
        LocalDate today = LocalDate.now();
        int year 		= today.getYear();
        int month 		= today.getMonthValue();
        int day 		= today.getDayOfMonth();
        return year + File.separator + String.format("%02d", month) + File.separator + String.format("%02d", day) + File.separator;
	}	
	
	/**
	 * @name 		hierarchicalSortList
	 * @date 		2024. 3. 14.
	 * @author		geekily
	 * @description sort data in hierarchical order. it's written because MYSQL doesn't have such function like Oracle.
	 */
	public static List<Map<String, Object>> hierarchicalSortList(String pkCoulmnName, String parentPkCoulmnName, String depthColumnName, String orderCoulmnName, List<Map<String, Object>> list){
		
		int fd = 0;
		for(Map<String, Object> m : list) {
			m.put(depthColumnName, parseToInt(m.get(depthColumnName)));
			m.put(orderCoulmnName, parseToInt(m.get(orderCoulmnName)));
			
			int dpth = (int) m.get(depthColumnName);
			if(fd < dpth) {fd = dpth;}
		}
		
		List<List<Map<String, Object>>> ssl = new ArrayList<List<Map<String,Object>>>();
		for(int i = 1; i <= fd; i++) {
			List<Map<String, Object>> odsl = new ArrayList<Map<String,Object>>();
			for(int j = 0; j < list.size(); j++) {
				if(i == (int) list.get(j).get(depthColumnName)) {
					odsl.add(list.get(j));
				}
			}	
			ssl.add(odsl);
		}
		
		for(int i = ssl.size() - 1; i >= 1; i--) {
			List<Map<String, Object>> sdsl 	= ssl.get(i);
			List<Map<String, Object>> bdsl 	= ssl.get(i - 1);
		
			if(i == ssl.size() - 1) {
				sortList(parentPkCoulmnName, orderCoulmnName, true, sdsl);
			}else {
				Collections.reverse(sdsl);
			}
			sortList(parentPkCoulmnName, orderCoulmnName, false, bdsl);
		
			int dpth = 0;
			for(int j = 0; j < bdsl.size(); j++) {
				Map<String, Object> bdm = bdsl.get(j);
				String gu = (String) bdm.get(parentPkCoulmnName);
				String pk = (String) bdm.get(pkCoulmnName);
				
				if(j == 0) {dpth = (int) bdm.get(depthColumnName);}
				if(dpth == (int) bdm.get(depthColumnName)) {bdm.put("gu", i != 1 ? gu : pk);}
	
				for(int k = 0; k < sdsl.size(); k++) {
					Map<String, Object> sdm = sdsl.get(k);
					if(pk.equals((String) sdm.get(i == ssl.size() - 1 ? parentPkCoulmnName : "gu"))) {
						sdm.put("gu", i != 1 ? gu : pk);
						bdsl.add(j + 1, sdsl.get(k));
					}
				}
			}
		}
		
		if(ssl.size() > 1) {
			ssl.subList(1, ssl.size()).clear();
		}
		 
		
		return removeUnnecessaryMenu(parentPkCoulmnName, ssl.get(0));
	}
	
    /**
     * @name 		sortList
     * @date 		2024. 3. 14.
     * @author		geekily
     * @description	this method is used only for the method 'hierarchicalSortList'.
     * 				it's for sorting data.
     */
    private static void sortList(String parentPkCoulmnName, String orderCoulmnName, Boolean flagReverse, List<Map<String, Object>> dataList) {
        Collections.sort(dataList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                String p1 = "";
                String p2 = "";
                if(map1.get(parentPkCoulmnName) != null) {
                	p1 = map1.get(parentPkCoulmnName).toString();
                }
                if(map2.get(parentPkCoulmnName) != null) {
                	p2 = map2.get(parentPkCoulmnName).toString();
                }
                
                int c;
                if(flagReverse) {c = p2.compareTo(p1);
                }else {c = p1.compareTo(p2);}

                if(c != 0) {return c;}

                // order 비교
                Integer o1 = Integer.parseInt(map1.get(orderCoulmnName).toString());
                Integer o2 = Integer.parseInt(map2.get(orderCoulmnName).toString());

                if(flagReverse) {return o2.compareTo(o1);
                }else {return o1.compareTo(o2);}
            }
        });
    }
    
	/**
	 * @name 		removeUnnecessaryMenu
	 * @date 		2024. 3. 14.
	 * @author		geekily
	 * @description this method is used only for the method 'hierarchicalSortList'.
	 * 				remove unnecessary data after sorting.
	 */
	private static List<Map<String, Object>> removeUnnecessaryMenu(String parentPkCoulmnName, List<Map<String, Object>> dataList) {
		List<Map<String, Object>> childMenuList = null;
		Map<String, Object> parentMenuMap 		= null;
		String groupUid 						= null;
		for(int i = 0; i < dataList.size(); i++) {
			Map<String, Object> menu = dataList.get(i);
			if(i == 0 || !groupUid.equals((String) menu.get("gu"))) {
				if(i != 0) {
					parentMenuMap.put("childList", childMenuList);						
				}
				
				childMenuList	= new ArrayList<Map<String,Object>>();
				parentMenuMap 	= menu;
				groupUid 		= (String) menu.get("gu");
			}else {
				childMenuList.add(menu);
			}
			
			if(i == dataList.size() - 1) {
				parentMenuMap.put("childList", childMenuList);
			}
		}
		
		for(int i = dataList.size() - 1; i >= 0; i--) {
			Map<String, Object> menu = dataList.get(i);
			if(Util.isEmpty((String) menu.get(parentPkCoulmnName))) {
				continue;
			}else {
				dataList.remove(i);
			}
		}
		return dataList;
	}
}
