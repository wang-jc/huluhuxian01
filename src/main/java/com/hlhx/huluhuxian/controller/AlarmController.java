package com.hlhx.huluhuxian.controller;

import com.hlhx.huluhuxian.model.*;
import com.hlhx.huluhuxian.service.*;
import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @Author wangjunchao
 * @create 2020/2/2 23:30
 */
@RestController
@RequestMapping("/alarm")
public class AlarmController {
    @Autowired
    private LuanChengService luanChengService;
    @Autowired
    private XinLeService xinLeService;
    @Autowired
    private YuanShiService yuanshiService;
    @Autowired
    private GaoYiService gaoyiService;
    @Autowired
    private LuQuanService luquanService;
    @Autowired
    private ZhengDingService zhengdingService;
    @Autowired
    private DbInfoService dbInfoService;
    @Autowired
    private ControlUnitNumService controlUnitNumService;
    @Autowired
    private CameraNumService cameraNumService;

    //获取每个点位的报警详情列表
    @RequestMapping("/getPointAlermList")
    public RespPageBean getPointAlermList(@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "10") Integer size, AlarmLog alarmLog, Date[] beginDateScope){
        RespPageBean bean=new RespPageBean();
        if(!alarmLog.getCityNo().isEmpty()){
            if("130111".equals(alarmLog.getCityNo())){
                bean=luanChengService.getPointAlermList(page,size,alarmLog,beginDateScope);
            }else if("130184".equals(alarmLog.getCityNo())){
                bean=xinLeService.getPointAlermList(page,size,alarmLog,beginDateScope);
            }else if("130127".equals(alarmLog.getCityNo())){
                bean=gaoyiService.getPointAlermList(page,size,alarmLog,beginDateScope);
            }else if("130132".equals(alarmLog.getCityNo())){
                bean=yuanshiService.getPointAlermList(page,size,alarmLog,beginDateScope);
            }else if("130123".equals(alarmLog.getCityNo())){
                bean=zhengdingService.getPointAlermList(page,size,alarmLog,beginDateScope);
            }else if("130110".equals(alarmLog.getCityNo())){
                bean=luquanService.getPointAlermList(page,size,alarmLog,beginDateScope);
            }
        }

        return bean;
    }
    //获取点位列表及点位报警次数
    @RequestMapping("/getPointList")
    public List<CameraInfo> getPointList(@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "10") Integer size, CameraInfo cameraInfo, Date[] beginDateScope){
        List<CameraInfo> list=new ArrayList<>();
        if(!cameraInfo.getCityNo().isEmpty()||!cameraInfo.getIndexCode().isEmpty()){
            if("130111".equals(cameraInfo.getCityNo())|| "130111".equals(cameraInfo.getIndexCode())){
                list=luanChengService.getCameraList(page,size,cameraInfo,beginDateScope);
                if(list==null||list.size()==0){
                    cameraInfo.setCityNo("130111");
                    list=cameraNumService.getCameraNum(page,size,cameraInfo,beginDateScope);
                }
            }else if("130184".equals(cameraInfo.getCityNo())|| "130184".equals(cameraInfo.getIndexCode())){
                list=xinLeService.getCameraList(page,size,cameraInfo,beginDateScope);
                if(list==null||list.size()==0){
                    cameraInfo.setCityNo("130111");
                    list=cameraNumService.getCameraNum(page,size,cameraInfo,beginDateScope);
                }
            }else if("130127".equals(cameraInfo.getCityNo())|| "130127".equals(cameraInfo.getIndexCode())){
                list=gaoyiService.getCameraList(page,size,cameraInfo,beginDateScope);
                if(list==null||list.size()==0){
                    cameraInfo.setCityNo("130111");
                    list=cameraNumService.getCameraNum(page,size,cameraInfo,beginDateScope);
                }
            }else if("130132".equals(cameraInfo.getCityNo())|| "130132".equals(cameraInfo.getIndexCode())){
                list=yuanshiService.getCameraList(page,size,cameraInfo,beginDateScope);
                if(list==null||list.size()==0){
                    cameraInfo.setCityNo("130111");
                    list=cameraNumService.getCameraNum(page,size,cameraInfo,beginDateScope);
                }
            }else if("130123".equals(cameraInfo.getCityNo())|| "130123".equals(cameraInfo.getIndexCode())){
                list=zhengdingService.getCameraList(page,size,cameraInfo,beginDateScope);
                if(list==null||list.size()==0){
                    cameraInfo.setCityNo("130111");
                    list=cameraNumService.getCameraNum(page,size,cameraInfo,beginDateScope);
                }
            }else if("130110".equals(cameraInfo.getCityNo())|| "130110".equals(cameraInfo.getIndexCode())){
                list=luquanService.getCameraList(page,size,cameraInfo,beginDateScope);
                if(list==null||list.size()==0){
                    cameraInfo.setCityNo("130111");
                    list=cameraNumService.getCameraNum(page,size,cameraInfo,beginDateScope);
                }
            }
        }
        return list;
    }
    //获取所有区域
    @RequestMapping("/getArea")
    public List<ControlUnit> getArea(){
        List<ControlUnit> list=new ArrayList<ControlUnit>();
        list= luanChengService.getArea();
        List<ControlUnit> list2=new ArrayList<ControlUnit>();
        list2=xinLeService.getArea();
        List<ControlUnit> list3=new ArrayList<ControlUnit>();
        list3=gaoyiService.getArea();
        List<ControlUnit> list4=new ArrayList<ControlUnit>();
        list4=zhengdingService.getArea();
        List<ControlUnit> list5=new ArrayList<ControlUnit>();
        list5=luquanService.getArea();
        List<ControlUnit> list6=new ArrayList<ControlUnit>();
        list6=yuanshiService.getArea();
        list.addAll(list2);
        list.addAll(list3);
        list.addAll(list4);
        list.addAll(list5);
        list.addAll(list6);
        return list;
    }
    //统计所有区域的报警次数总和
    @RequestMapping("/getAreaAlarmCount")
    public List<ControlUnit> getAreaAlarmCount(@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "30") Integer size, ControlUnit controlUnit, Date[] beginDateScope){
        List<ControlUnit> list=luanChengService.getControlUnitList(page,size,controlUnit,beginDateScope);
        if(list==null||list.size()==0){
            controlUnit.setIndexCode("130111");
            list=controlUnitNumService.getAreaAlarmNum(page ,size,controlUnit,beginDateScope);
        }
        List<ControlUnit> list2=xinLeService.getControlUnitList(page,size,controlUnit,beginDateScope);
        if(list2==null || list2.size()==0){
            controlUnit.setIndexCode("130184");
            list2=controlUnitNumService.getAreaAlarmNum(page ,size,controlUnit,beginDateScope);
        }
        List<ControlUnit> list3=gaoyiService.getControlUnitList(page,size,controlUnit,beginDateScope);
        if(list3==null ||list3.size()==0){
            controlUnit.setIndexCode("130127");
            list3=controlUnitNumService.getAreaAlarmNum(page ,size,controlUnit,beginDateScope);
        }
        List<ControlUnit> list4=luquanService.getControlUnitList(page,size,controlUnit,beginDateScope);
        if(list4==null ||list4.size()==0){
            controlUnit.setIndexCode("130110");
            list4=controlUnitNumService.getAreaAlarmNum(page ,size,controlUnit,beginDateScope);
        }
        List<ControlUnit> list5=zhengdingService.getControlUnitList(page,size,controlUnit,beginDateScope);
        if(list5==null ||list5.size()==0){
            controlUnit.setIndexCode("130123");
            list5=controlUnitNumService.getAreaAlarmNum(page ,size,controlUnit,beginDateScope);
        }
        List<ControlUnit> list6=yuanshiService.getControlUnitList(page,size,controlUnit,beginDateScope);
        if(list6==null||list6.size()==0){
            controlUnit.setIndexCode("130132");
            list6=controlUnitNumService.getAreaAlarmNum(page ,size,controlUnit,beginDateScope);
        }
        list.addAll(list2);
        list.addAll(list3);
        list.addAll(list4);
        list.addAll(list5);
        list.addAll(list6);
        //降序排序
        list.sort(Comparator.comparing(ControlUnit::getNum).reversed());
//        List<DbInfo> dbInfoList=dbInfoService.getDbInfoList();
//        System.out.println("测试数据源的个数——————————————————————————"+dbInfoList.size());
        return list;
    }

    //获取路线类别
    @RequestMapping("/getRegionInfo")
    public List<RegionInfo> getRegionInfo(Integer controlUnitId){
        List<RegionInfo> list=luanChengService.getRegionInfo(controlUnitId);
        return list;
    }

   /* @RequestMapping("/getAlarmList")
    public List<Alarm> getAlarmList(){
        return alarmService.getAlarmList();
    }*/


}
