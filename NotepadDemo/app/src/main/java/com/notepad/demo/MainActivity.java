package com.notepad.demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.notepad.demo.PresenterUtils.AppPresenter;
import com.notepad.demo.PresenterUtils.ViewInterface.PadView;
import com.notepad.demo.adapter.PadAdapter;
import com.notepad.demo.mode.PadApp;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements PadView<PadApp>{
    private ListView mNopadListView;
    private PadAdapter mPadAdapter;
    private List<PadApp>mList;
    private Button mNewPadBtn;
    private AppPresenter mAppPresenter;

    public final static int REQUEST_CODE=1;
    public final static int RESULT_CODE=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayouId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mNopadListView=(ListView)findViewById(R.id.notepadListView);
        mNewPadBtn=(Button)findViewById(R.id.newNotepadBtn);
        mAppPresenter=new AppPresenter();
        mList=new ArrayList<>();

//        for (int i=0;i<10;i++){
//            PadApp padApp=new PadApp();
//            padApp.setContent_title("当前时间：项目item=="+(i+1));
//            mList.add(padApp);
//        }

        mAppPresenter.setMainView(MainActivity.this,this);

        mPadAdapter=new PadAdapter(MainActivity.this,mList);
        mNopadListView.setAdapter(mPadAdapter);

        mNopadListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,NotePadSeeActivity.class);
                intent.putExtra("padApp",(PadApp)mPadAdapter.getItem(position));
                startActivityForResult(intent,MainActivity.REQUEST_CODE);
            }
        });

        mNopadListView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                menu.setHeaderTitle(R.string.choose_todo);
                menu.add(0,0,0,R.string.open_item);
                menu.add(0,1,0,R.string.edit_item);
                menu.add(0,2,0,R.string.delete_item);
            }
        });

        mNewPadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this,EditNotePadActivity.class)
                        ,MainActivity.REQUEST_CODE);
            }
        });

        //数据库获取数据
        mAppPresenter.getPadData();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.w("zhangfeiran100","requestCode111==="+requestCode);
        Log.w("zhangfeiran100","resultCode222==="+resultCode);

        if(resultCode==MainActivity.RESULT_CODE){
            //数据库获取数据
            mAppPresenter.getPadData();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Log.w("zhangfeiran100","info.id=="+info.id);
        int position= (int) info.id;
        PadApp padApp= (PadApp) mPadAdapter.getItem(position);
        Log.w("zhangfeiran100","padApp.getId()=="+padApp.getId());


        switch (item.getItemId()){
            case 0:
                Log.w("zhangfeiran100","onContextItemSelected 0");
                //打开
                Intent intent=new Intent(MainActivity.this,NotePadSeeActivity.class);
                intent.putExtra("padApp",padApp);
                startActivityForResult(intent,MainActivity.REQUEST_CODE);
                break;
            case 1:
                Log.w("zhangfeiran100","onContextItemSelected 1");
                //编辑
                Intent intent2=new Intent(MainActivity.this,EditNotePadActivity.class);
                intent2.putExtra("isEnble",1);
                intent2.putExtra("padApp",padApp);
                startActivityForResult(intent2,MainActivity.REQUEST_CODE);
                break;
            case 2:
                Log.w("zhangfeiran100","onContextItemSelected 2");
                //删除
                mAppPresenter.removeData(padApp.getId(),position);
                break;
        }
        return super.onContextItemSelected(item);
    }


    /**
     * 从数据库获取之前保存的newpad
     * @param list
     */
    @Override
    public void setGridView(List<PadApp> list) {
        Log.w("zhangfeiran100","数据库获取数据list=="+list);
        mList=list;
        if(mList!=null&&mList.size()>0){
            mPadAdapter.setData(mList);
            mPadAdapter.notifyDataSetInvalidated();
        }
    }

    /**
     * 返回结果出错
     * @param error
     */
    @Override
    public void NoteErrorView(String error) {
    }

    /**
     * 删除列表数据后页面显示
     */
    @Override
    public void deleteGridView(int position) {
        if(mPadAdapter.getCount()==0){
            EditNotePadActivity.padid=0;
        }
        mPadAdapter.removeData(position);
    }
}
