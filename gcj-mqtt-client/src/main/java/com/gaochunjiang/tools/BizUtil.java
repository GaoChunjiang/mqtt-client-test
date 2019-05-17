package com.gaochunjiang.tools;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BizUtil {
	/**
	 * 判断是否为 null、""、"null"
	 */
	public static boolean isNull(Object obj){
		boolean result = false;
		try{
			if(obj instanceof String){
				String str = (String)obj;
				if(str==null || str.length()==0 || "null".equalsIgnoreCase(str)){
					result = true;
				}
			} else {
				if(obj == null){
					result = true;
				}
			}
		} catch (Exception e) { }
		return result;
	}
	
	/**
	 * 是否不为空
	 */
	public static boolean isNotNull(Object obj){
		return !isNull(obj);
	}
	
	/**
	 * null 替换为 ""
	 * @return
	 */
	public static String replaceNullStr(Object obj){
		return nullToStr(obj, "");
	}
	
	/**
	 * null 替换为 ""
	 * @return
	 */
	public static Object replaceNull(Object obj){
		return nullToObj(obj, "");
	}
	
	/**
	 * obj 为 null 替换为 str
	 * @param obj
	 * @param str
	 * @return
	 */
	public static Object nullToObj(Object obj, Object str){
		return isNull(obj) ? str : obj;
	}
	
	/**
	 * obj 为 null 替换为 str
	 * @param obj
	 * @param str
	 * @return
	 */
	public static String nullToStr(Object obj, String str){
		Object rst = nullToObj(obj, str);
		return rst == null ? null : (rst instanceof String ? (String)rst : rst.toString());
	}
	
	/**
	 * 是否为数字（整数）
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str){
		boolean result = false;
		//
		try{
			if(isNotNull(str)){
				String regex = "^([1-9]\\d*)|(0)$";
				result = str.matches(regex);
			}
		} catch (Exception e) { }
		//
		return result;
	}
	
	/**
	 * 字符串转整形
	 * @return
	 */
	public static Integer stringToInteger(String str, Integer def){
		try{
			return Integer.valueOf(str);
		} catch(Exception e){
			return def;
		}
	}
	
	/**
	 * 字符串转浮点型
	 * @return
	 */
	public static Double stringToDouble(String str, Double def){
		try{
			return Double.valueOf(str);
		} catch(Exception e){
			return def;
		}
	}
	
	/**
	 * 获得当前文字的长度，中文为2个字符
	 */
	public static int chineseLen(String fromStr) {
		if(isNull(fromStr)){ return 0; }
		int fromLen = fromStr.length();
		int chineseLen = 0;
		for(int i = 0;i<fromLen;i++){
			if(gbValue(fromStr.charAt(i))>0){
				chineseLen = chineseLen + 2;
			}else{
				chineseLen ++;
			}
		}
		return chineseLen;
	}
	
	/**
	 * 返回GBK值
	 */
	public static int gbValue(char ch) {
		String str = new String();
		str += ch;
		try {
			byte[] bytes = str.getBytes("GBK");
			if (bytes.length < 2) return 0;
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 集合转数组
	 * @param list
	 * @return
	 */
	public static String[] listToArray(List<String> list){
		String[] result = null;
		if(list != null && list.size() > 0){
			result = new String[list.size()];
			for(int i=0; i<list.size(); i++){
				result[i] = list.get(i);
			}
		}
		return result;
	}
	
	/**
	 * 集合转字符串（英文逗号分隔）
	 * @param list
	 * @return
	 */
	public static String listToString(List<String> list){
		StringBuffer result = new StringBuffer();
		if(list != null && list.size() > 0){
			for(int i=0; i<list.size(); i++){
				if(i > 0){
					result.append(",");
				}
				result.append(list.get(i));
			}
		}
		return result.toString();
	}
	
	/**
	 * 数组转集合
	 * @param list
	 * @return
	 */
	public static List<String> arrayToList(String[] ary){
		List<String> result = null;
		if(ary != null && ary.length > 0){
			result = new ArrayList<String>();
			for(int i=0; i<ary.length; i++){
				result.add(ary[i]);
			}
		}
		return result;
	}
	
	/**
	 * 保留 num 位小数
	 * @param val
	 * @param num
	 * @return
	 */
	public static String formatDouble(double val, int num){
		StringBuffer formatStr = new StringBuffer();
		formatStr.append("#");
		if(num > 0){
			formatStr.append(".");
		}
		for(int i=0; i<num; i++){
			formatStr.append("#");
		}
		DecimalFormat df = new DecimalFormat(formatStr.toString()); 
		String rst = df.format(val);
		return rst;
	}
	
	/**
	 * id 去重，去异常
	 * @param ids
	 * @return
	 */
	public static String getValidIds(String ids){
		String rst = null;
		if(isNotNull(ids)){
			String[] idAry = ids.split(",");
			List<String> idList = new ArrayList<String>();
			for(String id : idAry){
				int idInt = stringToInteger(id, 0);
				if(idInt > 0 && idList.contains(""+idInt) == false){
					idList.add(""+idInt);
				}
			}
			//
			rst = listToString(idList);
		}
		return rst;
	}
}
