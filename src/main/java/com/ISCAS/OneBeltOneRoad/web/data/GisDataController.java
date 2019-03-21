package com.ISCAS.OneBeltOneRoad.web.data;

import com.ISCAS.OneBeltOneRoad.dao.GisDataDao;
import com.ISCAS.OneBeltOneRoad.entity.br.BrAnnotationData;
import com.ISCAS.OneBeltOneRoad.entity.data.Echarts.EchartsItem;
import com.ISCAS.OneBeltOneRoad.entity.data.GeoJsonItem.GeoJsonItem;
import com.ISCAS.OneBeltOneRoad.entity.data.OsmItem.OsmItem;
import com.ISCAS.OneBeltOneRoad.entity.data.WmsItem.WmsItem;
import com.ISCAS.OneBeltOneRoad.entity.data.chart.AnnotationDataChart;
import com.ISCAS.OneBeltOneRoad.entity.data.AnnotationData;
import com.ISCAS.OneBeltOneRoad.entity.data.time.AnnotationTimeData;
import com.ISCAS.OneBeltOneRoad.util.GisUtil;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api/data")
public class GisDataController {
    @Autowired
    GisDataDao gisDataDao;
//    time、style、layers
    @RequestMapping(value = "/{paratemeterName}")
    @ResponseBody
    Map<String, Object> annotationdataController(HttpServletRequest request, @PathVariable String paratemeterName){
        Map<String, Object> modelMap = new HashMap<>();
        BrAnnotationData brAnnotationData = gisDataDao.queryBrAnnotationData(paratemeterName);

        AnnotationData annotationData = new AnnotationData();
        if(brAnnotationData.getLayerFeatures() != null)
            annotationData.setLayerFeatures(brAnnotationData.getLayerFeatures().toString());
//        处理time
        if(brAnnotationData.getTime() != null){
            JSONObject jsonObject = new JSONObject(brAnnotationData.getTime().toString());
            if(jsonObject.getString("type").equals("index")){
                AnnotationTimeData annotationTimeData = GisUtil.annotationTimeDataUtil(jsonObject);
                annotationData.setTime(annotationTimeData);
            }
        }
//        处理style
        if(brAnnotationData.getStyle() != null) {
            JSONObject jsonObject = new JSONObject(brAnnotationData.getStyle().toString());
            Object Style = GisUtil.annotationStyleUtil(jsonObject);
            annotationData.setStyle(Style);
        }
//        处理chart
        if(brAnnotationData.getChart() != null){
            JSONObject jsonObject = new JSONObject(brAnnotationData.getChart());
            AnnotationDataChart annotationDataChart = GisUtil.annotationDataChartUtil(jsonObject);
            annotationData.setChart(annotationDataChart);
        }
//        KeyPoint由于缺少数据尚未开发
//        if(brAnnotationDataannotationData.getKeyPoint() != null)
//            annotationData.setKeyPoint(brAnnotationDataannotationData.getKeyPoint().toString());
//        处理layers
        if(brAnnotationData.getLayers() != null){
            JSONObject jsonObject = new JSONObject(brAnnotationData.getLayers());
            JSONArray jsonArray = new JSONArray(jsonObject.getString("value"));
            Object[][] layers = new Object[jsonArray.length()][];
            for(int i = 0; i < jsonArray.length(); i++){
                JSONArray jsonArray1 = jsonArray.getJSONArray(i);
                layers[i] = new Object[jsonArray1.length()];
                for(int j = 0; j < jsonArray1.length(); j++){
                    JSONObject brEchartItem = jsonArray1.getJSONObject(j);
                    if(brEchartItem.has("type")){
                        if(brEchartItem.getString("type").equals("Echarts")){
                            EchartsItem echartsItem = GisUtil.echartsItemHandler(brEchartItem);
                            layers[i][j] = echartsItem;
                        }
                        else if(brEchartItem.getString("type").equals("Vector")||brEchartItem.getString("type").equals("EmbeddedChart")){
                            GeoJsonItem geoJsonItem = GisUtil.geoJsonItemHandler(brEchartItem);
                            layers[i][j] = geoJsonItem;
                        }
                        else if(brEchartItem.getString("type").equals("Tile")||brEchartItem.getString("type").equals("Image")) {
                            WmsItem wmsItem = GisUtil.wmsItemHandler(brEchartItem);
                            layers[i][j] = wmsItem;
                        }
                        else if(brEchartItem.getString("type").equals("Osm")){
                            OsmItem osmItem = new OsmItem();
                            osmItem.setType(brEchartItem.getString("type"));
                            layers[i][j] = osmItem;
                        }
                    }
                }
            }
            annotationData.setLayers(layers);
        }
//      处理layerFeatures
        if(brAnnotationData.getLayerFeatures() != null){
            JSONObject jsonObject = new JSONObject(brAnnotationData.getLayerFeatures());
            JSONObject jsonObjectValue = new JSONObject(jsonObject.getString("value"));
            Map<String, Object> layerFeaturesItem = GisUtil.layerFeaturesHandler(jsonObjectValue);
            annotationData.setLayerFeatures(layerFeaturesItem);
        }
        modelMap.put("name", paratemeterName);
        modelMap.put("data", annotationData);
        return modelMap;
    }


}
