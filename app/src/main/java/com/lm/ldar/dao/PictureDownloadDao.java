package com.lm.ldar.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.lm.ldar.entity.Picture;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * Created by xieweikun on 2018/1/14.
 */

public class PictureDownloadDao extends AbstractDao<Picture, Long> {

    public static final String TABLENAME = "PICTURE";

    /**
     * Properties of entity VideoPlayEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        //序号
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        //图片编号
        public final static Property Number = new Property(1, String.class, "number", false, "NUMBER");
        //图片名称
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        //状态 0 未编辑 1 已编辑
        public final static Property Status = new Property(3, String.class, "status", false, "STATUS");
        //创建时间
        public final static Property Createtime = new Property(4, String.class, "createtime", false, "CREATETIME");
        //装置信息
        public final static Property Deviceinfo = new Property(5, String.class, "deviceinfo", false, "DEVICEINFO");
        //物质信息
        public final static Property Material = new Property(6, String.class, "material", false, "MATERIAL");
        //位置信息
        public final static Property Position = new Property(7, String.class, "position", false, "POSITION");
        //装置ID
        public final static Property Did = new Property(8, int.class, "did", false, "DID");
        //区域ID
        public final static Property Aid = new Property(9, int.class, "aid", false, "AID");
        //企业ID
        public final static Property Eid = new Property(10, int.class, "eid", false, "EID");
        //图片重命名
        public final static Property Elementname = new Property(11, String.class, "elementname", false, "ELEMENTNAME");
        //PID图号
        public final static Property Pidnumber = new Property(12, String.class, "pidnumber", false, "PIDNUMBER");
        //图像版本号
        public final static Property Pvid = new Property(13, int.class, "pvid", false, "PVID");
        //草图地址
        public final static Property Sketch = new Property(14, String.class, "sketch", false, "SKETCH");
    }

    ;

    public PictureDownloadDao(DaoConfig config) {
        super(config);
    }

    public PictureDownloadDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"PICTURE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NUMBER\" TEXT," + // 1: number
                "\"NAME\" TEXT," + // 2: name
                "\"STATUS\" TEXT," + // 3: status
                "\"CREATETIME\" TEXT," + //4:createtime
                "\"DEVICEINFO\" TEXT," + // 5: deviceinfo
                "\"MATERIAL\" TEXT," + // 6: material
                "\"POSITION\" TEXT," + // 7: position
                "\"DID\" INTEGER," +//8.did
                "\"AID\" INTEGER," +//9.aid
                "\"EID\" INTEGER," + //10.eid
                "\"ELEMENTNAME\" TEXT," + //11: elementname
                "\"PIDNUMBER\" TEXT," + //12: pidnumber
                "\"PVID\" INTEGER," + //13.pvid
                "\"SKETCH\" TEXT);");//14.sketch
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PICTURE\"";
        db.execSQL(sql);
    }

    @Override
    protected Picture readEntity(Cursor cursor, int offset) {
        Picture picture = new Picture(
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getString(offset + 1), // number
                cursor.getString(offset + 2), // name
                cursor.getString(offset + 3), // status
                cursor.getString(offset + 4), // createtime
                cursor.getString(offset + 5), // deviceinfo
                cursor.getString(offset + 6), // material
                cursor.getString(offset + 7), // position
                cursor.getInt(offset + 8),// did
                cursor.getInt(offset + 9),// aid
                cursor.getInt(offset + 10),// eid
                cursor.getString(offset + 11), // elementname
                cursor.getString(offset + 12), // pidnumber
                cursor.getInt(offset + 13),// pvid
                cursor.getString(offset + 14)// sketch
        );
        return picture;
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected void readEntity(Cursor cursor, Picture entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNumber(cursor.getString(offset + 1));
        entity.setName(cursor.getString(offset + 2));
        entity.setStatus(cursor.getString(offset + 3));
        entity.setCreatetime(cursor.getString(offset + 4));
        entity.setDeviceinfo(cursor.getString(offset + 5));
        entity.setMaterial(cursor.getString(offset + 6));
        entity.setPosition(cursor.getString(offset + 7));
        entity.setDid(cursor.getInt(offset + 8));
        entity.setAid(cursor.getInt(offset + 9));
        entity.setEid(cursor.getInt(offset + 10));
        entity.setElementname(cursor.getString(offset + 11));
        entity.setPidnumber(cursor.getString(offset + 12));
        entity.setPvid(cursor.getInt(offset + 13));
        entity.setSketch(cursor.getString(offset + 14));
    }

    @Override
    protected void bindValues(DatabaseStatement stmt, Picture entity) {
        stmt.clearBindings();
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNumber());
        stmt.bindString(3, entity.getName());
        stmt.bindString(4, entity.getStatus());
        stmt.bindString(5, entity.getCreatetime());
        stmt.bindString(6, entity.getDeviceinfo());
        stmt.bindString(7, entity.getMaterial());
        stmt.bindString(8, entity.getPosition());
        stmt.bindLong(9,entity.getDid());
        stmt.bindLong(10,entity.getAid());
        stmt.bindLong(11,entity.getEid());
        stmt.bindString(12, entity.getElementname());
        stmt.bindString(13, entity.getPidnumber());
        stmt.bindLong(14, entity.getPvid());
        stmt.bindString(15, entity.getSketch());
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, Picture entity) {
        stmt.clearBindings();
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNumber());
        stmt.bindString(3, entity.getName());
        stmt.bindString(4, entity.getStatus());
        stmt.bindString(5, entity.getCreatetime());
        stmt.bindString(6, entity.getDeviceinfo());
        stmt.bindString(7, entity.getMaterial());
        stmt.bindString(8, entity.getPosition());
        stmt.bindLong(9,entity.getDid());
        stmt.bindLong(10,entity.getAid());
        stmt.bindLong(11,entity.getEid());
        stmt.bindString(12, entity.getElementname());
        stmt.bindString(13, entity.getPidnumber());
        stmt.bindLong(14, entity.getPvid());
        stmt.bindString(15, entity.getSketch());
    }

    @Override
    protected Long updateKeyAfterInsert(Picture entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    protected Long getKey(Picture entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean hasKey(Picture entity) {
        return entity.getId() != null;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }

}
