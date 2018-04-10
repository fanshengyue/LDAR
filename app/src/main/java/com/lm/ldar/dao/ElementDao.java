package com.lm.ldar.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.lm.ldar.entity.CheckInfo;
import com.lm.ldar.entity.Element;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * Created by xieweikun on 2018/1/14.
 */

public class ElementDao extends AbstractDao<Element, Long> {

    public static final String TABLENAME = "ELEMENT";

    /**
     * Properties of entity VideoPlayEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        //序号
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        //类型
        public final static Property Type = new Property(1, String.class, "type", false, "TYPE");
        //创建时间
        public final static Property Datetime = new Property(2, String.class, "datetime", false, "DATETIME");
        //装置id
        public final static Property Did = new Property(3, Long.class, "did", true, "DID");
        //子区域id
        public final static Property Aid = new Property(4, Long.class, "aid", true, "AID");
        //任务taskuserid
        public final static Property Eid = new Property(5, Long.class, "eid", true, "EID");
        //计划id
        public final static Property Pid = new Property(6, Long.class, "pid", true, "PID");
        //密封点编号
        public final static Property text = new Property(7, String.class, "text", true, "TEXT");
        //检测方法
        public final static Property Method = new Property(8, String.class, "method", true, "METHOD");
        //组件编码
        public final static Property Number = new Property(9, String.class, "number", true, "NUMBER");
        //更新时间
        public final static Property updatetime = new Property(10, String.class, "updatetime", true, "UPDATETIME");
        //是否可达，1可达，2不可
        public final static Property Isreach = new Property(11, String.class, "isreach", true, "ISREACH");
        //PID图号
        public final static Property Pidnumber = new Property(12, String.class, "pidnumber", true, "PIDNUMBER");
        //图像版本ID
        public final static Property Pvid = new Property(13, Long.class, "pvid", true, "PVID");
    }


    public ElementDao(DaoConfig config) {
        super(config);
    }

    public ElementDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }
    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"ELEMENT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TYPE\" TEXT," + // 1: type
                "\"DATETIME\" TEXT," + // 2: datetime
                "\"DID\" INTEGER," + // 3: did
                "\"AID\" INTEGER," + //4:aid
                "\"EID\" INTEGER," + // 5: eid
                "\"PID\" INTEGER," + // 6: pid
                "\"TEXT\" TEXT," + // 7: text
                "\"METHOD\" TEXT," +//8.method
                "\"NUMBER\" TEXT," +//9.number
                "\"UPDATETIME\" TEXT," + //10.updatetime
                "\"ISREACH\" TEXT," +//11.isreach
                "\"PIDNUMBER\" TEXT," + //12.pidnumber
                "\"PVID\" INTEGER);");//13.pvid
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ELEMENT\"";
        db.execSQL(sql);
    }

    @Override
    protected Element readEntity(Cursor cursor, int offset) {
        Element element = new Element(
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getString(offset + 1), // type
                cursor.getString(offset + 2), // datetime
                cursor.getLong(offset + 3), // did
                cursor.getLong(offset + 4), // aid
                cursor.getLong(offset + 5), // eid
                cursor.getLong(offset + 6), // pid
                cursor.getString(offset + 7), // text
                cursor.getString(offset + 8),// method
                cursor.getString(offset + 9),// number
                cursor.getString(offset + 10),// updatetime
                cursor.getString(offset + 11), // isreach
                cursor.getString(offset + 12), // pidnumber
                cursor.getLong(offset + 13)// pvid
        );
        return element;
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected void readEntity(Cursor cursor, Element entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setType(cursor.getString(offset + 1));
        entity.setDatetime(cursor.getString(offset + 2));
        entity.setDid(cursor.getLong(offset + 3));
        entity.setAid(cursor.getLong(offset + 4));
        entity.setEid(cursor.getLong(offset + 5));
        entity.setPid(cursor.getLong(offset + 6));
        entity.setText(cursor.getString(offset + 7));
        entity.setMethod(cursor.getString(offset + 8));
        entity.setNumber(cursor.getString(offset + 9));
        entity.setUpdatetime(cursor.getString(offset + 10));
        entity.setIsreach(cursor.getString(offset + 11));
        entity.setPidnumber(cursor.getString(offset + 12));
        entity.setPvid(cursor.getLong(offset + 13));
    }

    @Override
    protected void bindValues(DatabaseStatement stmt, Element entity) {
        stmt.clearBindings();
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getType());
        stmt.bindString(3, entity.getDatetime());
        stmt.bindLong(4, entity.getDid());
        stmt.bindLong(5, entity.getAid());
        stmt.bindLong(6, entity.getEid());
        stmt.bindLong(7, entity.getPid());
        stmt.bindString(8, entity.getText());
        stmt.bindString(9,entity.getMethod());
        stmt.bindString(10,entity.getNumber());
        stmt.bindString(11,entity.getUpdatetime());
        stmt.bindString(12, entity.getIsreach());
        stmt.bindString(13, entity.getPidnumber());
        stmt.bindLong(14, entity.getPvid());

    }


    @Override
    protected void bindValues(SQLiteStatement stmt, Element entity) {
        stmt.clearBindings();
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getType());
        stmt.bindString(3, entity.getDatetime());
        stmt.bindLong(4, entity.getDid());
        stmt.bindLong(5, entity.getAid());
        stmt.bindLong(6, entity.getEid());
        stmt.bindLong(7, entity.getPid());
        stmt.bindString(8, entity.getText());
        stmt.bindString(9,entity.getMethod());
        stmt.bindString(10,entity.getNumber());
        stmt.bindString(11,entity.getUpdatetime());
        stmt.bindString(12, entity.getIsreach());
        stmt.bindString(13, entity.getPidnumber());
        stmt.bindLong(14, entity.getPvid());
    }

    @Override
    protected Long updateKeyAfterInsert(Element entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    protected Long getKey(Element entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean hasKey(Element entity) {
        return entity.getId() != null;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }

}
