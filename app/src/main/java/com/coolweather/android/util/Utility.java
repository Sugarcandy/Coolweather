package com.coolweather.android.util;

import android.text.TextUtils;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    /*
     *解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponce(String responce)
    {
        if(!TextUtils.isEmpty(responce))
        {
            try
            {
                JSONArray allProvinces=new JSONArray(responce);
                for(int i=0;i<allProvinces.length();i++)
                {
                    JSONObject provinceObject=allProvinces.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
     *解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponce(String responce,int provinceId )
    {
        if(!TextUtils.isEmpty(responce))
        {
            try
            {
                JSONArray allProvinces=new JSONArray(responce);
                for(int i=0;i<allProvinces.length();i++)
                {
                    JSONObject CityObject=allProvinces.getJSONObject(i);
                    City city=new City();
                    city.setCityName(CityObject.getString("name"));
                    city.setCityCode(CityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
     *解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponce(String responce,int cityId )
    {
        if(!TextUtils.isEmpty(responce))
        {
            try
            {
                JSONArray allProvinces=new JSONArray(responce);
                for(int i=0;i<allProvinces.length();i++)
                {
                    JSONObject CityObject=allProvinces.getJSONObject(i);
                    County county=new County();
                    county.setCountryName(CityObject.getString("name"));
                    county.setWeatherId("weather_id");
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }
}