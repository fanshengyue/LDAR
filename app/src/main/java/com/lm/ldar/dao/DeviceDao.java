package com.lm.ldar.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.lm.ldar.entity.Area;
import com.lm.ldar.entity.Device;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * Created by xieweikun on 2018/1/14.
 */

public class DeviceDao extends AbstractDao<Device, Long> {

    public static final String TABLENAME = "DEVICE";

    /**
     * Properties of entity VideoPlayEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        //装置ID
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        //装置编号
        public final static Property Number = new Property(1, String.class, "number", false, "NUMBER");
        //装置名称
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        //创建时间
        public final static Property Createtime = new Property(3, String.class, "createtime", false, "CREATETIME");
        //部门ID
        public final static Property Did = new Property(4, int.class, "did", false, "DID");
        //企业ID
        public final static Property Eid = new Property(5, int.class, "eid", false, "EID");
        //投产日期
        public final static Property Productiontime = new Property(6,String.class,"productiontime",false,"PRODUCTIONTIME");
        //状态
        public final static Property Valid = new Property(7, int.class, "valid", false, "VALID");
        //检测人员防护措施
        public final static Property Safeguard = new Property(8,String.class,"safeguard",false,"SAFEGUARD");
    };

    public DeviceDao(DaoConfig config) {
        super(config);
    }

    public DeviceDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DEVICE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NUMBER\" TEXT," + // 1: number
                "\"NAME\" TEXT," + // 2: name
                "\"CREATETIME\" TEXT," + //3.createtime
                "\"DID\" INTEGER,"+//4.did
                "\"EID\" INTEGER," + //5.eid
                "\"PRODUCTIONTIME\" TEXT,"+//6.productiontime
                "\"VALID\" INTEGER,"+//7.valid
                "\"SAFEGUARD\" TEXT);");//8.safeguard
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DEVICE\"";
        db.execSQL(sql);
    }

    @Override
    protected Device readEntity(Cursor cursor, int offset) {
        Device device = new Device(
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getString(offset + 1), // number
                cursor.getString(offset + 2), // name
                cursor.getString(offset + 3), // createtime
                cursor.getInt(offset + 4),// did
                cursor.getInt(offset + 5),// eid
                cursor.getString(offset + 6),// productiontime
                cursor.getInt(offset + 7),// valid
                cursor.getString(offset + 8)// safeguard
        );
        return device;
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected void readEntity(Cursor cursor, Device entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNumber(cursor.getString(offset + 1));
        entity.setName(cursor.getString(offset + 2));
        entity.setCreatetime(cursor.getString(offset + 3));
        entity.setDid(cursor.getInt(offset + 4));
        entity.setEid(cursor.getInt(offset + 5));
        entity.setProductiontime(cursor.getString(offset+6));
        entity.setValid(cursor.getInt(offset + 7));
        entity.setSafeguard(cursor.getString(offset+8));
    }

    @Override
    protected void bindValues(DatabaseStatement stmt, Device entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNumber());
        stmt.bindString(3, entity.getName());
        stmt.bindString(4, entity.getCreatetime());
        stmt.bindLong(5,entity.getDid());
        stmt.bindLong(6,entity.getEid());
        stmt.bindString(7,entity.getProductiontime());
        stmt.bindLong(8, entity.getValid());
        stmt.bindString(9,entity.getSafeguard());
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, Device entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNumber());
        stmt.bindString(3, entity.getName());
        stmt.bindString(4, entity.getCreatetime());
        stmt.bindLong(5,entity.getDid());
        stmt.bindLong(6,entity.getEid());
        stmt.bindString(7,entity.getProductiontime());
        stmt.bindLong(8, entity.getValid());
        stmt.bindString(9,entity.getSafeguard());
    }

    @Override
    protected Long updateKeyAfterInsert(Device entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    protected Long getKey(Device entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean hasKey(Device entity) {
        return entity.getId() != null;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }

}
