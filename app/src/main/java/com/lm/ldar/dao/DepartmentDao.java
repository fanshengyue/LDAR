package com.lm.ldar.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.lm.ldar.entity.Area;
import com.lm.ldar.entity.Department;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * Created by xieweikun on 2018/1/14.
 */

public class DepartmentDao extends AbstractDao<Department, Long> {

    public static final String TABLENAME = "DEPARTMENT";

    /**
     * Properties of entity VideoPlayEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        //部门ID
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        //部门编号
        public final static Property Number = new Property(1, String.class, "number", false, "NUMBER");
        //部门名称
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        //创建时间
        public final static Property Createtime = new Property(3, String.class, "createtime", false, "CREATETIME");
        //厂区ID
        public final static Property Fid = new Property(4, int.class, "fid", false, "FID");
        //企业ID
        public final static Property Eid = new Property(5, int.class, "eid", false, "EID");
        //状态
        public final static Property Valid = new Property(6, int.class, "valid", false, "VALID");
    };

    public DepartmentDao(DaoConfig config) {
        super(config);
    }

    public DepartmentDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DEPARTMENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NUMBER\" TEXT," + // 1: number
                "\"NAME\" TEXT," + // 2: name
                "\"CREATETIME\" TEXT," + //3.createtime
                "\"FID\" INTEGER,"+//4.fid
                "\"EID\" INTEGER," + //5.eid
                "\"VALID\" INTEGER);");//6.valid
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DEPARTMENT\"";
        db.execSQL(sql);
    }

    @Override
    protected Department readEntity(Cursor cursor, int offset) {
        Department department = new Department(
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getString(offset + 1), // number
                cursor.getString(offset + 2), // name
                cursor.getString(offset + 3), // createtime
                cursor.getInt(offset + 4),// fid
                cursor.getInt(offset + 5),// eid
                cursor.getInt(offset + 6)// valid
        );
        return department;
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected void readEntity(Cursor cursor, Department entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNumber(cursor.getString(offset + 1));
        entity.setName(cursor.getString(offset + 2));
        entity.setCreatetime(cursor.getString(offset + 3));
        entity.setFid(cursor.getInt(offset + 4));
        entity.setEid(cursor.getInt(offset + 5));
        entity.setValid(cursor.getInt(offset + 6));
    }

    @Override
    protected void bindValues(DatabaseStatement stmt, Department entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNumber());
        stmt.bindString(3, entity.getName());
        stmt.bindString(4, entity.getCreatetime());
        stmt.bindLong(5,entity.getFid());
        stmt.bindLong(6,entity.getEid());
        stmt.bindLong(7, entity.getValid());
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, Department entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNumber());
        stmt.bindString(3, entity.getName());
        stmt.bindString(4, entity.getCreatetime());
        stmt.bindLong(5,entity.getFid());
        stmt.bindLong(6,entity.getEid());
        stmt.bindLong(7, entity.getValid());
    }

    @Override
    protected Long updateKeyAfterInsert(Department entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    protected Long getKey(Department entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean hasKey(Department entity) {
        return entity.getId() != null;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }




}
