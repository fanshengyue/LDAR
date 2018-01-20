package com.lm.ldar.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.lm.ldar.entity.Area;
import com.lm.ldar.entity.Ctype;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * Created by xieweikun on 2018/1/14.
 */

public class CtypeDao extends AbstractDao<Ctype, Long> {

    public static final String TABLENAME = "CTYPE";

    /**
     * Properties of entity VideoPlayEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        //序号
        public final static Property Id = new Property(0, String.class, "id", true, "ID");
        //描述
        public final static Property Description = new Property(1, String.class, "description", false, "DESCRIPTION");
    };

    public CtypeDao(DaoConfig config) {
        super(config);
    }

    public CtypeDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CTYPE\" (" +
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ID\" TEXT ," + // 0: id
                "\"DESCRIPTION\" TEXT);");//1.description
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CTYPE\"";
        db.execSQL(sql);
    }

    @Override
    protected Ctype readEntity(Cursor cursor, int offset) {
        Ctype ctype = new Ctype(
                cursor.getString(offset+0),//id
                cursor.getString(offset+1)//description
        );
        return ctype;
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return null;
    }

    @Override
    protected void readEntity(Cursor cursor, Ctype entity, int offset) {
        entity.setId(cursor.getString(offset+0));
        entity.setDescription(cursor.getString(offset+1));
    }

    @Override
    protected void bindValues(DatabaseStatement stmt, Ctype entity) {
        stmt.clearBindings();
        stmt.bindString(1,entity.getId());
        stmt.bindString(2,entity.getDescription());
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, Ctype entity) {
        stmt.clearBindings();
        stmt.bindString(1,entity.getId());
        stmt.bindString(2,entity.getDescription());
    }

    @Override
    protected Long updateKeyAfterInsert(Ctype entity, long rowId) {
        return null;
    }

    @Override
    protected Long getKey(Ctype entity) {
        return null;
    }

    @Override
    protected boolean hasKey(Ctype entity) {
        return entity.getId() != null;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }

}
