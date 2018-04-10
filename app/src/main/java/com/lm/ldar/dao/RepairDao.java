package com.lm.ldar.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.lm.ldar.entity.Repair;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * Created by xieweikun on 2018/1/14.
 */

public class RepairDao extends AbstractDao<Repair, Long> {

    public static final String TABLENAME = "REPAIR";

    /**
     * Properties of entity VideoPlayEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        //序号
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        //组件编号
        public final static Property Tag = new Property(1, String.class, "tag", false, "TAG");
        //背景检测浓度
        public final static Property Pbvalue = new Property(2, String.class, "pbvalue", false, "PBVALUE");
        //第一次修复人员姓名
        public final static Property Firstname = new Property(3, String.class, "firstname", false, "FIRSTNAME");
        //第一次修复时间
        public final static Property Firsttime = new Property(4, String.class, "firsttime", false, "FIRSTTIME");
        //第一次修复方式
        public final static Property Firstmethod = new Property(5, String.class, "firstmethod", false, "FIRSTMETHOD");
        //第一次修复后浓度
        public final static Property Firstreplay = new Property(6, String.class, "firstreplay", false, "FIRSTREPLAY");
        //第一次修复后每分钟液滴数
        public final static Property Firstdropnumber = new Property(7, String.class, "firstdropnumber", false, "FIRSTDROPNUMBER");
        //第二次修复人员姓名
        public final static Property Secname = new Property(8, String.class, "secname", false, "SECNAME");
        //第二次修复时间
        public final static Property Sectime = new Property(9, String.class, "sectime", false, "SECTIME");
        //第二次修复方式
        public final static Property Secmethod = new Property(10, String.class, "secmethod", false, "SECMETHOD");
        //第二次修复后浓度
        public final static Property Secreplay = new Property(11, String.class, "secreplay", false, "SECREPLAY");
        //第二次修复后每分钟液滴数
        public final static Property Secdropnumber = new Property(12, String.class, "secdropnumber", false, "SECDROPNUMBER");
        //仪器检测浓度
        public final static Property Fcvalue = new Property(13, String.class, "fcvalue", false, "FCVALUE");
        //泄漏定义值
        public final static Property Define = new Property(14, String.class, "define", false, "DEFINE");
        //是否修复成功
        public final static Property Issuccess = new Property(15, Integer.class, "issuccess", true, "ISSUCCESS");
        //延迟原因
        public final static Property Delayreason = new Property(16, String.class, "delayreason", true, "DELAYREASON");
        //是否延期修复
        public final static Property Isdelay = new Property(17, Integer.class, "isdelay", true, "ISDELAY");
        //
        public final static Property Uid = new Property(18, Long.class, "uid", true, "UID");
        //检测人员
        public final static Property Checkpeople = new Property(19, String.class, "checkpeople", true, "CHECKPEOPLE");
        //装置id
        public final static Property Did = new Property(20, Long.class, "did", true, "DID");
        //子区域id
        public final static Property Aid = new Property(21, Long.class, "aid", true, "AID");
        //任务taskuserid
        public final static Property Tid = new Property(22, Long.class, "tid", true, "TID");
        //图片id
        public final static Property Pid = new Property(23, Long.class, "pid", true, "PID");
        //计划id
        public final static Property Wid = new Property(24, Long.class, "wid", true, "WID");
        //标点id
        public final static Property Eleid = new Property(25, Long.class, "eleid", true, "ELEID");
        //泄漏量ppm
        public final static Property Leakagerate = new Property(26, Double.class, "leakagerate", true, "LEAKAGERATE");
        //平均排放系数，泄漏量ppm
        public final static Property Repairleakagerate = new Property(27, Double.class, "repairleakagerate", true, "REPAIRLEAKAGERATE");
        //图像版本ID
        public final static Property Pvid = new Property(28, Long.class, "pvid", true, "PVID");
        //第一次检测人员
        public final static Property Firstcheckpeople = new Property(29, String.class, "firstcheckpeople", true, "FIRSTCHECKPEOPLE");
        //第二次检测人员
        public final static Property Seccheckpeople = new Property(30, String.class, "seccheckpeople", true, "SECCHECKPEOPLE");
    }


    public RepairDao(DaoConfig config) {
        super(config);
    }

    public RepairDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }
    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"REPAIR\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TAG\" TEXT," + // 1: tag
                "\"PBVALUE\" TEXT," + // 2: pbvalue
                "\"FIRSTNAME\" TEXT," + // 3: firstname
                "\"FIRSTTIME\" TEXT," + //4:firstmethod
                "\"FIRSTMETHOD\" TEXT," + // 5: firstmethod
                "\"FIRSTREPLAY\" TEXT," + // 6: firstreplay
                "\"FIRSTDROPNUMBER\" TEXT," + // 7: firstdropnumber
                "\"SECNAME\" TEXT," +//8.secname
                "\"SECTIME\" TEXT," +//9.sectime
                "\"SECMETHOD\" TEXT," + //10.secmethos
                "\"SECREPLAY\" TEXT," +//11.secreplay
                "\"SECDROPNUMBER\" TEXT," + //12.secdropnumber
                "\"FCVALUE\" TEXT," +//13.fcvalue
                "\"DEFINE\" TEXT," + //14.define
                "\"ISSUCCESS\" INTEGER," +//15.issuccess
                "\"DELAYREASON\" TEXT," + //16.delayreason
                "\"ISDELAY\" INTEGER," +//17.isdelay
                "\"UID\" INTEGER," + //18.uid
                "\"CHECKTIME\" TEXT," +//19.checktime
                "\"DID\" INTEGER," + //20.did
                "\"AID\" INTEGER," + //21.aid
                "\"TID\" INTEGER," +//21.tid
                "\"PID\" INTEGER," + //23.pid
                "\"WID\" INTEGER," + //24.wid
                "\"ELEID\" INTEGER," + //25.eleid
                "\"LEAKAGERATE\" DOUBLE," + //26.leakagerate
                "\"REPAIRLEAKAGERATE\" DOUBLE," + //27.repairleakagerate
                "\"PVID\" INTEGER," + //28.pvid
                "\"FIRSTCHECKPEOPLE\" TEXT," + //29.originalfcvalue
                "\"SECCHECKPEOPLE\" TEXT);");//30.seccheckpeople
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"REPAIR\"";
        db.execSQL(sql);
    }

    @Override
    protected Repair readEntity(Cursor cursor, int offset) {
        Repair repair = new Repair(
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getString(offset + 1), // tag
                cursor.getString(offset + 2), // pbvalue
                cursor.getString(offset + 3), // firstname
                cursor.getString(offset + 4), // firsttime
                cursor.getString(offset + 5), // firstmethod
                cursor.getString(offset + 6), // firstreplay
                cursor.getString(offset + 7), // firstdropnumber
                cursor.getString(offset + 8),// secname
                cursor.getString(offset + 9),// sectime
                cursor.getString(offset + 10),// secmethod
                cursor.getString(offset + 11), // secreplay
                cursor.getString(offset + 12), // secdropnumber
                cursor.getString(offset + 13),// fcvalue
                cursor.getString(offset + 14),// define
                cursor.getInt(offset+15),//issuccess
                cursor.getString(offset+16),//delayreason
                cursor.getInt(offset+17),//isdelay
                cursor.getLong(offset+18),//uid
                cursor.getString(offset+19),//checktime
                cursor.getLong(offset+20),//did
                cursor.getLong(offset+21),//aid
                cursor.getLong(offset+22),//tid
                cursor.getLong(offset+23),//pid
                cursor.getLong(offset+24),//wid
                cursor.getLong(offset+25),//eleid
                cursor.getDouble(offset+26),//leakagerate
                cursor.getDouble(offset+27),//repairleakagerate
                cursor.getLong(offset+28),//pvid
                cursor.getString(offset+29),//29.originalfcvalue
                cursor.getString(offset+30)//seccheckpeople

        );
        return repair;
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected void readEntity(Cursor cursor, Repair entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTag(cursor.getString(offset + 1));
        entity.setPbvalue(cursor.getString(offset + 2));
        entity.setFirstname(cursor.getString(offset + 3));
        entity.setFirsttime(cursor.getString(offset + 4));
        entity.setFirstmethod(cursor.getString(offset + 5));
        entity.setFirstreplay(cursor.getString(offset + 6));
        entity.setFirstdropnumber(cursor.getString(offset + 7));
        entity.setSecname(cursor.getString(offset + 8));
        entity.setSectime(cursor.getString(offset + 9));
        entity.setSecmethod(cursor.getString(offset + 10));
        entity.setSecreplay(cursor.getString(offset + 11));
        entity.setSecdropnumber(cursor.getString(offset + 12));
        entity.setFcvalue(cursor.getString(offset + 13));
        entity.setDefine(cursor.getString(offset + 14));
        entity.setIssuccess(cursor.getInt(offset + 15));
        entity.setDelayreason(cursor.getString(offset + 16));
        entity.setIsdelay(cursor.getInt(offset + 17));
        entity.setUid(cursor.getLong(offset + 18));
        entity.setChecktime(cursor.getString(offset + 19));
        entity.setDid(cursor.getLong(offset + 20));
        entity.setAid(cursor.getLong(offset + 21));
        entity.setTid(cursor.getLong(offset + 22));
        entity.setPid(cursor.getLong(offset + 23));
        entity.setWid(cursor.getLong(offset + 24));
        entity.setEleid(cursor.getLong(offset + 25));
        entity.setLeakagerate(cursor.getDouble(offset + 26));
        entity.setRepairleakagerate(cursor.getDouble(offset + 27));
        entity.setPvid(cursor.getLong(offset + 28));
        entity.setFirstcheckpeople(cursor.getString(offset + 29));
        entity.setSeccheckpeople(cursor.getString(offset + 30));
    }
    @Override
    protected void bindValues(DatabaseStatement stmt, Repair entity) {
        stmt.clearBindings();
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getTag());
        stmt.bindString(3, entity.getPbvalue());
        stmt.bindString(4, entity.getFirstname());
        stmt.bindString(5, entity.getFirsttime());
        stmt.bindString(6, entity.getFirstmethod());
        stmt.bindString(7, entity.getFirstreplay());
        stmt.bindString(8, entity.getFirstdropnumber());
        stmt.bindString(9,entity.getSecname());
        stmt.bindString(10,entity.getSectime());
        stmt.bindString(11,entity.getSecmethod());
        stmt.bindString(12, entity.getSecreplay());
        stmt.bindString(13, entity.getSecdropnumber());
        stmt.bindString(14, entity.getFcvalue());
        stmt.bindString(15, entity.getDefine());
        stmt.bindLong(16,entity.getIssuccess());
        stmt.bindString(17,entity.getDelayreason());
        stmt.bindLong(18,entity.getIsdelay());
        stmt.bindLong(19,entity.getUid());
        stmt.bindString(20,entity.getChecktime());
        stmt.bindLong(21,entity.getDid());
        stmt.bindLong(22,entity.getAid());
        stmt.bindLong(23,entity.getTid());
        stmt.bindLong(24,entity.getPid());
        stmt.bindLong(25,entity.getWid());
        stmt.bindLong(26,entity.getEleid());
        stmt.bindDouble(27,entity.getLeakagerate());
        stmt.bindDouble(28,entity.getRepairleakagerate());
        stmt.bindString(29,entity.getFirstcheckpeople());
        stmt.bindString(30,entity.getSeccheckpeople());
    }


    @Override
    protected void bindValues(SQLiteStatement stmt, Repair entity) {
        stmt.clearBindings();
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getTag());
        stmt.bindString(3, entity.getPbvalue());
        stmt.bindString(4, entity.getFirstname());
        stmt.bindString(5, entity.getFirsttime());
        stmt.bindString(6, entity.getFirstmethod());
        stmt.bindString(7, entity.getFirstreplay());
        stmt.bindString(8, entity.getFirstdropnumber());
        stmt.bindString(9,entity.getSecname());
        stmt.bindString(10,entity.getSectime());
        stmt.bindString(11,entity.getSecmethod());
        stmt.bindString(12, entity.getSecreplay());
        stmt.bindString(13, entity.getSecdropnumber());
        stmt.bindString(14, entity.getFcvalue());
        stmt.bindString(15, entity.getDefine());
        stmt.bindLong(16,entity.getIssuccess());
        stmt.bindString(17,entity.getDelayreason());
        stmt.bindLong(18,entity.getIsdelay());
        stmt.bindLong(19,entity.getUid());
        stmt.bindString(20,entity.getChecktime());
        stmt.bindLong(21,entity.getDid());
        stmt.bindLong(22,entity.getAid());
        stmt.bindLong(23,entity.getTid());
        stmt.bindLong(24,entity.getPid());
        stmt.bindLong(25,entity.getWid());
        stmt.bindLong(26,entity.getEleid());
        stmt.bindDouble(27,entity.getLeakagerate());
        stmt.bindDouble(28,entity.getRepairleakagerate());
        stmt.bindString(29,entity.getFirstcheckpeople());
        stmt.bindString(30,entity.getSeccheckpeople());
    }

    @Override
    protected Long updateKeyAfterInsert(Repair entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    protected Long getKey(Repair entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean hasKey(Repair entity) {
        return entity.getId() != null;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }

}
