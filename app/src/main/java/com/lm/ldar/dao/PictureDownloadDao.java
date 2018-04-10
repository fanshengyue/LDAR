package com.lm.ldar.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.lm.ldar.entity.PictureDownload;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * Created by xieweikun on 2018/1/14.
 */

public class PictureDownloadDao extends AbstractDao<PictureDownload, Long> {

    public static final String TABLENAME = "PICTUREDOWNLOAD";

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
        //检测or复测
        public final static Property ischeck = new Property(15,int.class,"ischeck",false,"ISCHECK");
        //纬度
        public final static Property Latitude = new Property(16, Double.class, "latitude", false, "LATITUDE");
        //经度
        public final static Property Longitude = new Property(17, Double.class, "longitude", false, "LONGITUDE");
        //人为排序
        public final static Property Xid = new Property(18, Long.class, "xid", false, "XID");
    }



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
        db.execSQL("CREATE TABLE " + constraint + "\"PICTUREDOWNLOAD\" (" + //
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
                "\"SKETCH\" TEXT," + //14.sketch
                "\"ISCHECK\" INTEGER," + //15.ischeck
                "\"LATITUDE\" DOUBLE," + //16.latitude
                "\"LONGITUDE\" DOUBLE," + //17.longitude
                "\"XID\" INTEGER);");//18.xid
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PICTUREDOWNLOAD\"";
        db.execSQL(sql);
    }

    @Override
    protected PictureDownload readEntity(Cursor cursor, int offset) {
        PictureDownload picture = new PictureDownload(
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
                cursor.getString(offset + 14),// sketch
                cursor.getInt(offset+15),//ischeck
                cursor.getDouble(offset+16),//latitude
                cursor.getDouble(offset+17),//longitude
                cursor.getLong(offset+18)//xid
        );
        return picture;
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected void readEntity(Cursor cursor, PictureDownload entity, int offset) {
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
        entity.setIscheck(cursor.getInt(offset + 15));
        entity.setLatitude(cursor.getDouble(offset+16));
        entity.setLongitude(cursor.getDouble(offset+17));
        entity.setXid(cursor.getLong(offset+18));
    }

    @Override
    protected void bindValues(DatabaseStatement stmt, PictureDownload entity) {
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
        stmt.bindLong(16, entity.getIscheck());
        stmt.bindDouble(17,entity.getLatitude());
        stmt.bindDouble(18,entity.getLongitude());
        stmt.bindLong(19,entity.getXid());
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, PictureDownload entity) {
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
        stmt.bindLong(16, entity.getIscheck());
        stmt.bindDouble(17,entity.getLatitude());
        stmt.bindDouble(18,entity.getLongitude());
        stmt.bindLong(19,entity.getXid());
    }

    @Override
    protected Long updateKeyAfterInsert(PictureDownload entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    protected Long getKey(PictureDownload entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean hasKey(PictureDownload entity) {
        return entity.getId() != null;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }

}
