package com.ISCAS.OneBeltOneRoad;

import com.ISCAS.OneBeltOneRoad.dao.Br.BrDao;
import com.ISCAS.OneBeltOneRoad.entity.br.BrImportProject;
import com.ISCAS.OneBeltOneRoad.util.JsonUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BrImportProjectDaoTest extends BaseTest {
    @Autowired
    private  BrDao brDao;
    @Test
    @Ignore
    public void selectBrImportProjectAllTest(){
        List<BrImportProject> brImportProjectList = brDao.selectBrImportProjectAll();
        BufferedReader br;
        BufferedWriter bw;
        String geoPath = "E:\\code\\countries.geojson";
        try{
            br = new BufferedReader(new FileReader(geoPath));
            bw = new BufferedWriter(new FileWriter("E:\\code\\BrImportProject.geojson"));
            String s = null, ws = null;
            while((s = br.readLine()) != null){
                try{
                    JSONObject dataJson = new JSONObject(s);
                    JSONArray dataFeatures = dataJson.getJSONArray("features");
                    //1.创建最外层Object包含type和features两个属性
                    JSONObject GeoJson = new JSONObject();
                    GeoJson.put("type", "FeatureCollection");
                    /*
                     * 2.构造features数组，该数组中包含每个国家的属性以及地理信息
                     *   其中属性来源于数据库、地理信息来源于GeoJson
                     */
                    JSONArray features = new JSONArray();
                    GeoJson.put("features", features);
                    for(BrImportProject brImportProject : brImportProjectList){
                        for(int i = 0;i < dataFeatures.length(); i++){
                            JSONObject countryDataFeature = dataFeatures.getJSONObject(i);
                            JSONObject dataProperties = countryDataFeature.getJSONObject("properties");
                            String dataComment = dataProperties.getString("_comment");
                            if(brImportProject.getNation().equals(dataComment)){
                                JSONObject feature = new JSONObject();
                                JSONObject properties = new JSONObject();
                                properties.put("nation", brImportProject.getNation());
                                properties.put("province", brImportProject.getProvince());
                                properties.put("city", brImportProject.getCity());
                                properties.put("coordinate", brImportProject.getCoordinate());
                                properties.put("project_name", brImportProject.getProjectName());
                                properties.put("project_type", brImportProject.getProjectType());
                                properties.put("project_introduction", brImportProject.getProjectIntroduction());
                                properties.put("thumbnail", brImportProject.getThumbnail());
                                properties.put("construction_status", brImportProject.getConstructionStatus());
                                properties.put("detail_image", brImportProject.getDetailImage());
                                properties.put("domestic_company", brImportProject.getDomesticCompany());
                                feature.put("properties", properties);
                                feature.put("geometry", countryDataFeature.getJSONObject("geometry"));
                                feature.put("type", countryDataFeature.getString("type"));
                                feature.put("id", countryDataFeature.getString("id"));
                                features.put(feature);
                            }
                        }
                    }
                    ws = GeoJson.toString();
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
    }
    @Test
    @Ignore
    public void selectBrImportProjectAllTestNormal(){
        List<BrImportProject> brImportProjectList = brDao.selectBrImportProjectAll();
        JSONArray layers = new JSONArray();
        JSONObject layerFeatures = new JSONObject();
        JSONObject geoData = new JSONObject();
        String ws = null;
        JSONArray layersItemArray = new JSONArray();
        for(BrImportProject brImportProject : brImportProjectList){
            JSONObject layerFeaturesItem = new JSONObject();
            JSONObject layerFeaturesItemGeometry = new JSONObject();
            JSONArray layerFeaturesItemGeometryCoordinates = new JSONArray();
            JSONArray layerFeaturesItemGeometryCoordinatesItem = new JSONArray();
            //1.构造layers
            JSONObject layersItem = new JSONObject();
            JSONObject layersItemSource = new JSONObject();
            layersItemSource.put("feature", (brImportProject.getId() - 1) + "");
            layersItem.put("source", layersItemSource);
            layersItem.put("type", "Vector");
            layersItemArray.put(layersItem);

            //2.构造layerFeatures
            String[] coordinate = brImportProject.getCoordinate().split(",");
            Float[] coordinatetoFloat = new Float[2];
            JSONObject properties = new JSONObject();
            coordinatetoFloat[1] = Float.parseFloat(coordinate[0]);
            coordinatetoFloat[0] = Float.parseFloat(coordinate[1]);
            layerFeaturesItemGeometry.put("coordinates", coordinatetoFloat);
            layerFeaturesItemGeometry.put("type", "Point");

            properties.put("nation", brImportProject.getNation());
            properties.put("province", brImportProject.getProvince());
            properties.put("city", brImportProject.getCity());
            properties.put("project_name", brImportProject.getProjectName());
            properties.put("project_type", brImportProject.getProjectType());
            properties.put("project_introduction", brImportProject.getProjectIntroduction());
            properties.put("thumbnail", brImportProject.getThumbnail());
            properties.put("construction_status", brImportProject.getConstructionStatus());
            properties.put("detail_image", brImportProject.getDetailImage());
            properties.put("domestic_company", brImportProject.getDomesticCompany());
            layerFeaturesItem.put("geometry", layerFeaturesItemGeometry);
            layerFeaturesItem.put("id", (brImportProject.getId() - 1) + "");
            layerFeaturesItem.put("type", "Feature");
            layerFeaturesItem.put("properties", properties);
            layerFeatures.put("" + (brImportProject.getId() - 1), layerFeaturesItem);
        }
        geoData.put("layerFeatures", layerFeatures);
        layers.put(layersItemArray);
        geoData.put("layers", layers);
        ws = geoData.toString();
        System.out.println(ws);
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\code\\important-projects1.json"));
            bw.write(ws);
            bw.flush();
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @Test
    public void selectBrImportCountryAllTest(){
        String excelPath = "E:\\项目\\一带一路\\一带一路国家_temp.xls";
        File excelFile = new File(excelPath);
        JsonUtil jsonUtil = new JsonUtil();
        List excelList = jsonUtil.readExcel(excelFile);
        BufferedReader br;
        BufferedWriter bw;
        String geoPath = "E:\\code\\countries.geojson";
        try {
            br = new BufferedReader(new FileReader(geoPath));
            bw = new BufferedWriter(new FileWriter("E:\\code\\BrImportCountry_1.json"));
            String s = null, ws = null;
            while((s = br.readLine()) != null){
                try {
                    JSONObject dataJson = new JSONObject(s);
                    JSONArray dataFeatures = dataJson.getJSONArray("features");
                    JSONArray layerData = new JSONArray();
                    JSONObject timeData = new JSONObject();
//                    sequence：时间轴每个时刻的信息构成的数组，属于TimeData的一部分
                    JSONArray sequence = new JSONArray();
                    JSONObject annotation = new JSONObject();
                    JSONObject layerFeaturesData = new JSONObject();
                    int nationIndex = 0;
                    int timeIndex = 0;
                    int timeIndex2 = 0;
                    int timeIndex3 = 0;
                    int signDocument = 0;
                    int infoSource = 0;
                    int signDocument2 = 0;
                    int infoSource2 = 0;
                    int signDocument3 = 0;
                    int infoSource3 = 0;
//                    excel中第一行为列标题
                    List titleList = (List)excelList.get(0);
//                    找到国家的列
                    for(int k = 0; k < titleList.size(); k++){
                        if(titleList.get(k).equals("国家")) {
                            nationIndex = k;
                        }
                        if(titleList.get(k).equals("时间")) {
                            timeIndex = k;
                        }
                        if(titleList.get(k).equals("签署文件")) {
                            signDocument = k;
                        }
                        if(titleList.get(k).equals("信息来源")) {
                            infoSource = k;
                        }
                        if(titleList.get(k).equals("时间2")) {
                            timeIndex2 = k;
                        }
                        if(titleList.get(k).equals("签署文件2")) {
                            signDocument2 = k;
                        }
                        if(titleList.get(k).equals("信息来源2")) {
                            infoSource2 = k;
                        }
                        if(titleList.get(k).equals("时间3")){
                            timeIndex3 = k;
                        }
                        if(titleList.get(k).equals("签署文件3")){
                            signDocument3 = k;
                        }
                        if(titleList.get(k).equals("信息来源3")){
                            infoSource3 = k;
                        }
                    }

                    for(int i = 1; i < excelList.size(); i++){
                        List list = (List)excelList.get(i);
                        JSONArray layerItem = new JSONArray();
                        JSONObject layerObjectItem = new JSONObject();
                        JSONObject layerObject = new JSONObject();
                        JSONObject layerFeaturesItem = new JSONObject();
                        for(int j  = 0; j < dataFeatures.length(); j++){
                            JSONObject dataFeature = dataFeatures.getJSONObject(j);
                            JSONObject properties =  dataFeature.getJSONObject("properties");
                            String dataComment = properties.getString("_comment");
                            if(list.get(nationIndex).equals(dataComment)){
                                layerObjectItem.put("feature", dataFeature.getString("id"));
                                layerObject.put("type", "Vector");
                                layerObject.put("source", layerObjectItem);
                                layerItem.put(layerObject);
                                layerFeaturesItem.put("type", "Feature");
                                layerFeaturesItem.put("id", dataFeature.getString("id"));
                                JSONObject layerFeaturesProperties = new JSONObject();
                                layerFeaturesProperties.put("name", properties.getString("name"));
                                layerFeaturesItem.put("properties", layerFeaturesProperties);
                                layerFeaturesItem.put("geometry", dataFeature.getJSONObject("geometry"));
                                layerFeaturesData.put(dataFeature.getString("id"), layerFeaturesItem);
                                String time = list.get(timeIndex).toString();
                                if(!time.equals("")){
                                    JSONObject timeItem = new JSONObject();
                                    timeItem.put("index", i - 1);
                                    timeItem.put("name", time);
                                    if(!list.get(signDocument).equals("")&&!list.get(infoSource).equals(""))
                                        timeItem.put("description", "一带一路加入国家：" + dataComment + "   签署文件:" + list.get(signDocument) + "    信息来源:" + list.get(infoSource));
                                    if(!list.get(signDocument).equals("")&&list.get(infoSource).equals(""))
                                        timeItem.put("description", "一带一路加入国家：" + dataComment + "   签署文件:" + list.get(signDocument));
                                    if(list.get(signDocument).equals("")&&!list.get(infoSource).equals(""))
                                        timeItem.put("description", "一带一路加入国家：" + dataComment + "   信息来源:" + list.get(infoSource));
                                    sequence.put(timeItem);
                                }
                                String time2 = list.get(timeIndex2).toString();
                                if(!time2.equals("")){
                                    JSONObject timeItem = new JSONObject();
                                    timeItem.put("index", i - 1);
                                    timeItem.put("name", time2);
                                    if(!list.get(signDocument2).equals("")&&!list.get(infoSource2).equals(""))
                                        timeItem.put("description", "一带一路加入国家：" + dataComment + "   签署文件:" + list.get(signDocument2) + "    信息来源:" + list.get(infoSource2));
                                    if(!list.get(signDocument2).equals("")&&list.get(infoSource2).equals(""))
                                        timeItem.put("description", "一带一路加入国家：" + dataComment + "   签署文件:" + list.get(signDocument2));
                                    if(list.get(signDocument2).equals("")&&!list.get(infoSource2).equals(""))
                                        timeItem.put("description", "一带一路加入国家：" + dataComment + "   信息来源:" + list.get(infoSource2));
                                    sequence.put(timeItem);
                                }
                                String time3 = list.get(timeIndex3).toString();
                                if(!time3.equals("")){
                                    JSONObject timeItem = new JSONObject();
                                    timeItem.put("index", i - 1);
                                    timeItem.put("name", time3);
                                    if(!list.get(signDocument3).equals("")&&!list.get(infoSource3).equals(""))
                                        timeItem.put("description", "一带一路加入国家：" + dataComment + "   签署文件:" + list.get(signDocument3) + "    信息来源:" + list.get(infoSource3));
                                    if(!list.get(signDocument3).equals("")&&list.get(infoSource3).equals(""))
                                        timeItem.put("description", "一带一路加入国家：" + dataComment + "   签署文件:" + list.get(signDocument3));
                                    if(list.get(signDocument3).equals("")&&!list.get(infoSource3).equals(""))
                                        timeItem.put("description", "一带一路加入国家：" + dataComment + "   信息来源:" + list.get(infoSource3));
                                    sequence.put(timeItem);
                                }
                                break;
                            }
                        }
                        layerData.put(layerItem);
                    }
                    timeData.put("type", "index");
                    timeData.put("caption", "一带一路时间");
                    timeData.put("initTime", 0);
                    //对sequence进行排序
                    sequence = jsonArraySort(sequence);
                    timeData.put("sequence", sequence);
                    annotation.put("layers", layerData);
                    annotation.put("time", timeData);
                    annotation.put("layerFeatures", layerFeaturesData);
                    ws = annotation.toString();
                    System.out.println(ws);
                }
                catch (JSONException e){
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
    }
    public static JSONArray jsonArraySort(JSONArray sequence){
        List<JSONObject> jsonObjectList = new ArrayList<JSONObject>();
        for(int i = 0; i < sequence.length(); i++)
            jsonObjectList.add(sequence.getJSONObject(i));
        Collections.sort(jsonObjectList, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                String date1 = o1.getString("name");
                String date2 = o2.getString("name");
                return date1.compareTo(date2);
            }
        });
        sequence = new JSONArray();
        for(JSONObject jsonObject :jsonObjectList){
            sequence.put(jsonObject);
        }
        return sequence;
    }
}
