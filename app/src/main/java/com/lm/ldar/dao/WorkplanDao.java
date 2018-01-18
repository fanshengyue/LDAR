package com.lm.ldar.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.lm.ldar.entity.Area;
import com.lm.ldar.entity.Workplan;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * Created by xieweikun on 2018/1/14.
 */

public class WorkplanDao extends AbstractDao<Workplan, Long> {

    public static final String TABLENAME = "WORKPLAN";

    /**
     * Properties of entity VideoPlayEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        //序号
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        //计划名称
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        //企业ID
        public final static Property Eid = new Property(2, int.class, "eid", false, "EID");
        //状态
        public final static Property State = new Property(3, int.class, "state", false, "STATE");
        //命名规则ID
        public final static Property Nid = new Property(4, int.class, "nid", false, "NID");
        //泄漏标准ID
        public final static Property Sid = new Property(5, int.class, "sid", false, "SID");
        //继承序号ID
        public final static Property Pvid = new Property(6, int.class, "pvid", false, "PVID");
    };

    public WorkplanDao(DaoConfig config) {
        super(config);
    }

    public WorkplanDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"WORKPLAN\" (" +
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"EID\" INTEGER," + //2.eid
                "\"STATE\" INTEGER," + //3.state
                "\"NID\" INTEGER," + //4.nid
                "\"SID\" INTEGER," + //5.sid
                "\"PVID\" INTEGER);");//6.pvid
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WORKPLAN\"";
        db.execSQL(sql);
    }

    @Override
    protected Workplan readEntity(Cursor cursor, int offset) {
        Workplan workplan = new Workplan(
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getString(offset + 1), // name
                cursor.getInt(offset + 2),// eid
                cursor.getInt(offset + 3),// state
                cursor.getInt(offset + 4),// nid
                cursor.getInt(offset + 5),// sid
                cursor.getInt(offset + 6)// pvid
        );
        return workplan;
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected void readEntity(Cursor cursor, Workplan entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setEid(cursor.getInt(offset + 2));
        entity.setState(cursor.getInt(offset + 3));
        entity.setNid(cursor.getInt(offset + 4));
        entity.setSid(cursor.getInt(offset + 5));
        entity.setPvid(cursor.getInt(offset + 6));
    }

    @Override
    protected void bindValues(DatabaseStatement stmt, Workplan entity) {
        stmt.clearBindings();
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
        stmt.bindLong(3,entity.getEid());
        stmt.bindLong(4,entity.getState());
        stmt.bindLong(5,entity.getNid());
        stmt.bindLong(6,entity.getSid());
        stmt.bindLong(7,entity.getPvid());
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, Workplan entity) {
        stmt.clearBindings();
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
        stmt.bindLong(3,entity.getEid());
        stmt.bindLong(4,entity.getState());
        stmt.bindLong(5,entity.getNid());
        stmt.bindLong(6,entity.getSid());
        stmt.bindLong(7,entity.getPvid());
    }

    @Override
    protected Long updateKeyAfterInsert(Workplan entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    protected Long getKey(Workplan entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean hasKey(Workplan entity) {
        return entity.getId() != null;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }

}
