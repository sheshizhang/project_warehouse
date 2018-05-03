package com.notepad.demo.mode;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by feiran.zhang on 2018/4/30.
 */

@Entity
public class PadApp implements Serializable {
    private static final long serialVersionUID = 1L;

    @Property(nameInDb = "id")
    @Id(autoincrement = true)
    long id;
    @Property(nameInDb = "content_title")
    private String content_title;
    @Property(nameInDb = "content")
    private String content;
    @Property(nameInDb = "edit_timer")
    private String edit_timer;

    @Property(nameInDb = "padid")
    @NotNull
    private long padid;



    @Generated(hash = 20068928)
    public PadApp(long id, String content_title, String content, String edit_timer,
            long padid) {
        this.id = id;
        this.content_title = content_title;
        this.content = content;
        this.edit_timer = edit_timer;
        this.padid = padid;
    }

    @Generated(hash = 1547228735)
    public PadApp() {
    }
    


    public String getContent_title() {
        return content_title;
    }

    public void setContent_title(String content_title) {
        this.content_title = content_title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEdit_timer() {
        return edit_timer;
    }

    public void setEdit_timer(String edit_timer) {
        this.edit_timer = edit_timer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPadid() {
        return padid;
    }

    public void setPadid(long padid) {
        this.padid = padid;
    }

    @Override
    public String toString() {
        return "PadApp{" +
                "id=" + id +
                ", content_title='" + content_title + '\'' +
                ", content='" + content + '\'' +
                ", edit_timer='" + edit_timer + '\'' +
                ", padid=" + padid +
                '}';
    }

}
