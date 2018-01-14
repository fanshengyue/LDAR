package com.lm.ldar.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;


import com.lm.ldar.entity.Enterprise;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * Created by fanshengyue on 2018/1/13.
 */

public class EnterpriseDao extends AbstractDao<Enterprise, Long> {

    public static final String TABLENAME = "ENTERPRISE";

    /**
     * Properties of entity VideoPlayEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Ecode = new Property(1, String.class, "ecode", false, "ECODE");
        public final static Property Ename = new Property(2, String.class, "ename", false, "ENAME");
        public final static Property Legal_persion = new Property(3, String.class, "legalperson", false, "LEGAL_PERSION");
        public final static Property Industry = new Property(4, String.class, "industry", false, "INDUSTRY");
        public final static Property Create_time = new Property(5, String.class, "createtime", false, "CREATE_TIME");
        public final static Property Valid = new Property(6, int.class, "valid", false, "VALID");
        public final static Property Nid = new Property(7, int.class, "nid", false, "NID");
        public final static Property Ppid = new Property(8, int.class, "ppid", false, "PPID");
        public final static Property Epid = new Property(9, int.class, "epid", false, "EPID");
    };


    public EnterpriseDao(DaoConfig config) {
        super(config);
    }
    public EnterpriseDao(DaoConfig config, DaoSession daoSession) {
        super(config,daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ENTERPRISE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ECODE\" TEXT NOT NULL," + // 1: ecode
                "\"ENAME\" TEXT NOT NULL," + // 2: ename
                "\"LEGAL_PERSION\" TEXT," + // 3: legal_persion
                "\"INDUSTRY\" TEXT," + // 4: industry
                "\"CREATE_TIME\" TEXT," + //5.create_time
                "\"VALID\" INTEGER," + //6.valid
                "\"NID\" INTEGER," + //7.nid
                "\"PPID\" INTEGER," + //8.ppid
                "\"EPID\" INTEGER);");//9.epid
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ENTERPRISE\"";
        db.execSQL(sql);
    }

    @Override
    protected Enterprise readEntity(Cursor cursor, int offset) {
        Enterprise entity = new Enterprise( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getString(offset + 1), // ecode
                cursor.getString(offset + 2), // ename
                cursor.getString(offset + 3), // legal_persion
                cursor.getString(offset + 4), // industry
                cursor.getString(offset + 5), // create_time
                cursor.getInt(offset + 6),// valid
                cursor.getInt(offset + 7),// nid
                cursor.getInt(offset + 8),// ppid
                cursor.getInt(offset + 9)// epid

        );
        return entity;
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected void readEntity(Cursor cursor, Enterprise entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setEcode(cursor.getString(offset + 1));
        entity.setEcode(cursor.getString(offset + 2));
        entity.setLegalperson(cursor.getString(offset + 3));
        entity.setIndustry(cursor.getString(offset + 4));
        entity.setCreatetime(cursor.getString(offset + 5));
        entity.setValid(cursor.getInt(offset + 6));
        entity.setNid(cursor.getInt(offset + 7));
        entity.setPpid(cursor.getInt(offset + 8));
        entity.setEpid(cursor.getInt(offset + 9));
    }

    @Override
    protected void bindValues(DatabaseStatement stmt, Enterprise entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getEcode());
        stmt.bindString(3, entity.getEname());
        stmt.bindString(4, entity.getLegalperson());
        stmt.bindString(5, entity.getIndustry());
        stmt.bindString(6, entity.getCreatetime());
        stmt.bindLong(7, entity.getValid());
        stmt.bindLong(8, entity.getNid());
        stmt.bindLong(9, entity.getPpid());
        stmt.bindLong(10,entity.getEpid());
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, Enterprise entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getEcode());
        stmt.bindString(3, entity.getEname());
        stmt.bindString(4, entity.getLegalperson());
        stmt.bindString(5, entity.getIndustry());
        stmt.bindString(6, entity.getCreatetime());
        stmt.bindLong(7, entity.getValid());
        stmt.bindLong(8, entity.getNid());
        stmt.bindLong(9, entity.getPpid());
        stmt.bindLong(10,entity.getEpid());
    }

    @Override
    protected Long updateKeyAfterInsert(Enterprise entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    protected Long getKey(Enterprise entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean hasKey(Enterprise entity) {
        return entity.getId() != null;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }
}
