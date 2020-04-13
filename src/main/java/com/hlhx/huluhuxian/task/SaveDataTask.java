package com.hlhx.huluhuxian.task;

import com.hlhx.huluhuxian.model.CameraInfo;
import com.hlhx.huluhuxian.model.ControlUnit;
import com.hlhx.huluhuxian.service.*;
import com.hlhx.huluhuxian.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Description: 定时存储统计的结果信息
 * @Author: wangjc
 * @CreateDate: 2020/3/31
 * @Version: 1.0
 */
@Component
public class SaveDataTask {
    @Autowired
    private ControlUnitNumService controlUnitNumService;
    @Autowired
    private LuanChengService luanChengService;
    @Autowired
    private GaoYiService gaoYiService;
    @Autowired
    private XinLeService xinLeService;
    @Autowired
    private LuQuanService luQuanService;
    @Autowired
    private ZhengDingService zhengDingService;
    @Autowired
    private YuanShiService yuanShiService;
    @Autowired
    private CameraNumService cameraNumService;

    @Scheduled(cron = "0 10 00 * * ?")
    public void saveAlarmNum(){
        try {
            Date time= DateUtil.lastDayTime(new Date());
            Date[] beginDateScope= DateUtil.beginDateScope();
            ControlUnit controlUnit1=new ControlUnit();
            List<ControlUnit> list=luanChengService.getControlUnitList(null,null,controlUnit1,beginDateScope);
            List<ControlUnit> list1=gaoYiService.getControlUnitList(null,null,controlUnit1,beginDateScope);
            List<ControlUnit> list2=xinLeService.getControlUnitList(null,null,controlUnit1,beginDateScope);
            List<ControlUnit> list3=luQuanService.getControlUnitList(null,null,controlUnit1,beginDateScope);
            List<ControlUnit> list4=zhengDingService.getControlUnitList(null,null,controlUnit1,beginDateScope);
            List<ControlUnit> list5=yuanShiService.getControlUnitList(null,null,controlUnit1,beginDateScope);
            list.addAll(list1);
            list.addAll(list2);
            list.addAll(list3);
            list.addAll(list4);
            list.addAll(list5);
            for (ControlUnit controlUnit:list){
                controlUnit.setTime(time);
            }
            controlUnitNumService.insertAreaAlarmNum(list);
            System.out.println("#################################");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Scheduled(cron = "0 10 00 * * ?")
    public void saveCameraAlarmNum(){
        try {
            Date time= DateUtil.lastDayTime(new Date());
            Date[] beginDateScope= DateUtil.beginDateScope();
            CameraInfo cameraInfo1=new CameraInfo();
            List<CameraInfo> list=luanChengService.getCameraList(null,null,cameraInfo1,beginDateScope);
            for (CameraInfo cameraInfo:list){
                cameraInfo.setCityNo("130111");
                cameraInfo.setTime(time);
            }
            List<CameraInfo> list1=gaoYiService.getCameraList(null,null,cameraInfo1,beginDateScope);
            for (CameraInfo cameraInfo:list1){
                cameraInfo.setCityNo("130127");
                cameraInfo.setTime(time);
            }
            List<CameraInfo> list2=xinLeService.getCameraList(null,null,cameraInfo1,beginDateScope);
            for (CameraInfo cameraInfo:list2){
                cameraInfo.setCityNo("130184");
                cameraInfo.setTime(time);
            }
            List<CameraInfo> list3=luQuanService.getCameraList(null,null,cameraInfo1,beginDateScope);
            for (CameraInfo cameraInfo:list3){
                cameraInfo.setCityNo("130110");
                cameraInfo.setTime(time);
            }
            List<CameraInfo> list4=zhengDingService.getCameraList(null,null,cameraInfo1,beginDateScope);
            for (CameraInfo cameraInfo:list4){
                cameraInfo.setCityNo("130123");
                cameraInfo.setTime(time);
            }
            List<CameraInfo> list5=yuanShiService.getCameraList(null,null,cameraInfo1,beginDateScope);
            for (CameraInfo cameraInfo:list5){
                cameraInfo.setCityNo("130132");
                cameraInfo.setTime(time);
            }
            list.addAll(list1);
            list.addAll(list2);
            list.addAll(list3);
            list.addAll(list4);
            list.addAll(list5);
            cameraNumService.insertCameraNum(list);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

//    @Scheduled(cron = "0 */2 * * * ?")
    public void getAlarmNum(){
        try {
            Date[] beginDateScope= DateUtil.beginDateScope();
            ControlUnit controlUnit=new ControlUnit();
            List<ControlUnit> list=controlUnitNumService.getAreaAlarmNum(null,null,controlUnit,beginDateScope);
            System.out.println(list.size());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
//    @Scheduled(cron = "0 */2 * * * ?")
    public void getCameraNum(){
        try {
            Date[] beginDateScope= DateUtil.beginDateScope();
            CameraInfo cameraInfo=new CameraInfo();
            cameraInfo.setRegionId(3);
            List<CameraInfo> list = cameraNumService.getCameraNum(null, null, cameraInfo, beginDateScope);
            System.out.println(list.size());
        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
