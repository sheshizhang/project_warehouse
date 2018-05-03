package com.notepad.demo;

import android.text.TextUtils;
import android.widget.TextView;

import com.notepad.demo.dataBase.GreenDaoManager;
import com.notepad.demo.mode.PadApp;


/**
 * Created by feiran.zhang on 2018/5/3.
 */

public class NotePadSeeActivity extends BaseActivity {
    private TextView mTitleTextView;
    private TextView mContentTextView;

//    private GreenDaoManager greenDaoManager;
    private PadApp exterenpadApp;

    @Override
    public int getLayouId() {
        return R.layout.notepadsee_activity;
    }

    @Override
    public void initView() {
        mTitleTextView=(TextView)findViewById(R.id.notepadsee_TextView);
        mContentTextView=(TextView)findViewById(R.id.notepadsee_ContentTextView);

        getData();
    }

    public void getData(){
        exterenpadApp= (PadApp) getIntent().getSerializableExtra("padApp");
//        greenDaoManager=GreenDaoManager.getInstance();

        String title=exterenpadApp.getContent_title();
        String content=exterenpadApp.getContent();

        if (!TextUtils.isEmpty(title)&&!TextUtils.isEmpty(content)){
            mTitleTextView.setText(title);
            mContentTextView.setText(content);
        }

    }

}
