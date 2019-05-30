package org.framework.core.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：Jackson 工具类
 * <p>
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 烟台海涛网络科技有限公司
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-10 15:47
 */
public class Jackson {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static XmlMapper xmlMapper = new XmlMapper();

    /**
     * @Description:javaBean, list, array convert to json string
     * @Date: 15:51 2019/5/10
     * @param entity : Java Object
     * @Return:json string
     */
    public static <T> String obj2json(T entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{}";
    }

    /**
     * @Description:json string convert to javaBean
     * @Date: 16:04 2019/5/10
     * @param json : JSON 字符串
     * @Param clz  : Java Object Class
     * @Return:Java Object
     */
    public static <T> T json2pojo(String json, Class<T> clz) {
        try {
            return objectMapper.readValue(json, clz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description:json string convert to map
     * @Date: 16:03 2019/5/10
     * @param json : JSON 字符串
     * @return Java Object
     * @Return:
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> json2map(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<String, Object>();
    }

    /**
     * @Description:json string convert to map with javaBean
     * @Date: 16:03 2019/5/10
     * @param json : json 字符串
     * @Param: clz		 : Java class
     * @Return:
     */
    public static <T> Map<String, T> json2map(String json, Class<T> clz) {
        Map<String, T> result = new HashMap<String, T>();
        Map<String, Map<String, Object>> map;
        try {
            map = objectMapper.readValue(json, new TypeReference<Map<String, T>>() { });
            for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
                result.put(entry.getKey(), map2pojo(entry.getValue(), clz));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Description:json array string convert to list with javaBean
     * @Date: 16:00 2019/5/10
     * @Param: jsonArray	: json array string
     * @param clz			: java class
     * @Return:
     */
    public static <T> List<T> json2list(String jsonArray, Class<T> clz) {
        List<T> result = new ArrayList<T>();
        List<Map<String, Object>> list;
        try {
            list = objectMapper.readValue(jsonArray, new TypeReference<List<T>>() { });
            for (Map<String, Object> map : list) {
                result.add(map2pojo(map, clz));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Description: map convert to javaBean
     * @Date: 15:54 2019/5/10
     * @param map : map&lt;String, Object&gt; Object
     * @Param: clz : java class
     * @Return:java object
     */
    public static <T> T map2pojo(Map<String, Object> map, Class<T> clz) {
        return objectMapper.convertValue(map, clz);
    }

    /**
     * @Description:javaBean convert to map
     * @Date: 15:54 2019/5/10
     * @Param object : Java Object
     * @Return:Map&lt;String, Object&gt;
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> obj2map(Object object) {
        return objectMapper.convertValue(object, Map.class);
    }

    /**
     * @Description:json string convert to xml string
     * @Date: 15:52 2019/5/10
     * @Param xml string
     * @Return:
     */
    public static String json2xml(String jsonString) throws Exception {
        JsonNode root = objectMapper.readTree(jsonString);
        return xmlMapper.writeValueAsString(root);
    }

    /**
     * @Description:xml string convert to json string
     * @Date: 15:52 2019/5/10
     * @Param xml : xml string
     * @Return:json string
     */
    public static String xml2json(String xml) throws Exception {
        StringWriter w = new StringWriter();
        JsonParser jp = xmlMapper.getFactory().createParser(xml);
        JsonGenerator jg = objectMapper.getFactory().createGenerator(w);
        while (jp.nextToken() != null) {
            jg.copyCurrentEvent(jp);
        }
        jp.close();
        jg.close();
        return w.toString();
    }
}
