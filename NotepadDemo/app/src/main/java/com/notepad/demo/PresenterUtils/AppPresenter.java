package com.notepad.demo.PresenterUtils;

import android.content.Context;

import com.notepad.demo.PresenterUtils.ViewInterface.PadView;
import com.notepad.demo.mode.PadApp;
import com.notepad.demo.mode.impl.NotePadAppinfo;

import java.util.List;

/**
 * Created by feiran.zhang on 2018/5/1.
 */

public class AppPresenter extends PresenFrame<PadApp> implements Presenter{
    private NotePadAppinfo mNotePadAppinfo;

    @Override
    public void getPadData() {
        if (mNotePadAppinfo!=null){
            mNotePadAppinfo.getAllDatasFromDataBase(new OnAppCallback() {
                @Override
                public void onSuccess(List pResponse) {
                    mPadView.setGridView(pResponse);
                }

                @Override
                public void onError(String pError) {
                    mPadView.NoteErrorView(pError);
                }
            });
        }
    }

    @Override
    public void removeData(long id,int position) {
        if(mNotePadAppinfo!=null){
            mNotePadAppinfo.deleteModeData(id);
            mPadView.deleteGridView(position);
        }
    }

    @Override
    public void removeData(String name) {
    }


    @Override
    public void setMainView(Context context, PadView<PadApp> padView) {
        super.setMainView(context, padView);
        mNotePadAppinfo=new NotePadAppinfo(context);
    }
}
