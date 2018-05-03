package com.notepad.demo;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.notepad.demo.Utils.Utils;
import com.notepad.demo.dataBase.GreenDaoManager;
import com.notepad.demo.mode.PadApp;

import java.util.List;

/**
 * Created by feiran.zhang on 2018/5/1.
 */

public class EditNotePadActivity extends BaseActivity {
    private EditText mEditTextTitle;
    private EditText mEditContent;
    private Button mSaveBtn;
    private List<PadApp>mList;
    private GreenDaoManager greenDaoManager;
    private PadApp exterenpadApp;
    public static long padid=0;
    public int enable=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayouId() {
        return R.layout.notepad_activity;
    }

    @Override
    public void initView() {
        greenDaoManager=GreenDaoManager.getInstance();
        enable=getIntent().getIntExtra("isEnble",0);

        GetPadid();

        mEditTextTitle=(EditText)findViewById(R.id.edit_titleText);
        mEditContent=(EditText)findViewById(R.id.content_edit);
        mSaveBtn=(Button)findViewById(R.id.newPadSaveBtn);

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewPadDatas();
            }
        });

        Log.w("zhangfeiran100","enable=="+enable);
        if(enable==1){
            //编辑状态
            ShowEditTextView(true);
        }
    }

    //获取数据库padid
    private void GetPadid() {
        List<PadApp>installlist=greenDaoManager.queryExitDatas();

        if(installlist!=null&&installlist.size()>0){
            PadApp padapp=installlist.get(0);
            EditNotePadActivity.padid=padapp.getPadid();

            Log.w("zhangfeiran100","从数据库获取的EditNotePadActivity.padid=="+EditNotePadActivity.padid);
        }
    }

    public void ShowEditTextView(boolean isShow){
        exterenpadApp= (PadApp) getIntent().getSerializableExtra("padApp");
        Log.w("zhangfeiran100","padApp=="+exterenpadApp);

        if(isShow){
            mEditTextTitle.setTextColor(getResources().getColor(R.color.colorBlack));
        }

        List<PadApp>installlist=greenDaoManager.queryExitDatas();
        if(installlist!=null&&installlist.size()>0){
            for (PadApp searchpadApp:installlist){
                if (exterenpadApp.getId()==searchpadApp.getId()){
                    if(!TextUtils.isEmpty(exterenpadApp.getContent_title())){
                        mEditTextTitle.setText(exterenpadApp.getContent_title().toString());
                    }
                    if(!TextUtils.isEmpty(exterenpadApp.getContent())){
                        mEditContent.setText(exterenpadApp.getContent().toString());
                    }
                    break;
                }
            }
        }
    }

    public void saveNewPadDatas(){
        String title=mEditTextTitle.getText().toString();
        String content=mEditContent.getText().toString();

        if (enable==1){
            Log.w("zhangfeiran100","编辑状态");
            if(exterenpadApp!=null&&
                    !TextUtils.isEmpty(title)&&!TextUtils.isEmpty(content)){
                exterenpadApp.setContent_title(title);
                exterenpadApp.setContent(content);
                exterenpadApp.setEdit_timer(Utils.getDateTimer2());
                GreenDaoManager.getInstance().inserDataBase(exterenpadApp);

                setResult(MainActivity.RESULT_CODE);
                Toast.makeText(EditNotePadActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                finish();
            }else{
                if(TextUtils.isEmpty(title)){
                    Toast.makeText(EditNotePadActivity.this,"标题不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(content)){
                    Toast.makeText(EditNotePadActivity.this,"输入内容不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }else{
            Log.w("zhangfeiran100","非编辑状态");
            if(!TextUtils.isEmpty(title)&&!TextUtils.isEmpty(content)){
                //先获取一次padid。
                mList=GreenDaoManager.getInstance().queryExitDatas();
                for (PadApp padApp1:mList){
                    EditNotePadActivity.padid=padApp1.getPadid();
                   break;
                }

                Log.w("zhangfeiran100","非编辑状态EditNotePadActivity.padid=="+EditNotePadActivity.padid);

                EditNotePadActivity.padid++;

                PadApp padApp=new PadApp();
                padApp.setContent_title(title);
                padApp.setContent(content);
                padApp.setId(EditNotePadActivity.padid);
                padApp.setEdit_timer(Utils.getDateTimer2());
                GreenDaoManager.getInstance().inserDataBase(padApp);

                mList=GreenDaoManager.getInstance().queryExitDatas();
                for (PadApp padApp1:mList){
                    padApp1.setPadid(EditNotePadActivity.padid);
                    //存入数据库，下次取数据则从数据库取。
                    GreenDaoManager.getInstance().inserDataBase(padApp1);
                }

                Log.w("zhangfeiran100","此时NotePadActivity.padid=="+ EditNotePadActivity.padid);
                setResult(MainActivity.RESULT_CODE);
                Toast.makeText(EditNotePadActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                finish();
            }else{
                if(TextUtils.isEmpty(title)){
                    Toast.makeText(EditNotePadActivity.this,"标题不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(content)){
                    Toast.makeText(EditNotePadActivity.this,"输入内容不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        exterenpadApp=null;
    }
}
