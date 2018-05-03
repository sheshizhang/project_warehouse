package com.notepad.demo.mode.impl;

import android.content.Context;
import android.util.Log;

import com.notepad.demo.PresenterUtils.PresenFrame;
import com.notepad.demo.dataBase.GreenDaoManager;
import com.notepad.demo.mode.PadApp;
import com.notepad.demo.mode.modeInface.ModeInterface;

import java.util.List;

/**
 * Created by feiran.zhang on 2018/5/1.
 */

public class NotePadAppinfo implements ModeInterface<PadApp>{
    private Context mContext;
    private GreenDaoManager greenDaoManager;

    public NotePadAppinfo(Context context){
        mContext=context;
        greenDaoManager=GreenDaoManager.getInstance();
    }


    @Override
    public void addModeData(PadApp object) {
        greenDaoManager.inserDataBase(object);
    }

    @Override
    public void deleteModeData(long id) {
        greenDaoManager.deleteById(id);
    }

    @Override
    public void deleteAllMode() {
        greenDaoManager.deleteAll();
    }

    @Override
    public void getAllDatasFromDataBase(PresenFrame.OnAppCallback onAppCallback) {
        List<PadApp>list=greenDaoManager.queryExitDatas();

        Log.w("zhangfeiran100","List<PadApp>list=="+list);
        if (list!=null&&list.size()>0){
            onAppCallback.onSuccess(list);
        }else{
            onAppCallback.onError("数据库暂无数据");
        }
    }


}
