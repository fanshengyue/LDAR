package com.lm.ldar.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.lm.ldar.entity.Area;
import com.lm.ldar.entity.Pictureversion;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * Created by xieweikun on 2018/1/14.
 */

public class PictureversionDao extends AbstractDao<Pictureversion, Long> {

    public static final String TABLENAME = "PICTUREVERSION";

    /**
     * Properties of entity VideoPlayEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        //图像版本ID
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        //版本号
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        //版本描述
        public final static Property introduction = new Property(2, String.class, "introduction", false, "INTRODUCTION");
        //创建时间
        public final static Property Createtime = new Property(3, String.class, "createtime", false, "CREATETIME");
        //企业ID
        public final static Property Eid = new Property(4, int.class, "eid", false, "EID");
        //添加人ID
        public final static Property Uid = new Property(5, int.class, "uid", false, "UID");
        //继承图像版本ID
        public final static Property Pvid = new Property(6, int.class, "pvid", false, "PVID");
        //建档类型
        public final static Property Type = new Property(7, int.class, "type", false, "TYPE");
    }

    ;

    public PictureversionDao(DaoConfig config) {
        super(config);
    }

    public PictureversionDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"PICTUREVERSION\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"INTRODUCTION\" TEXT," + // 2: introduction
                "\"CREATETIME\" TEXT," + // 3: createtime
                "\"EID\" INTEGER," +//4.eid
                "\"UID\" INTEGER," +//5.uid
                "\"PVID\" INTEGER," + //6.pvid
                "\"TYPE\" INTEGER);");//7.type
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PICTUREVERSION\"";
        db.execSQL(sql);
    }

    @Override
    protected Pictureversion readEntity(Cursor cursor, int offset) {
        Pictureversion pictureversion = new Pictureversion(
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getString(offset + 1), // name
                cursor.getString(offset + 2), // introduction
                cursor.getString(offset + 3), // createtime
                cursor.getInt(offset + 4),// eid
                cursor.getInt(offset + 5),// uid
                cursor.getInt(offset + 6),// pvid
                cursor.getInt(offset + 7)// type
        );
        return pictureversion;
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected void readEntity(Cursor cursor, Pictureversion entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setIntroduction(cursor.getString(offset + 2));
        entity.setCreatetime(cursor.getString(offset + 3));
        entity.setEid(cursor.getInt(offset + 4));
        entity.setUid(cursor.getInt(offset + 5));
        entity.setPvid(cursor.getInt(offset + 6));
        entity.setType(cursor.getInt(offset + 7));
    }

    @Override
    protected void bindValues(DatabaseStatement stmt, Pictureversion entity) {
        stmt.clearBindings();
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
        stmt.bindString(3, entity.getIntroduction());
        stmt.bindString(4, entity.getCreatetime());
        stmt.bindLong(5,entity.getEid());
        stmt.bindLong(6,entity.getUid());
        stmt.bindLong(7, entity.getPvid());
        stmt.bindLong(8, entity.getType());
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, Pictureversion entity) {
        stmt.clearBindings();
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
        stmt.bindString(3, entity.getIntroduction());
        stmt.bindString(4, entity.getCreatetime());
        stmt.bindLong(5,entity.getEid());
        stmt.bindLong(6,entity.getUid());
        stmt.bindLong(7, entity.getPvid());
        stmt.bindLong(8, entity.getType());
    }

    @Override
    protected Long updateKeyAfterInsert(Pictureversion entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    protected Long getKey(Pictureversion entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean hasKey(Pictureversion entity) {
        return entity.getId() != null;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }

}
