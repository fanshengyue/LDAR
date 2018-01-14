package com.lm.ldar.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.lm.ldar.entity.Factory;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * Created by xieweikun on 2018/1/14.
 */

public class FactoryDao extends AbstractDao<Factory, Long> {

    /**
     * Properties of entity VideoPlayEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Number = new Property(1, String.class, "number", false, "NUMBER");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Create_time = new Property(5, String.class, "create_time", false, "CREATE_TIME");
        public final static Property Valid = new Property(6, int.class, "valid", false, "VALID");
        public final static Property Eid = new Property(9, int.class, "eid", false, "EID");
    };

    public FactoryDao(DaoConfig config) {
        super(config);
    }

    public FactoryDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"factory\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NUMBER\" TEXT NOT NULL," + // 1: number
                "\"NAME\" TEXT NOT NULL," + // 2: name
                "\"CREATE_TIME\" TEXT," + //3.create_time
                "\"VALID\" INTEGER," + //4.valid
                "\"EID\" INTEGER);");//5.eid
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"factory\"";
        db.execSQL(sql);
    }

    @Override
    protected Factory readEntity(Cursor cursor, int offset) {
        Factory factory = new Factory(
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getString(offset + 1), // number
                cursor.getString(offset + 2), // name
                cursor.getString(offset + 3), // create_time
                cursor.getInt(offset + 4),// valid
                cursor.getInt(offset + 5)// eid
        );
        return factory;
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected void readEntity(Cursor cursor, Factory entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNumber(cursor.getString(offset + 1));
        entity.setName(cursor.getString(offset + 2));
        entity.setCreate_time(cursor.getString(offset + 3));
        entity.setValid(cursor.getInt(offset + 4));
        entity.setEid(cursor.getInt(offset + 5));
    }

    @Override
    protected void bindValues(DatabaseStatement stmt, Factory entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNumber());
        stmt.bindString(3, entity.getName());
        stmt.bindString(4, entity.getCreate_time());
        stmt.bindLong(5, entity.getValid());
        stmt.bindLong(6,entity.getEid());
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, Factory entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNumber());
        stmt.bindString(3, entity.getName());
        stmt.bindString(4, entity.getCreate_time());
        stmt.bindLong(5, entity.getValid());
        stmt.bindLong(6,entity.getEid());
    }

    @Override
    protected Long updateKeyAfterInsert(Factory entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    protected Long getKey(Factory entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean hasKey(Factory entity) {
        return entity.getId() != null;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }
}
