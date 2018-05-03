package com.notepad.demo.PresenterUtils;

import android.content.Context;

import com.notepad.demo.PresenterUtils.ViewInterface.PadView;

import java.util.List;

/**
 * Created by feiran.zhang on 2018/5/1.
 */

public class PresenFrame<T>{
    protected Context context;
    protected PadView<T>mPadView;

    public void setMainView(Context context,PadView<T>padView){
        this.context=context;
        this.mPadView=padView;
    }

    public interface OnAppCallback<T> {
        void onSuccess(List<T> pResponse);
        void onError(String pError);
    }
}
