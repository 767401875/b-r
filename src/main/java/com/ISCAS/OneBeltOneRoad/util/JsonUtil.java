package com.ISCAS.OneBeltOneRoad.util;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class JsonUtil {
    public static void main(String[] args){
//        JsonUtil obj = new JsonUtil();
//        File excelFile = new File("E:\\项目\\一带一路\\数据调研\\ITC\\product\\Organic chemicals exporters.xls");
//        File excelFile = new File("E:\\项目\\一带一路\\数据调研\\UNCTAD\\Volume growth rates of merchandise\\Volume growth rates of merchandise exports, quarterly, Q1 2005-Q2 2018.xls");
//        File excelFile = new File("E:\\项目\\一带一路\\数据调研\\UNCTAD\\indices of exports and imports\\indices of imports, annual.xls");
//        List excelList = obj.readExcel(excelFile);
//        for (int i = 0; i < excelList.size(); i++) {
//            List list = (List) excelList.get(i);
//            for (int j = 0; j < list.size(); j++) {
//                System.out.print(list.get(j) + " \\");
//            }
//            System.out.println();
//        }
//        File geoJsonFile = new File("E:\\code\\temp.json");
//        File geoJsonFile = new File("E:\\code\\temp_t.geojson");
//        File geoJsonFile = new File("E:\\code\\timeLineGeoJson.geojson");
//        readGeoJson(geoJsonFile, excelFile);
//        readGDPArrayGeoJson(geoJsonFile, excelFile);
//        readIndicesArrayGeoJson(geoJsonFile, excelFile);
//        timeLineGeoJson(geoJsonFile);
//        timeLineGdpGeoJson(geoJsonFile);
//        induceGeoJson(geoJsonFile);
    }

    //读取geojson的方法
    public static JSONObject readGeoJson(File geoJsonFile, File excelFile){
        JsonUtil obj = new JsonUtil();
        List excelList = obj.readExcel(excelFile);
        BufferedReader br;
        BufferedWriter bw;
        try {
            br = new BufferedReader(new FileReader(geoJsonFile.getPath()));
            bw = new BufferedWriter(new FileWriter("E:\\code\\new_countries8.geojson"));

            String s = null, ws = null;
            while ((s = br.readLine()) != null){
                try {
                    JSONObject dataJson = new JSONObject(s);
                    JSONArray features = dataJson.getJSONArray("features");
                    //增加文件指标到features
                    String indexName = excelFile.getName().split("\\.")[0];
                    for(int i = 0; i < features.length(); i++){
                        //定位到国家Feature
                        JSONObject info = features.getJSONObject(i);
                        JSONObject properties = info.getJSONObject("properties");
                        String comment = properties.getString("_comment");
                        JSONObject indexJSONObject = new JSONObject();
                        properties.put(indexName, indexJSONObject);
                        //Excel第一行是指标名称，其他行是数据
                        for (int j = 1; j < excelList.size(); j++) {
                            List list = (List) excelList.get(j);
                            //第一列为国家名字，通过国家名字来进行匹配
                            if(comment.equals(list.get(0))){
                                for(int k = 1; k < list.size(); k++){
                                    String indexDetail = (String) list.get(k);
                                    if(indexDetail.isEmpty()){
                                        continue;
                                    }
                                    List indexDetailNames = (List) excelList.get(0);
                                    String indexDetailName = (String)indexDetailNames.get(k);
                                    indexJSONObject.put(indexDetailName, indexDetail);
                                }
                            }
                        }
                        //增加文件指标到feature中

                    }
                    ws = dataJson.toString();
                    System.out.println(ws);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
            bw.write(ws);
            bw.flush();
            br.close();
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    // 去读Excel的方法readExcel，该方法的入口参数为一个File对象
    public List readExcel(File file) {
        try {
            // 创建输入流，读取Excel
            InputStream is = new FileInputStream(file.getAbsolutePath());
            // jxl提供的Workbook类
            Workbook wb = Workbook.getWorkbook(is);
            // Excel的页签数量
            int sheet_size = wb.getNumberOfSheets();
            for (int index = 0; index < sheet_size; index++) {
                List<List> outerList=new ArrayList<List>();
                // 每个页签创建一个Sheet对象
                Sheet sheet = wb.getSheet(index);
                // sheet.getRows()返回该页的总行数
                for (int i = 0; i < sheet.getRows(); i++) {
                    List innerList=new ArrayList();
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        String cellinfo = sheet.getCell(j, i).getContents();
//                        if(cellinfo.isEmpty()){
//                            continue;
//                        }
                        innerList.add(cellinfo);
//                        System.out.print(cellinfo + " |");
                    }
                    outerList.add(i, innerList);
//                    System.out.println();
                }
                return outerList;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //读取GDP文件
    public static JSONObject readGDPArrayGeoJson(File geoJsonFile, File excelFile){
        JsonUtil obj = new JsonUtil();
        List excelList = obj.readExcel(excelFile);
        BufferedReader br;
        BufferedWriter bw;
        try {
            br = new BufferedReader(new FileReader(geoJsonFile.getPath()));
            bw = new BufferedWriter(new FileWriter("E:\\code\\new_countries13.geojson"));
            String s = null, ws = null;
            while ((s = br.readLine()) != null){
                try {
                    JSONObject dataJson = new JSONObject(s);
                    JSONArray features = dataJson.getJSONArray("features");
                    //增加文件指标到features
                    String indexName = excelFile.getName().split("\\.")[0];
                    for(int i = 0; i < features.length(); i++){
                        //定位到国家Feature
                        JSONObject info = features.getJSONObject(i);
                        JSONObject properties = info.getJSONObject("properties");
                        String comment = properties.getString("_comment");
                        JSONArray indexJSONArray = new JSONArray();
                        properties.put(indexName, indexJSONArray);
                        //Excel第一行是指标名称，其他行是数据
                        for (int j = 1; j < excelList.size(); j++) {
                            List list = (List) excelList.get(j);
                            //第一列为国家名字，通过国家名字来进行匹配
                            if(comment.equals(list.get(0))){
                                JSONObject indexOfArray = new JSONObject();
                                for(int k = 1; k < list.size(); k++){
                                    String indexDetail = (String) list.get(k);
                                    if(indexDetail.isEmpty()){
                                        continue;
                                    }
                                    List indexDetailNames = (List) excelList.get(0);
                                    String indexDetailName = (String)indexDetailNames.get(k);
                                    indexOfArray.put(indexDetailName, indexDetail);
                                }
                                indexJSONArray.put(indexOfArray);
                            }
                        }
                        //增加文件指标到feature中

                    }
                    ws = dataJson.toString();
                    System.out.println(ws);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
            bw.write(ws);
            bw.flush();
            br.close();
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    //读取indices of exports and imports文件
    public static JSONObject readIndicesArrayGeoJson(File geoJsonFile, File excelFile){
        JsonUtil obj = new JsonUtil();
        List excelList = obj.readExcel(excelFile);
        BufferedReader br;
        BufferedWriter bw;
        try {
            br = new BufferedReader(new FileReader(geoJsonFile.getPath()));
            bw = new BufferedWriter(new FileWriter("E:\\code\\new_countries14.geojson"));
            String s = null, ws = null;
            for(int i = 2; i < excelList.size(); i++){
                List list = (List) excelList.get(i);
                for(int j = 0; j < list.size(); j++){
                    String indexDetail = (String) list.get(j);
                    System.out.print(indexDetail + " ");
                }
                System.out.println();
            }
            while ((s = br.readLine()) != null){
                try {
                    JSONObject dataJson = new JSONObject(s);
                    JSONArray features = dataJson.getJSONArray("features");
                    //增加文件指标到features
                    String indexName = excelFile.getName().split("\\.")[0];
                    for(int i = 0; i < features.length(); i++){
                        //定位到国家Feature
                        JSONObject countryObject = features.getJSONObject(i);
                        JSONObject properties = countryObject.getJSONObject("properties");
                        String comment = properties.getString("_comment");
                        JSONObject indexJSON = new JSONObject();
                        //在源文件中移除原有属性
                        if(properties.has(indexName))
                            properties.remove(indexName);
                        /*
                         *  Excel第一行为时间
                         *  第二行为指标名称
                         *  第三行开始为指标值
                         *  第一列为中文名称
                        */
                        for (int j = 2; j < excelList.size(); j++) {
                            List list = (List) excelList.get(j);
                            JSONObject economyObject = new JSONObject();
                            JSONObject timeJsonObjecty = new JSONObject();
                            //第一列为国家名字，通过国家名字来进行匹配
                            if(comment.equals(list.get(0))){
                                List timeList = (List)excelList.get(0);
                                List indexDetailNames = (List) excelList.get(1);
                                for(int k = 2; k < list.size(); k++){
                                    String indexDetail = (String) list.get(k);
                                    if(indexDetail.isEmpty()){
                                        continue;
                                    }
                                    //指标名称
                                    String indexDetailName = (String)indexDetailNames.get(k);
                                    if((k - 2) % 3 == 0){
                                        timeJsonObjecty = new JSONObject();
                                    }
                                    timeJsonObjecty.put(indexDetailName, indexDetail);
                                    //时间
                                    String time = (String)timeList.get(k);
                                    economyObject.put(time, timeJsonObjecty);
                                }
                                indexJSON.put(list.get(1).toString(), economyObject);
                            }
                        }
                        //增加文件指标到feature中
                        //然后重新条件源有属性
                        properties.put(indexName, indexJSON);
                    }
                    ws = dataJson.toString();
//                    System.out.println(ws);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
            bw.write(ws);
            bw.flush();
            br.close();
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public static JSONObject timeJSONObjectHandler(JSONArray features, JSONObject properties,  String propertyStr, JSONObject timeDateJson){
        JSONObject property = properties.getJSONObject(propertyStr);
        Set<String> keySet =  property.keySet();
        System.out.println("---------------");
        System.out.println(property.toString());
        for(String key : keySet){
//          System.out.println(num + ":" + key + " " + foreignInvestmentInward.getString(key));
            JSONObject timeObj = new JSONObject();
            timeObj.put("type", "FeatureCollection");
            JSONArray timeFeatures = new JSONArray();
            for(int j = 0; j < features.length(); j++){
                JSONObject timeFeature = new JSONObject();
                JSONObject timeGeometry = new JSONObject();
                JSONObject timeFeatureObj = features.getJSONObject(j);
                timeGeometry = timeFeatureObj.getJSONObject("geometry");
                String id = timeFeatureObj.getString("id");
                String type = timeFeatureObj.getString("type");
                JSONObject timePropertiesObj  = timeFeatureObj.getJSONObject("properties");
                JSONObject timePropertyObj = timePropertiesObj.getJSONObject(propertyStr);
//              属性
                JSONObject timeProperties = new JSONObject();
                timeProperties.put(propertyStr, timePropertyObj.getString(key));
                timeFeature.put("geometry", timeGeometry);
                timeFeature.put("id", id);
                timeFeature.put("type", type);
                timeFeature.put("_comment", timeFeatureObj.getJSONObject("properties").getString("_comment"));
                timeFeature.put("name", timeFeatureObj.getJSONObject("properties").getString("name"));
                timeFeature.put("properties", timeProperties);
                timeFeatures.put(timeFeature);
            }
            timeObj.put("features", timeFeatures);
            timeDateJson.put(key, timeObj);
        }
        return timeDateJson;
    }
    //propertiesStr中时间等长
    public static JSONObject timeArrayJSONObjectHandler(JSONArray features, JSONObject properties,  String[] propertiesStr, JSONObject timeDateJson){
//        String propertyStr = propertiesStr[0];
        JSONObject[] property = new JSONObject[propertiesStr.length];
        for(int i = 0; i < property.length; i++)
            property[i] = properties.getJSONObject(propertiesStr[i]);
        Set<String> keySet =  property[0].keySet();
        for(String key : keySet){
//          System.out.println(num + ":" + key + " " + foreignInvestmentInward.getString(key));
            JSONObject timeObj = new JSONObject();
            timeObj.put("type", "FeatureCollection");
            JSONArray timeFeatures = new JSONArray();
            for(int j = 0; j < features.length(); j++){
                JSONObject timeFeature = new JSONObject();
                JSONObject timeGeometry = new JSONObject();
                JSONObject timeFeatureObj = features.getJSONObject(j);
                timeGeometry = timeFeatureObj.getJSONObject("geometry");
                String id = timeFeatureObj.getString("id");
                String type = timeFeatureObj.getString("type");
                timeFeatureObj.getJSONObject("properties").getString("_comment");
//              属性
                JSONObject timeProperties = new JSONObject();
                for(int i = 0; i < propertiesStr.length; i++){
                    JSONObject timePropertiesObj  = timeFeatureObj.getJSONObject("properties");
                    JSONObject timePropertyObj = timePropertiesObj.getJSONObject(propertiesStr[i]);
                    timeProperties.put(propertiesStr[i], timePropertyObj.getString(key));
                }

                timeFeature.put("geometry", timeGeometry);
                timeFeature.put("id", id);
                timeFeature.put("type", type);
                timeFeature.put("_comment", timeFeatureObj.getJSONObject("properties").getString("_comment"));
                timeFeature.put("name", timeFeatureObj.getJSONObject("properties").getString("name"));
                timeFeature.put("properties", timeProperties);
                timeFeatures.put(timeFeature);
            }
            timeDateJson.put(key, timeFeatures);
        }
        return timeDateJson;
    }
    //GDP为数组形式
    public static JSONObject timeGDPJSONObjectHandler(JSONArray features, JSONObject properties,  String propertyGDPStr, JSONObject timeDateJson){
        JSONArray gdpProperty = properties.getJSONArray(propertyGDPStr);
        String _comment = properties.getString("_comment");
//        System.out.println(_comment);
        System.out.println(timeDateJson);
        for(int i = 0; i < gdpProperty.length(); i++){
            JSONObject counrtyGdpProperty = gdpProperty.getJSONObject(i);
            Set<String> keySet = counrtyGdpProperty.keySet();
            for(String key : keySet){
                if(key.equals("COMPONENT")){
                    continue;
                }
                if(key.equals("ECONOMY")){
                    continue;
                }
//                System.out.print(key + ":" + counrtyGdpProperty.getString(key) + " ");
            }
//            System.out.println();
//            System.out.println(counrtyGdpProperty.toString());
        }
//        System.out.println(gdpProperty.toString());
        return timeDateJson;
    }
//    读取geojson的方法
    public static JSONObject timeLineGeoJson(File geoJsonFile){
        BufferedReader br;
        BufferedWriter bw;
        try {
            br = new BufferedReader(new FileReader(geoJsonFile.getPath()));
            bw = new BufferedWriter(new FileWriter("E:\\code\\timeLineGeoJson_GDP.geojson"));
            String s = null, ws = null;
            while ((s = br.readLine()) != null){
                try {
                    JSONObject dataJson = new JSONObject(s);
                    JSONObject timeDateJson = new JSONObject();
                    JSONObject timeDateJson1 = new JSONObject();
                    JSONArray features = dataJson.getJSONArray("features");
                    for(int i = 0;i < features.length(); i++){
//                        country下查找geometry
                        JSONObject country = features.getJSONObject(i);
                        JSONObject properties = country.getJSONObject("properties");
                        String[] propertiesStr = {"Foreign direct investment Inward stock, annual", "Foreign direct investment Inward flows, annual", "Foreign direct investment Outward flows stock, annual", "Total and urban population, annual "};
//                        String propertyStr = "Foreign direct investment Inward stock, annual";
//                        String propertyStr = "Foreign direct investment Outward flows, annual";
//                        timeDateJson = timeJSONObjectHandler(features, properties, propertyStr, timeDateJson);
                        timeDateJson1 = timeArrayJSONObjectHandler(features, properties, propertiesStr, timeDateJson);
                    }
                    ws = timeDateJson1.toString();
//                    ws = timeDateJson.toString();
                    System.out.println(ws);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
            bw.write(ws);
            bw.flush();
            br.close();
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
//  对每个国家的属性进行处理
    public static JSONObject timeLineGdpGeoJsonHandler(JSONObject dataJson, JSONObject timeLineJson, String propertyGDPStr){
//        遍历时间对象
        Set<String> timeKeySet = timeLineJson.keySet();
        for(String timeKey : timeKeySet){
            JSONArray timeObject = timeLineJson.getJSONArray(timeKey);
//            遍历时间对象里面的每个国家对象
            for(int i = 0; i < timeObject.length(); i++){
                JSONObject timeCountryObject = timeObject.getJSONObject(i);
//                通过id来进行匹配
                String timeCountryId = timeCountryObject.getString("id");
//                时间对象里面的properties属性
                JSONObject timeProperties = timeCountryObject.getJSONObject("properties");
//                遍历地理文件中国家对象
                JSONArray features = dataJson.getJSONArray("features");
                JSONArray timeGDPArray = new JSONArray();
                for(int j = 0; j < features.length(); j++){
//                    生成地理文件中国家对象
                    JSONObject country = features.getJSONObject(j);
                    String id = country.getString("id");
                    if(id.equals(timeCountryId)){
//                        地理文件中每个国家中的属性
                        JSONObject properties = country.getJSONObject("properties");
                        JSONArray gdpArray = properties.getJSONArray(propertyGDPStr);
                        for(int k = 0; k < gdpArray.length(); k++){
//                            gdp里面包含时间以及COMPONENT、ECONOMY
                            JSONObject gdpObject = gdpArray.getJSONObject(k);
                            Set<String> keySet = gdpObject.keySet();

                            for(String key : keySet){
                                if(key.equals("COMPONENT")){
                                    continue;
                                }
                                if(key.equals("ECONOMY")){
                                    continue;
                                }
                                if(key.equals(timeKey)){
                                    JSONObject timeGdpJson = new JSONObject();
                                    timeGdpJson.put("value", gdpObject.getString(timeKey));
                                    timeGdpJson.put("COMPONENT", gdpObject.getString("COMPONENT"));
                                    timeGdpJson.put("ECONOMY", gdpObject.getString("ECONOMY"));
                                    timeGDPArray.put(timeGdpJson);
                                    break;
                                }
                            }
                        }
                    }
                }
                timeProperties.put("Gross domestic product GDP by type of expenditure", timeGDPArray);
            }
        }
        return timeLineJson;
    }
    /*
     *timeLineGeoJson处理结果包含四部分：
     * 1.Foreign direct investment Outward flows stock, annual
     * 2.Total and urban population, annual
     * 3.Foreign direct investment Inward stock, annual
     * 4.Foreign direct investment Inward flows, annual
     * 在此基础上增加GDP Gross domestic product GDP by type of expenditure包含数组
     */
    public static void timeLineGdpGeoJson(File geoJsonFile){
        File baseJsonFile = new File("E:\\code\\timeLineGeoJson.geojson");
        String propertyGDPStr = "Gross domestic product GDP by type of expenditure";
        //br读取地理文件
        BufferedReader br;
        //brBase读取处理后的文件
        BufferedReader brBase;
        BufferedWriter bw;
        String ws = null;
        try {
            br = new BufferedReader(new FileReader(geoJsonFile.getPath()));
            brBase = new BufferedReader(new FileReader(baseJsonFile.getPath()));
            bw = new BufferedWriter(new FileWriter("E:\\code\\timeLineGeoJson_GDP.geojson"));
            String s = null, sBase = null;
            while((s = br.readLine()) != null&&(sBase = brBase.readLine()) != null){
                try{
//                  1.首先对地理文件中进行处理到properties
                    JSONObject dataJson = new JSONObject(s);
                    JSONObject timeLineJson = new JSONObject(sBase);
                    timeLineJson = timeLineGdpGeoJsonHandler(dataJson, timeLineJson, propertyGDPStr);
                    ws = timeLineJson.toString();
//                    System.out.println(ws);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
            bw.write(ws);
            bw.flush();
            brBase.close();
            br.close();
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static JSONObject induceGeoJsonHandler(JSONObject dataJson, JSONObject indexJson, String[] propertiesIndexStr){
        Set<String> timeKeySet = indexJson.keySet();
//      1.遍历时间文件中的时间对象 timeKey为时间点
        for(String timeKey : timeKeySet){
            JSONArray timeObject = indexJson.getJSONArray(timeKey);
//          2.处理时间对象里面的每个国家对象
            for(int i = 0; i < timeObject.length(); i++){
                JSONObject timeCountry = timeObject.getJSONObject(i);
                String timeCountryId = timeCountry.getString("id");
//              3.获取地理文件中的国家数组
                JSONArray features = dataJson.getJSONArray("features");
                for(int j = 0; j < features.length(); j++){
//                  4.遍历地理文件中的每个国家
                    JSONObject country = features.getJSONObject(j);
                    String countryId = country.getString("id");
                    if(countryId.equals(timeCountryId)){
                        JSONObject countryProperties = country.getJSONObject("properties");
                        for(int k = 0; k < propertiesIndexStr.length; k++){
//                          5.在地理文件中提取属性中的指标
                            String propertyIndexStr = propertiesIndexStr[k];
                            JSONObject indexObject = countryProperties.getJSONObject(propertyIndexStr);
//                          6.提取指标下的国家信息
                            Set<String> indexCountriesSet = indexObject.keySet();
//                          7.indexCountry为国家名称
                            for(String indexCountry : indexCountriesSet){
//                              8.提取时间为准的指标
                                JSONObject indexTimeObject = indexObject.getJSONObject(indexCountry);
                                Set<String> indexTimeSet = indexTimeObject.keySet();
//                              9.遍历地理文件中的时间对象 indexTime 为时间点
                                for(String indexTime : indexTimeSet){
                                    if(indexTime.equals(timeKey)){
//                                      10.地理文件中每个时间对应的对象
                                        JSONObject indexObjectdetail = indexTimeObject.getJSONObject(indexTime);
                                        JSONObject timeProperties = timeCountry.getJSONObject("properties");
                                        JSONObject tempCountry = new JSONObject();
                                        tempCountry.put(indexCountry, indexObjectdetail);
                                        timeProperties.put(propertyIndexStr, tempCountry);
                                    }
                                }
                            }

                        }

                    }
                }
            }
        }
        return indexJson;
    }
    /*
     *induceGeoJson：
     * 1.Foreign direct investment Outward flows stock, annual
     * 2.Total and urban population, annual
     * 3.Foreign direct investment Inward stock, annual
     * 4.Foreign direct investment Inward flows, annual
     * 5.Gross domestic product GDP by type of expenditure
     * 在此基础上增加indices of imports, annual、indices of exports, annual按照地域包含时间上的指标
     */
    public static void induceGeoJson(File geoFile){
        File baseJsonFile = new File("E:\\code\\timeLineGeoJson_GDP.geojson");
        String[] propertiesIndexStr = {"indices of imports, annual", "indices of exports, annual"};
        //brGeo读取地理文件
        BufferedReader brGeo;
        //brBase读取处理后的文件
        BufferedReader brBase;
        BufferedWriter bw;
        try {
            brGeo = new BufferedReader(new FileReader(geoFile.getPath()));
            brBase = new BufferedReader(new FileReader(baseJsonFile.getPath()));
            bw = new BufferedWriter(new FileWriter("E:\\code\\induceGeoJson.geojson"));
            String s = null, sBase = null;
            String ws = null;
            while ((s = brGeo.readLine()) != null&&(sBase = brBase.readLine()) != null){
                JSONObject dataJson = new JSONObject(s);
                JSONObject indexJson = new JSONObject(sBase);
                indexJson = induceGeoJsonHandler(dataJson, indexJson, propertiesIndexStr);
                ws = indexJson.toString();
                System.out.println(ws);
            }

            bw.write(ws);
            bw.flush();
            brBase.close();
            brGeo.close();
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
