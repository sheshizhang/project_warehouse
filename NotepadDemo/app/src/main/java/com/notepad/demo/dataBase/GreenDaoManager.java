package com.notepad.demo.dataBase;

import com.notepad.demo.MyPadApplication;
import com.notepad.demo.greendao.DaoMaster;
import com.notepad.demo.greendao.DaoSession;
import com.notepad.demo.greendao.PadAppDao;
import com.notepad.demo.mode.PadApp;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by feiran.zhang on 2018/4/30.
 */

public class GreenDaoManager {

    private static final String DB_NAME = "notepad";// 数据库名称

    private DaoMaster mMaster;
    private DaoSession mDaosession;

    public static GreenDaoManager getInstance(){
        return GreenDaoHolder.instance;
    }

    static class GreenDaoHolder{
        private static GreenDaoManager instance=new GreenDaoManager();
    }

    public GreenDaoManager(){
        MyDaoOpenHelper myDaoOpenHelper=new MyDaoOpenHelper(MyPadApplication.mApp,DB_NAME);
        mMaster=new DaoMaster(myDaoOpenHelper.getWritableDatabase());
        mDaosession=mMaster.newSession();
    }

    public DaoMaster getMaster(){
        return mMaster;
    }
    public DaoSession getDaosession(){
        return mDaosession;
    }

    public DaoSession getNewDasession(){
        mDaosession=mMaster.newSession();
        return mDaosession;
    }


    /**
     * 将数据插入数据库中
     * @param padApp
     */
    public void inserDataBase(PadApp padApp){
        if(mDaosession!=null){
            PadAppDao padAppDao=mDaosession.getPadAppDao();
            padAppDao.insertOrReplace(padApp);
        }
    }

    public List<PadApp> queryExitDatas(){
        List<PadApp>mList=null;
        if (mDaosession!=null){
            PadAppDao padAppDao=mDaosession.getPadAppDao();
            QueryBuilder queryBuilder=padAppDao.queryBuilder();
            mList=queryBuilder.list();
        }
        return mList;
    }


    /**
     * 根据id来查询某一项
     * @param id
     */
    public PadApp queryData(long id){
        PadApp padApp=null;
        if(mDaosession!=null){
            PadAppDao padAppDao=mDaosession.getPadAppDao();
            padApp=padAppDao.queryBuilder().where(PadAppDao.Properties.Id.eq(id)).unique();
        }
        return padApp;
    }


    /**
     * 根据数据类型来删除
     * @param data
     */
    public void delete(PadApp data) {
        if(mDaosession!=null){
            PadAppDao padAppDao=mDaosession.getPadAppDao();
            PadApp newdata=padAppDao.queryBuilder().where(PadAppDao.Properties.Id.eq(data.getId())).unique();
            padAppDao.delete(newdata);
        }
    }

    /**
     * 根据id来删除数据
     * @param id
     * @return
     */
    public void deleteById(long id) {
        if(mDaosession!=null){
            PadAppDao padAppDao=mDaosession.getPadAppDao();
            PadApp newdata=padAppDao.queryBuilder().where(PadAppDao.Properties.Id.eq(id)).unique();
            padAppDao.delete(newdata);
        }
    }


    /**
     * 删除全部应用
     */
    public void deleteAll() {
        if(mDaosession!=null){
            PadAppDao padAppDao=mDaosession.getPadAppDao();
            padAppDao.deleteAll();
        }
    }

    public void update(PadApp padApp){
        if (mDaosession!=null){
            PadAppDao padAppDao=mDaosession.getPadAppDao();

            PadApp padApp2=padAppDao.queryBuilder().where(PadAppDao.Properties.Id.eq(padApp.getId())).unique();
            padApp2.setContent_title(padApp.getContent_title());
            padApp2.setContent(padApp.getContent());

            mDaosession.update(padApp2);
        }
    }





}
