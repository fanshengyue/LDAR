package com.lm.ldar.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.lm.ldar.entity.Instrument;
import com.lm.ldar.entity.User;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * Created by fanshengyue on 2018/1/13.
 */

public class InstrumentDao extends AbstractDao<Instrument, Long> {
    public static final String TABLENAME = "INSTRUMENT";


    /**
     * Properties of entity VideoPlayEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Insname = new Property(1, String.class, "insname", false, "INSNAME");
        public final static Property Eid = new Property(2, Long.class, "eid", false, "EID");
    };


    public InstrumentDao(DaoConfig config) {
        super(config);
    }
    public InstrumentDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"INSTRUMENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"INSNAME\" TEXT NOT NULL," + // 1: insname
                "\"EID\" INTEGER);"); // 2: eid
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"INSTRUMENT\"";
        db.execSQL(sql);
    }

    @Override
    protected Instrument readEntity(Cursor cursor, int offset) {

        Instrument entity = new Instrument( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getString(offset + 1), // insname
                cursor.getLong(offset + 2) // eid
        );
        return entity;
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected void readEntity(Cursor cursor, Instrument entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setInsname(cursor.getString(offset + 1));
        entity.setEid(cursor.getLong(offset + 2));
    }

    @Override
    protected void bindValues(DatabaseStatement stmt, Instrument entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getInsname());
        stmt.bindLong(3, entity.getEid());
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, Instrument entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getInsname());
        stmt.bindLong(3, entity.getEid());
    }

    @Override
    protected Long updateKeyAfterInsert(Instrument entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    protected Long getKey(Instrument entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean hasKey(Instrument entity) {
        return entity.getId() != null;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }
}
