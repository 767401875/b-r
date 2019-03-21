package com.ISCAS.OneBeltOneRoad.util;

import com.ISCAS.OneBeltOneRoad.entity.data.WmsItem.WmsItem;
import com.ISCAS.OneBeltOneRoad.entity.data.WmsItem.WmsItemSource;
import com.ISCAS.OneBeltOneRoad.entity.data.WmsItem.WmsItemSourceParams;
import com.ISCAS.OneBeltOneRoad.entity.data.Echarts.*;
import com.ISCAS.OneBeltOneRoad.entity.data.GeoJsonItem.*;
import com.ISCAS.OneBeltOneRoad.entity.data.chart.*;
import com.ISCAS.OneBeltOneRoad.entity.data.style.*;
import com.ISCAS.OneBeltOneRoad.entity.data.time.AnnotationTimeData;
import com.ISCAS.OneBeltOneRoad.entity.data.time.AnnotationTimeDataSequence;
import com.ISCAS.OneBeltOneRoad.entity.data.layerFeatures.LayerFeatures;
import com.ISCAS.OneBeltOneRoad.entity.data.layerFeatures.LayerFeaturesGeometry;
import com.ISCAS.OneBeltOneRoad.entity.data.layerFeatures.LayerFeaturesProperties;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GisUtil {
    public static AnnotationTimeData annotationTimeDataUtil(JSONObject jsonObject){
        AnnotationTimeData annotationTimeData = new AnnotationTimeData();
        annotationTimeData.setType(jsonObject.getString("type"));
        annotationTimeData.setCaption(jsonObject.getString("caption"));
        annotationTimeData.setInitTime(jsonObject.getInt("initTime"));
        JSONArray jsonArray = jsonObject.getJSONArray("sequence");
        AnnotationTimeDataSequence[] annotationTimeDataSequences = new AnnotationTimeDataSequence[jsonArray.length()];
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject item = jsonArray.getJSONObject(i);
            AnnotationTimeDataSequence annotationTimeDataSequence = new AnnotationTimeDataSequence();
            annotationTimeDataSequence.setIndex(item.getInt("index"));
            annotationTimeDataSequence.setCaption(item.getString("caption"));
            annotationTimeDataSequence.setDescription(item.getString("description"));
            annotationTimeDataSequences[i] = annotationTimeDataSequence;
        }
        annotationTimeData.setSequence(annotationTimeDataSequences);
        return annotationTimeData;
    }

    public static Object annotationStyleUtil(JSONObject jsonObject){
        if(jsonObject.getString("type").equals("curTime")){
            CurTimeStyle curTimeStyle = new CurTimeStyle();
            StyleDef curTime = new StyleDef();
            StyleDef Default = new StyleDef();
            FillDef fillDef = new FillDef();
            StrokeDef strokeDef = new StrokeDef();
            //curTime
            if(jsonObject.has("curTime")){
                if(jsonObject.getJSONObject("curTime").has("fill")){
                    if(jsonObject.getJSONObject("curTime").getJSONObject("fill").has("color"))
                        fillDef.setColor(jsonObject.getJSONObject("curTime").getJSONObject("fill").getString("color"));
                    if(jsonObject.getJSONObject("curTime").getJSONObject("fill").has("opacity"))
                        fillDef.setOpacity(jsonObject.getJSONObject("curTime").getJSONObject("fill").getDouble("opacity"));
                }
                if(jsonObject.getJSONObject("curTime").has("stroke")){
                    if(jsonObject.getJSONObject("curTime").getJSONObject("stroke").has("color"))
                        strokeDef.setColor(jsonObject.getJSONObject("curTime").getJSONObject("stroke").getString("color"));
                    if(jsonObject.getJSONObject("curTime").getJSONObject("stroke").has("opacity"))
                        strokeDef.setOpacity(jsonObject.getJSONObject("curTime").getJSONObject("stroke").getDouble("opacity"));
                    if(jsonObject.getJSONObject("curTime").getJSONObject("stroke").has("width"))
                        strokeDef.setWidth(jsonObject.getJSONObject("curTime").getJSONObject("stroke").getInt("width"));
                    if(jsonObject.getJSONObject("curTime").getJSONObject("stroke").has("zIndex"))
                        strokeDef.setzIndex(jsonObject.getJSONObject("curTime").getJSONObject("stroke").getInt("zIndex"));
                }
            }
            if(fillDef != null)
                curTime.setFill(fillDef);
            if(strokeDef != null)
                curTime.setStroke(strokeDef);
            if(jsonObject.has("curTime")&& jsonObject.getJSONObject("curTime").has("pointRadius"))
                curTime.setPointRadius(jsonObject.getJSONObject("curTime").getInt("pointRadius"));
            //Default
            fillDef = new FillDef();
            strokeDef = new StrokeDef();
            if(jsonObject.has("default")){
                if(jsonObject.getJSONObject("default").has("fill")){
                    if (jsonObject.getJSONObject("default").getJSONObject("fill").has("color"))
                        fillDef.setColor(jsonObject.getJSONObject("default").getJSONObject("fill").getString("color"));
                    if(jsonObject.getJSONObject("default").getJSONObject("fill").has("opacity"))
                        fillDef.setOpacity(jsonObject.getJSONObject("default").getJSONObject("fill").getDouble("opacity"));
                }
                if(jsonObject.getJSONObject("default").has("stroke")){
                    if(jsonObject.getJSONObject("default").getJSONObject("stroke").has("color"))
                        strokeDef.setColor(jsonObject.getJSONObject("default").getJSONObject("stroke").getString("color"));
                    if(jsonObject.getJSONObject("default").getJSONObject("stroke").has("opacity"))
                        strokeDef.setOpacity(jsonObject.getJSONObject("default").getJSONObject("stroke").getDouble("opacity"));
                    if(jsonObject.getJSONObject("default").getJSONObject("stroke").has("width"))
                        strokeDef.setWidth(jsonObject.getJSONObject("default").getJSONObject("stroke").getInt("width"));
                    if(jsonObject.getJSONObject("default").getJSONObject("stroke").has("zIndex"))
                        strokeDef.setzIndex(jsonObject.getJSONObject("default").getJSONObject("stroke").getInt("zIndex"));
                }
            }
            if(fillDef != null)
                Default.setFill(fillDef);
            if(strokeDef != null)
                Default.setStroke(strokeDef);
            if(jsonObject.getJSONObject("default") != null&& jsonObject.getJSONObject("default").has("pointRadius"))
                Default.setPointRadius(jsonObject.getJSONObject("default").getInt("pointRadius"));
            curTimeStyle.setType(jsonObject.getString("type"));
            curTimeStyle.setCaption(jsonObject.getString("caption"));
            curTimeStyle.setCurTime(curTime);
            curTimeStyle.setDefault(Default);
            return curTimeStyle;
        }
        if(jsonObject.getString("type").equals("static")){
            StaticStyle staticStyle = new StaticStyle();
            StyleDef Static = new StyleDef();
            StrokeDef strokeDef = new StrokeDef();
            FillDef fillDef = new FillDef();
            if(jsonObject.has("static")){
                if(jsonObject.getJSONObject("static").has("fill")){
                    if(jsonObject.getJSONObject("static").getJSONObject("fill").has("color"))
                        fillDef.setColor(jsonObject.getJSONObject("static").getJSONObject("fill").getString("color"));
                    if(jsonObject.getJSONObject("static").getJSONObject("fill").has("opacity"))
                        fillDef.setOpacity(jsonObject.getJSONObject("static").getJSONObject("fill").getDouble("opacity"));
                    if(jsonObject.getJSONObject("static").getJSONObject("fill").has("zIndex"))
                        fillDef.setzIndex(jsonObject.getJSONObject("static").getJSONObject("fill").getInt("zIndex"));
                }
                if(jsonObject.getJSONObject("static").has("stroke")){
                    if(jsonObject.getJSONObject("static").getJSONObject("stroke").has("color"))
                        strokeDef.setColor(jsonObject.getJSONObject("static").getJSONObject("stroke").getString("color"));
                    if(jsonObject.getJSONObject("static").getJSONObject("stroke").has("opacity"))
                        strokeDef.setOpacity(jsonObject.getJSONObject("static").getJSONObject("stroke").getDouble("opacity"));
                    if(jsonObject.getJSONObject("static").getJSONObject("stroke").has("zIndex"))
                        strokeDef.setzIndex(jsonObject.getJSONObject("static").getJSONObject("stroke").getInt("zIndex"));
                    if(jsonObject.getJSONObject("static").getJSONObject("stroke").has("width"))
                        strokeDef.setWidth(jsonObject.getJSONObject("static").getJSONObject("stroke").getInt("width"));
                }
            }
            if(fillDef != null)
                Static.setFill(fillDef);
            if(strokeDef != null)
                Static.setStroke(strokeDef);
            if(jsonObject.getJSONObject("static") != null&& jsonObject.getJSONObject("static").has("pointRadius"))
                Static.setPointRadius(jsonObject.getJSONObject("static").getInt("pointRadius"));
            staticStyle.setStatic(Static);
            if(jsonObject.has("caption"))
                staticStyle.setCaption(jsonObject.getString("caption"));
            if(jsonObject.has("type"))
                staticStyle.setType(jsonObject.getString("type"));
            return staticStyle;
        }
        return null;
    }
    public static AnnotationDataChart annotationDataChartUtil(JSONObject jsonObject){
        AnnotationDataChart annotationDataChart = new AnnotationDataChart();
        AnnotationDataChartOptions annotationDataChartOptions = new AnnotationDataChartOptions();
        AnnotationDataChartOptionsX annotationDataChartOptionsX = new AnnotationDataChartOptionsX();
        AnnotationDataChartOptionsY annotationDataChartOptionsY = new AnnotationDataChartOptionsY();
        AnnotationDataChartOptionsSeries[] annotationDataChartOptionsSeries = null;

        JSONArray dataJsonArray = new JSONArray();
        if(jsonObject.has("value")){
            jsonObject = new JSONObject(jsonObject.getString("value"));
            if(jsonObject.has("options")){
                if(jsonObject.getJSONObject("options").has("xAxis")){
                    if(jsonObject.getJSONObject("options").getJSONObject("xAxis").has("type"))
                        annotationDataChartOptionsX.setType(jsonObject.getJSONObject("options").getJSONObject("xAxis").getString("type"));
                    if(jsonObject.getJSONObject("options").getJSONObject("xAxis").has("data")){
                        dataJsonArray = jsonObject.getJSONObject("options").getJSONObject("xAxis").getJSONArray("data");
                        String[] data = new String[dataJsonArray.length()];
                        for(int i = 0; i < dataJsonArray.length(); i++){
                            data[i] = dataJsonArray.get(i).toString();
                        }
                        annotationDataChartOptionsX.setData(data);
                    }
                }
                if(jsonObject.getJSONObject("options").has("yAxis")){
                    if(jsonObject.getJSONObject("options").getJSONObject("yAxis").has("type"))
                        annotationDataChartOptionsY.setType(jsonObject.getJSONObject("options").getJSONObject("yAxis").getString("type"));
                }
                if(jsonObject.getJSONObject("options").has("series")){
                    dataJsonArray = jsonObject.getJSONObject("options").getJSONArray("series");
                    String[] data = null;
                    annotationDataChartOptionsSeries = new AnnotationDataChartOptionsSeries[dataJsonArray.length()];
                    for(int i = 0; i < dataJsonArray.length(); i++){
                        JSONObject seriesItem = dataJsonArray.getJSONObject(i);
                        JSONArray seriesItemData = seriesItem.getJSONArray("data");
                        AnnotationDataChartOptionsSeries item = new AnnotationDataChartOptionsSeries();
                        data = new String[seriesItemData.length()];
                        for(int j = 0; j < seriesItemData.length(); j++){
                            data[j] = seriesItemData.get(j).toString();
                        }
                        item.setData(data);
                        item.setType(seriesItem.getString("type"));
                        annotationDataChartOptionsSeries[i] = item;
                    }
                }

            }
        }
        annotationDataChartOptions.setxAxis(annotationDataChartOptionsX);
        annotationDataChartOptions.setyAxis(annotationDataChartOptionsY);
        annotationDataChartOptions.setSeries(annotationDataChartOptionsSeries);
        annotationDataChart.setOptions(annotationDataChartOptions);
        annotationDataChart.setCaption(jsonObject.getString("caption"));
        return annotationDataChart;
    }
    public static EchartsLineStyle echartsLineStyleHandler(JSONObject brLineStyle){
        EchartsLineStyle lineStyle = new EchartsLineStyle();
        if(brLineStyle.has("normal")){
            EchartsLineStyleNormal normal = new EchartsLineStyleNormal();
            JSONObject brLineStyleNormal = brLineStyle.getJSONObject("normal");
            if(brLineStyleNormal.has("color"))
                normal.setColor(brLineStyleNormal.getString("color"));
            if(brLineStyleNormal.has("width"))
                normal.setWidth(brLineStyleNormal.getDouble("width"));
            if(brLineStyleNormal.has("opacity"))
                normal.setOpacity(brLineStyleNormal.getDouble("opacity"));
            if(brLineStyleNormal.has("curveness"))
                normal.setCurveness(brLineStyleNormal.getDouble("curveness"));
            lineStyle.setNormal(normal);
        }
        return lineStyle;
    }
    public static EchartsItem echartsItemHandler(JSONObject brEchartItem){
        EchartsItem echartsItem = new EchartsItem();
        echartsItem.setType(brEchartItem.getString("type"));
        EchartsData echartsData = new EchartsData();
        JSONObject brEchartsData = brEchartItem.getJSONObject("data");
        JSONArray brSeries = brEchartsData.getJSONArray("series");
        EchartsSeries[] series = new EchartsSeries[brSeries.length()];
        for(int k = 0; k < brSeries.length(); k++){
            JSONObject brSeriesItem = brSeries.getJSONObject(k);
            JSONArray brSeriesData = brSeriesItem.getJSONArray("data");
            EchartsSeriesData[] data = new EchartsSeriesData[brSeriesData.length()];
            for(int m = 0; m < brSeriesData.length(); m++){
                JSONObject brSeriesDataItem = brSeriesData.getJSONObject(m);
                JSONArray brSeriesDataItemCoords = brSeriesDataItem.getJSONArray("coords");
                EchartsSeriesData dataItem = new EchartsSeriesData();
                Double[][] coords = new Double[brSeriesDataItemCoords.length()][];
                for(int x = 0; x < brSeriesDataItemCoords.length(); x++){
                    JSONArray brSeriesDataItemCoordsX = brSeriesDataItemCoords.getJSONArray(x);
                    coords[x] = new Double[brSeriesDataItemCoordsX.length()];
                    for(int y = 0; y < brSeriesDataItemCoordsX.length(); y++){
                        coords[x][y] = brSeriesDataItemCoordsX.getDouble(y);
                    }
                }
                dataItem.setCoords(coords);
                if(brSeriesDataItem.has("fromName"))
                    dataItem.setFromName(brSeriesDataItem.getString("fromName"));
                if(brSeriesDataItem.has("toName"))
                    dataItem.setToName(brSeriesDataItem.getString("toName"));
                if(brSeriesDataItem.has("lineStyle")){
                    JSONObject brLineStyle = brSeriesDataItem.getJSONObject("lineStyle");
                    EchartsLineStyle lineStyle = echartsLineStyleHandler(brLineStyle);
                    dataItem.setLineStyle(lineStyle);
                }
                data[m] = dataItem;
            }
            EchartsSeries seriesItem = new EchartsSeries();
            seriesItem.setData(data);

            if(brSeriesItem.has("name"))
                seriesItem.setName(brSeriesItem.getString("name"));
            if(brSeriesItem.has("type"))
                seriesItem.setType(brSeriesItem.getString("type"));
            if(brSeriesItem.has("effect")){
                EchartsSeriesEffect effect = new EchartsSeriesEffect();
                JSONObject brEffect = brSeriesItem.getJSONObject("effect");
                if(brEffect.has("show"))
                    effect.setShow(brEffect.getBoolean("show"));
                if(brEffect.has("color"))
                    effect.setColor(brEffect.getString("color"));
                if(brEffect.has("period"))
                    effect.setPeriod(brEffect.getDouble("period"));
                if(brEffect.has("symbol"))
                    effect.setSymbol(brEffect.getString("symbol"));
                if(brEffect.has("symbolSize"))
                    effect.setSymbolSize(brEffect.getDouble("symbolSize"));
                if(brEffect.has("trailLength"))
                    effect.setTrailLength(brEffect.getDouble("trailLength"));
                if(brEffect.has("constantSpeed"))
                    effect.setConstantSpeed(brEffect.getDouble("constantSpeed"));
                seriesItem.setEffect(effect);
            }
            if(brSeriesItem.has("zlevel"))
                seriesItem.setZlevel(brSeriesItem.getInt("zlevel"));
            if(brSeriesItem.has("lineStyle")){
                JSONObject brLineStyle = brSeriesItem.getJSONObject("lineStyle");
                EchartsLineStyle lineStyle = echartsLineStyleHandler(brLineStyle);
                seriesItem.setLineStyle(lineStyle);
            }
            if(brSeriesItem.has("progressive"))
                seriesItem.setProgressive(brSeriesItem.getInt("progressive"));
            if(brSeriesItem.has("coordinateSystem"))
                seriesItem.setCoordinateSystem(brSeriesItem.getString("coordinateSystem"));
            if(brSeriesItem.has("progressiveThreshold"))
                seriesItem.setProgressiveThreshold(brSeriesItem.getInt("progressiveThreshold"));
            if(brSeriesItem.has("symbolSize"))
                seriesItem.setSymbolSize(brSeriesItem.getDouble("symbolSize"));
            if(brSeriesItem.has("silent"))
                seriesItem.setSilent(brSeriesItem.getBoolean("silent"));
            if(brSeriesItem.has("polyline"))
                seriesItem.setPolyline(brSeriesItem.getBoolean("polyline"));
            series[k] = seriesItem;
        }
        echartsData.setSeries(series);
        if(brEchartsData.has("tooltip")){
            JSONObject tooltipObject = brEchartsData.getJSONObject("tooltip");
            EchartsToolTip toolTip = new EchartsToolTip();
            toolTip.setTrigger(tooltipObject.getString("trigger"));
            echartsData.setTooltip(toolTip);
        }
        echartsItem.setData(echartsData);
        return echartsItem;
    }
    public static WmsItem wmsItemHandler(JSONObject brEchartItem){
        WmsItem wmsItem = new WmsItem();
        WmsItemSource source = new WmsItemSource();
        WmsItemSourceParams params = new WmsItemSourceParams();
        wmsItem.setType(brEchartItem.getString("type"));
        if(brEchartItem.has("source")){
            JSONObject brWmsSource = brEchartItem.getJSONObject("source");
            if(brWmsSource.has("params")){
                JSONObject brWmsSourceParams = brWmsSource.getJSONObject("params");
                if(brWmsSourceParams.has("layers"))
                    params.setLayers(brWmsSourceParams.getString("layers"));
                if(brWmsSourceParams.has("LAYERS"))
                    params.setLayers(brWmsSourceParams.getString("LAYERS"));
                if(brWmsSourceParams.has("styles"))
                    params.setStyles(brWmsSourceParams.getString("styles"));
                if(brWmsSourceParams.has("STYLES"))
                    params.setStyles(brWmsSourceParams.getString("STYLES"));
                source.setParams(params);
            }
            if(brWmsSource.has("url"))
                source.setUrl(brWmsSource.getString("url"));
            if(brWmsSource.has("serverType"))
                source.setServerType(brWmsSource.getString("serverType"));
        }
        wmsItem.setType(brEchartItem.getString("type"));
        wmsItem.setSource(source);
        return wmsItem;
    }
    public static GeoJsonItem geoJsonItemHandler(JSONObject brEchartItem){
        GeoJsonItem geoJsonItem = new GeoJsonItem();
        GeoJsonItemSource source = new GeoJsonItemSource();
        geoJsonItem.setType(brEchartItem.getString("type"));
        if(brEchartItem.has("source")){
            JSONObject brEchartItemSource = brEchartItem.getJSONObject("source");
            //feature对应一个字符串或者一个GeoJSON对象
            if(brEchartItemSource.has("feature")){
                if(brEchartItemSource.optString("feature") instanceof String){
                    //如果feature为一个对象
                    if(brEchartItemSource.optJSONObject("feature") instanceof  JSONObject){
                        GeoJsonFeature feature = new GeoJsonFeature();
                        JSONObject brFeature = brEchartItemSource.getJSONObject("feature");
                        feature.setType(brFeature.getString("type"));
                        JSONArray brFeatures = brFeature.getJSONArray("features");
                        GeoJsonFeatureItems[] features = new GeoJsonFeatureItems[brFeatures.length()];
                        for(int m = 0; m < brFeatures.length(); m++){
                            JSONObject brFeaturesItem = brFeatures.getJSONObject(m);
                            GeoJsonFeatureItems featuresItem = new GeoJsonFeatureItems();
                            featuresItem.setId(brFeaturesItem.getString("id"));
                            featuresItem.setType(brFeaturesItem.getString("type"));
                            GeoJsonFeatureItemsGeometry geometry = new GeoJsonFeatureItemsGeometry();
                            JSONObject brGeometry = brFeaturesItem.getJSONObject("geometry");

                            //Polygon三维数组
                            if(brGeometry.getString("type").equals("Polygon")){
                                JSONArray brCoordinates = brGeometry.getJSONArray("coordinates");
                                Double[][][] coordinates = new Double[brCoordinates.length()][][];
                                for(int n = 0; n < brCoordinates.length(); n++){
                                    JSONArray coordinatesItem = brCoordinates.getJSONArray(n);
                                    coordinates[n] = new Double[coordinatesItem.length()][];
                                    for(int x = 0; x < coordinatesItem.length(); x++){
                                        JSONArray brCoordinatesX = coordinatesItem.getJSONArray(x);
                                        coordinates[n][x] = new Double[brCoordinatesX.length()];
                                        for(int y = 0; y < brCoordinatesX.length(); y++){
                                            coordinates[n][x][y] = brCoordinatesX.getDouble(y);
                                        }
                                    }
                                }
                                geometry.setCoordinates(coordinates);
                            }
                            //MultiPolygon四维数组
                            if(brGeometry.getString("type").equals("MultiPolygon")){
                                JSONArray brCoordinates = brGeometry.getJSONArray("coordinates");
                                Double[][][][] coordinates = new Double[brCoordinates.length()][][][];
                                for(int n = 0; n < brCoordinates.length(); n++){
                                    JSONArray coordinatesN = brCoordinates.getJSONArray(n);
                                    coordinates[n] = new Double[coordinatesN.length()][][];
                                    for(int k = 0; k < coordinatesN.length(); k++){
                                        JSONArray coordinatesK = coordinatesN.getJSONArray(k);
                                        coordinates[n][k] = new Double[coordinatesK.length()][];
                                        for(int x = 0; x < coordinatesK.length(); x++){
                                            JSONArray coordinatesX = coordinatesK.getJSONArray(x);
                                            coordinates[n][k][x] = new Double[coordinatesX.length()];
                                            for(int y = 0; y < coordinatesX.length(); y++){
                                                coordinates[n][k][x][y] = coordinatesX.getDouble(y);
                                            }
                                        }
                                    }
                                }
                                geometry.setCoordinates(coordinates);
                            }

                            geometry.setType(brGeometry.getString("type"));
                            featuresItem.setGeometry(geometry);
                            GeoJsonFeatureItemsProperties properties = new GeoJsonFeatureItemsProperties();
                            JSONObject brProperties = brFeaturesItem.getJSONObject("properties");
                            properties.setName(brProperties.getString("name"));
                            featuresItem.setProperties(properties);
                            features[m] = featuresItem;
                        }
                        feature.setFeatures(features);
                        source.setFeature(feature);
                    }
                    //如果feature为一个字符串
                    else{
                        String s = brEchartItemSource.getString("feature");
                        source.setFeature(s);
                    }

                }
            }
        }
        geoJsonItem.setSource(source);
        return geoJsonItem;
    }
    public static Map<String, Object> layerFeaturesHandler(JSONObject jsonObjectValue){
        Map<String, Object> layerFeaturesItem = new HashMap<>();
        for (String key : jsonObjectValue.keySet()){
            JSONObject item = jsonObjectValue.getJSONObject(key);
            LayerFeatures layerFeatures = new LayerFeatures();
            if(item.has("id"))
                layerFeatures.setId(item.getString("id"));
            if(item.has("type"))
                layerFeatures.setType(item.getString("type"));
            if(item.has("geometry")){
                JSONObject geometry = item.getJSONObject("geometry");
                LayerFeaturesGeometry layerFeaturesGeometry = new LayerFeaturesGeometry();
                /*
                  type表示坐标类型 coordinates坐标点
                  type:1.Point(2维) 2.Polygon(3维) 3.MultiPolygon(4维)
                 */
                if(geometry.has("type")&&geometry.has("coordinates")){
                    JSONArray brCoordinates = geometry.getJSONArray("coordinates");
                    if(geometry.getString("type").equals("Point")){
                        Double[] coordinates = new Double[brCoordinates.length()];
                        for(int y = 0; y < brCoordinates.length(); y++){
                            coordinates[y] = brCoordinates.getDouble(y);
                        }
                        layerFeaturesGeometry.setCoordinates(coordinates);
                    }
                    if(geometry.getString("type").equals("Polygon")) {
                        Double[][][] coordinates = new Double[brCoordinates.length()][][];
                        for(int n = 0; n < brCoordinates.length(); n++){
                            JSONArray brCoordinatesN = brCoordinates.getJSONArray(n);
                            coordinates[n] = new Double[brCoordinatesN.length()][];
                            for(int x = 0; x < brCoordinatesN.length(); x++){
                                JSONArray brCoordinatesX = brCoordinatesN.getJSONArray(x);
                                coordinates[n][x] = new Double[brCoordinatesX.length()];
                                for(int y = 0; y < brCoordinatesX.length(); y++){
                                    coordinates[n][x][y] = brCoordinatesX.getDouble(y);
                                }
                            }
                        }
                        layerFeaturesGeometry.setCoordinates(coordinates);
                    }
                    if(geometry.getString("type").equals("MultiPolygon")) {
                        Double[][][][] coordinates = new Double[brCoordinates.length()][][][];
                        for(int m = 0; m < brCoordinates.length(); m++){
                            JSONArray brCoordinatesM = brCoordinates.getJSONArray(m);
                            coordinates[m] = new Double[brCoordinatesM.length()][][];
                            for(int n = 0; n < brCoordinatesM.length(); n++){
                                JSONArray brCoordinatesN = brCoordinatesM.getJSONArray(n);
                                coordinates[m][n] = new Double[brCoordinatesN.length()][];
                                for(int x = 0; x < brCoordinatesN.length(); x++){
                                    JSONArray brCoordinatesX = brCoordinatesN.getJSONArray(x);
                                    coordinates[m][n][x] = new Double[brCoordinatesX.length()];
                                    for(int y = 0; y < brCoordinatesX.length(); y++){
                                        coordinates[m][n][x][y] = brCoordinatesX.getDouble(y);
                                    }
                                }
                            }

                        }
                        layerFeaturesGeometry.setCoordinates(coordinates);
                    }
                    layerFeaturesGeometry.setType(geometry.getString("type"));
                    layerFeatures.setGeometry(layerFeaturesGeometry);
                }
            }
            if(item.has("properties")){
                JSONObject properties = item.getJSONObject("properties");
                LayerFeaturesProperties layerFeaturesProperties = new LayerFeaturesProperties();
                if(properties.has("name"))
                    layerFeaturesProperties.setName(properties.getString("name"));
                if(properties.has("city"))
                    layerFeaturesProperties.setCity(properties.getString("city"));
                if(properties.has("nation"))
                    layerFeaturesProperties.setNation(properties.getString("nation"));
                if(properties.has("province"))
                    layerFeaturesProperties.setProvince(properties.getString("province"));
                if(properties.has("project_name"))
                    layerFeaturesProperties.setProject_name(properties.getString("project_name"));
                if(properties.has("project_type"))
                    layerFeaturesProperties.setProject_type(properties.getString("project_type"));
                if(properties.has("construction_status"))
                    layerFeaturesProperties.setConstruction_status(properties.getString("construction_status"));
                if(properties.has("project_introduction"))
                    layerFeaturesProperties.setProject_introduction(properties.getString("project_introduction"));
                layerFeatures.setProperties(layerFeaturesProperties);
            }
            layerFeaturesItem.put(key, layerFeatures);
        }

        return layerFeaturesItem;
    }
}
