package com.notepad.demo.mode.modeInface;

import com.notepad.demo.PresenterUtils.PresenFrame;

/**
 * Created by feiran.zhang on 2018/5/1.
 */

public interface ModeInterface<T>{

    void addModeData(T object);
    void deleteModeData(long id);
    void deleteAllMode();
    void getAllDatasFromDataBase(PresenFrame.OnAppCallback onAppCallback);
}
