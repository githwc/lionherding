package com.lh.common.utils;


import com.lh.common.encoder.BASE64;

import java.io.*;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * 功能描述：数据类型转换工具包
 * <p>
 * <p>版权所有：</p>
 * 未经人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-05-09
 */
public class ConverterUtil {

    /**
     * 将Clob类型数据转换成字符串类型数据
     * @param content : clob对象
     * @return 转换的结果字符串
     * @throws SQLException
     */
    public static String clob2String(Clob content) throws SQLException {
        return content != null ? content.getSubString(1, (int) content.length()) : "";
    }

    /**
     * 通过流的方式读取
     * 将Clob类型数据转换成字符串类型数据
     * @param content : clob对象
     * @return 转换的结果字符串
     * @throws SQLException
     * @throws IOException
     */
    public static String clob2StringByStream(Clob content) throws SQLException, IOException {
        String str = "";
        if (content != null) {
            Reader is = content.getCharacterStream();
            BufferedReader br = new BufferedReader(is);
            String s = br.readLine();
            StringBuffer sb = new StringBuffer();
            while (s != null) {
                sb.append(s);
                s = br.readLine();
            }
            str = sb.toString();
            if (br != null) {
                br.close();
            }
            if (is != null) {
                is.close();
            }
        }
        return str;
    }

    /**
     * SET转换MAP
     *
     // * @param str
     * @return
     */
    public static Map<Object, Object> SetToMap(Set<Object> setobj) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        for (Iterator iterator = setobj.iterator(); iterator.hasNext();) {
            Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) iterator.next();
            map.put(entry.getKey().toString(), entry.getValue() == null ? "" : entry.getValue().toString().trim());
        }
        return map;
    }

    /**
     * 将 ResultSet 转化成 List
     * @param rs : {@link java.lang.ResultSet}
     * @return List&lt;HashMap&lt;String, Object&gt;&gt;
     */
    public static List<HashMap<String, Object>> ResultSet2List(ResultSet rs) {
        List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i).toLowerCase();
                    String value = rs.getString(columnName);
                    map.put(columnName, value);
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将图片转换成 Base64 字符串
     *
     * @return 图片内容的Base64 字符串
     */
    public static String image2String(File image) {
        String str = "";
        try {
            InputStream in = new FileInputStream(image);
            byte[] data = new byte[in.available()];
            in.read(data);
            in.close();
            str = BASE64.encrypt(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
