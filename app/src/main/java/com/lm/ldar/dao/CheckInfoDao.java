package com.lm.ldar.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.lm.ldar.entity.CheckInfo;
import com.lm.ldar.entity.Picture;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

import java.nio.DoubleBuffer;

/**
 * Created by xieweikun on 2018/1/14.
 */

public class CheckInfoDao extends AbstractDao<CheckInfo, Long> {

    public static final String TABLENAME = "CHECKINFO";

    /**
     * Properties of entity VideoPlayEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        //序号
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        //日期
        public final static Property Date = new Property(1, String.class, "date", false, "DATE");
        //时间
        public final static Property Time = new Property(2, String.class, "time", false, "TIME");
        //媒介
        public final static Property Medium = new Property(3, String.class, "medium", false, "MEDIUM");
        //类型
        public final static Property Type = new Property(4, String.class, "type", false, "TYPE");
        //组件编号
        public final static Property Tag = new Property(5, String.class, "tag", false, "TAG");
        //背景检测浓度
        public final static Property Pbvalue = new Property(6, String.class, "pbvalue", false, "PBVALUE");
        //
        public final static Property Pbunit = new Property(7, String.class, "pbunit", false, "PBUNIT");
        //
        public final static Property Pbstate = new Property(8, String.class, "pbstate", false, "PBSTATE");
        //仪器检测浓度
        public final static Property Pcvalue = new Property(9, String.class, "pcvalue", false, "PCVALUE");
        //
        public final static Property Pcunit = new Property(10, String.class, "pcunit", false, "PCUNIT");
        //
        public final static Property Pcstate = new Property(11, String.class, "pcstate", false, "PCSTATE");
        //背景测浓度
        public final static Property Fbvalue = new Property(12, String.class, "fbvalue", false, "FBVALUE");
        //
        public final static Property Fbunit = new Property(13, String.class, "fbunit", false, "FBUNIT");
        //
        public final static Property Fbstate = new Property(14, String.class, "fbstate", false, "FBSTATE");
        //仪器检测浓度
        public final static Property Fcvalue = new Property(15, String.class, "fcvalue", false, "FCVALUE");
        //
        public final static Property Fcunit = new Property(16, String.class, "fcunit", false, "FCUNIT");
        //
        public final static Property Fcstate = new Property(17, String.class, "fcstate", false, "FCSTATE");
        //泄漏定义值
        public final static Property Define = new Property(18, String.class, "define", false, "DEFINE");
        //用户id
        public final static Property Uid = new Property(19, Long.class, "uid", true, "UID");
        //仪器id
        public final static Property Insid = new Property(20, Long.class, "insid", true, "INSID");
        //装置id
        public final static Property Did = new Property(21, Long.class, "did", true, "DID");
        //子区域id
        public final static Property Aid = new Property(22, Long.class, "aid", true, "AID");
        //任务taskuserid
        public final static Property Tid = new Property(23, Long.class, "tid", true, "TID");
        //计划id
        public final static Property Wid = new Property(24, Long.class, "wid", true, "WID");
        //标点id
        public final static Property Eleid = new Property(25, Long.class, "eleid", true, "ELEID");
        //运行天数
        public final static Property Days = new Property(26, Double.class, "days", true, "DAYS");
        //日均运行小时数
        public final static Property Horus = new Property(27, Double.class, "horus", true, "HORUS");
        //是否泄露
        public final static Property Isleak = new Property(28, Integer.class, "iseak", true, "ISLEAK");
        //泄漏量ppm
        public final static Property Leakagerate = new Property(29, Double.class, "leakagerate", true, "LEAKAGERATE");
        //平均排放系数，泄漏量ppm
        public final static Property Leakageratepjxs = new Property(30, Double.class, "leakageratepjxs", true, "LEAKAGERATEPJXS");
        //FID   PID，检测方式
        public final static Property Det = new Property(31, String.class, "det", true, "DET");
        //是否泄漏
        public final static Property Leak = new Property(32, String.class, "leak", true, "LEAK");
        //泄漏源
        public final static Property Leaksource = new Property(33, String.class, "leaksource", true, "LEAKSOURCE");
        //维修方式
        public final static Property Repairmethod = new Property(34, String.class, "repairmethod", true, "REPAIRMETHOD");
        //
        public final static Property Originalfcvalue = new Property(35, String.class, "originalfcvalue", true, "ORIGINALFCVALUE");
        //图像版本ID
        public final static Property Pvid = new Property(36, Long.class, "pvid", true, "PVID");
        //检测人员
        public final static Property Checkpeople = new Property(37, String.class, "checkpeople", true, "CHECKPEOPLE");
    }


    public CheckInfoDao(DaoConfig config) {
        super(config);
    }

    public CheckInfoDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }
    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHECKINFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"DATA\" TEXT," + // 1: data
                "\"TIME\" TEXT," + // 2: time
                "\"MEDIUM\" TEXT," + // 3: medium
                "\"TYPE\" TEXT," + //4:type
                "\"TAG\" TEXT," + // 5: tag
                "\"PBVALUE\" TEXT," + // 6: pbvalue
                "\"PBUNIT\" TEXT," + // 7: pbunit
                "\"PBSTATE\" TEXT," +//8.pbstate
                "\"PCVALUE\" TEXT," +//9.pcvalue
                "\"PCUNIT\" TEXT," + //10.pcunit
                "\"PCSTATE\" TEXT," +//11.pcstate
                "\"FBVALUE\" TEXT," + //12.fbvalue
                "\"FBUNIT\" TEXT," +//13.fbunit
                "\"FBSTATE\" TEXT," + //14.fbstate
                "\"FCVALUE\" TEXT," +//15.fcvalue
                "\"FCUNIT\" TEXT," + //16.fcunit
                "\"FCSTATE\" TEXT," +//17.fcstate
                "\"DEFINE\" TEXT," + //18.define
                "\"UID\" INTEGER," +//19.aid
                "\"INSID\" INTEGER," + //20.insid
                "\"DID\" INTEGER," + //21.did
                "\"AID\" INTEGER," + //22.aid
                "\"TID\" INTEGER," +//23.tid
                "\"WID\" INTEGER," + //24.wid
                "\"ELEID\" INTEGER," + //25.eleid
                "\"DAYS\" DOUBLE," + //26.days
                "\"HORUS\" DOUBLE," + //27.horus
                "\"ISLEAK\" INTEGER," + //28.isleak
                "\"LEAKAGERATE\" DOUBLE," + //29.leakagerate
                "\"LEAKAGERATEPJXS\" DOUBLE," + //30.leakageratepjxs
                "\"DET\" TEXT," + //31.det
                "\"LEAK\" TEXT," + //32.leak
                "\"LEAKSOURCE\" TEXT," + //33.leaksource
                "\"REPAIRMETHOD\" TEXT," + //34.repairmethod
                "\"ORIGINALFCVALUE\" TEXT," + //35.originalfcvalue
                "\"PVID\" INTEGER," + //36.pvid
                "\"CHECKPEOPLE\" TEXT);");//37.checkpeople
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHECKINFO\"";
        db.execSQL(sql);
    }

    @Override
    protected CheckInfo readEntity(Cursor cursor, int offset) {
        CheckInfo checkInfo = new CheckInfo(
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getString(offset + 1), // data
                cursor.getString(offset + 2), // time
                cursor.getString(offset + 3), // medium
                cursor.getString(offset + 4), // type
                cursor.getString(offset + 5), // tag
                cursor.getString(offset + 6), // pbvalue
                cursor.getString(offset + 7), // pbunit
                cursor.getString(offset + 8),// pbstate
                cursor.getString(offset + 9),// pcvalue
                cursor.getString(offset + 10),// pcunit
                cursor.getString(offset + 11), // pcstate
                cursor.getString(offset + 12), // fbvalue
                cursor.getString(offset + 13),// fbunit
                cursor.getString(offset + 14),// fbstate
                cursor.getString(offset+15),//fcvalue
                cursor.getString(offset+16),//fcunit
                cursor.getString(offset+17),//fcstate
                cursor.getString(offset+18),//define
                cursor.getLong(offset+19),//uid
                cursor.getLong(offset+20),//inid
                cursor.getLong(offset+21),//did
                cursor.getLong(offset+22),//aid
                cursor.getLong(offset+23),//tid
                cursor.getLong(offset+24),//wid
                cursor.getLong(offset+25),//eleid
                cursor.getDouble(offset+26),//days
                cursor.getDouble(offset+27),//horus
                cursor.getInt(offset+28),//isleak
                cursor.getDouble(offset+29),//leakagerate
                cursor.getDouble(offset+30),//leakageratepjxs
                cursor.getString(offset+31),//det
                cursor.getString(offset+32),//leak
                cursor.getString(offset+33),//leaksource
                cursor.getString(offset+34),//repairmethod
                cursor.getString(offset+35),//originalfcvalue
                cursor.getLong(offset+36),//pvid
                cursor.getString(offset+37)//checkpeople

        );
        return checkInfo;
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected void readEntity(Cursor cursor, CheckInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDate(cursor.getString(offset + 1));
        entity.setTime(cursor.getString(offset + 2));
        entity.setMedium(cursor.getString(offset + 3));
        entity.setType(cursor.getString(offset + 4));
        entity.setTag(cursor.getString(offset + 5));
        entity.setPbvalue(cursor.getString(offset + 6));
        entity.setPbunit(cursor.getString(offset + 7));
        entity.setPbstate(cursor.getString(offset + 8));
        entity.setPcvalue(cursor.getString(offset + 9));
        entity.setPcunit(cursor.getString(offset + 10));
        entity.setPcstate(cursor.getString(offset + 11));
        entity.setFbvalue(cursor.getString(offset + 12));
        entity.setFbunit(cursor.getString(offset + 13));
        entity.setFbstate(cursor.getString(offset + 14));
        entity.setFcvalue(cursor.getString(offset + 15));
        entity.setFcunit(cursor.getString(offset + 16));
        entity.setFcstate(cursor.getString(offset + 17));
        entity.setDefine(cursor.getString(offset + 18));
        entity.setUid(cursor.getLong(offset + 19));
        entity.setInsid(cursor.getLong(offset + 20));
        entity.setDid(cursor.getLong(offset + 21));
        entity.setAid(cursor.getLong(offset + 22));
        entity.setTid(cursor.getLong(offset + 23));
        entity.setWid(cursor.getLong(offset + 24));
        entity.setEleid(cursor.getLong(offset + 25));
        entity.setDays(cursor.getDouble(offset + 26));
        entity.setHorus(cursor.getDouble(offset + 27));
        entity.setIsleak(cursor.getInt(offset + 28));
        entity.setLeakagerate(cursor.getDouble(offset + 29));
        entity.setLeakageratepjxs(cursor.getDouble(offset + 30));
        entity.setDet(cursor.getString(offset + 31));
        entity.setLeak(cursor.getString(offset + 32));
        entity.setLeaksource(cursor.getString(offset + 33));
        entity.setRepairmethod(cursor.getString(offset + 34));
        entity.setOriginalfcvalue(cursor.getString(offset + 35));
        entity.setPvid(cursor.getLong(offset + 36));
        entity.setCheckpeople(cursor.getString(offset + 37));
    }

    @Override
    protected void bindValues(DatabaseStatement stmt, CheckInfo entity) {
        stmt.clearBindings();
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getDate());
        stmt.bindString(3, entity.getTime());
        stmt.bindString(4, entity.getMedium());
        stmt.bindString(5, entity.getType());
        stmt.bindString(6, entity.getTag());
        stmt.bindString(7, entity.getPbvalue());
        stmt.bindString(8, entity.getPbunit());
        stmt.bindString(9,entity.getPbstate());
        stmt.bindString(10,entity.getPcvalue());
        stmt.bindString(11,entity.getPcunit());
        stmt.bindString(12, entity.getPcstate());
        stmt.bindString(13, entity.getFbvalue());
        stmt.bindString(14, entity.getFbunit());
        stmt.bindString(15, entity.getFbstate());
        stmt.bindString(16,entity.getFcvalue());
        stmt.bindString(17,entity.getFcunit());
        stmt.bindString(18,entity.getFcstate());
        stmt.bindString(19,entity.getDefine());
        stmt.bindLong(20,entity.getUid());
        stmt.bindLong(21,entity.getInsid());
        stmt.bindLong(22,entity.getDid());
        stmt.bindLong(23,entity.getAid());
        stmt.bindLong(24,entity.getTid());
        stmt.bindLong(25,entity.getWid());
        stmt.bindLong(26,entity.getEleid());
        stmt.bindDouble(27,entity.getDays());
        stmt.bindDouble(28,entity.getHorus());
        stmt.bindLong(29,entity.getIsleak());
        stmt.bindDouble(30,entity.getLeakagerate());
        stmt.bindDouble(31,entity.getLeakageratepjxs());
        stmt.bindString(32,entity.getDet());
        stmt.bindString(33,entity.getLeak());
        stmt.bindString(34,entity.getLeaksource());
        stmt.bindString(35,entity.getRepairmethod());
        stmt.bindString(36,entity.getOriginalfcvalue());
        stmt.bindLong(37,entity.getPvid());
        stmt.bindString(38,entity.getCheckpeople());


    }


    @Override
    protected void bindValues(SQLiteStatement stmt, CheckInfo entity) {
        stmt.clearBindings();
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getDate());
        stmt.bindString(3, entity.getTime());
        stmt.bindString(4, entity.getMedium());
        stmt.bindString(5, entity.getType());
        stmt.bindString(6, entity.getTag());
        stmt.bindString(7, entity.getPbvalue());
        stmt.bindString(8, entity.getPbunit());
        stmt.bindString(9,entity.getPbstate());
        stmt.bindString(10,entity.getPcvalue());
        stmt.bindString(11,entity.getPcunit());
        stmt.bindString(12, entity.getPcstate());
        stmt.bindString(13, entity.getFbvalue());
        stmt.bindString(14, entity.getFbunit());
        stmt.bindString(15, entity.getFbstate());
        stmt.bindString(16,entity.getFcvalue());
        stmt.bindString(17,entity.getFcunit());
        stmt.bindString(18,entity.getFcstate());
        stmt.bindString(19,entity.getDefine());
        stmt.bindLong(20,entity.getUid());
        stmt.bindLong(21,entity.getInsid());
        stmt.bindLong(22,entity.getDid());
        stmt.bindLong(23,entity.getAid());
        stmt.bindLong(24,entity.getTid());
        stmt.bindLong(25,entity.getWid());
        stmt.bindLong(26,entity.getEleid());
        stmt.bindDouble(27,entity.getDays());
        stmt.bindDouble(28,entity.getHorus());
        stmt.bindLong(29,entity.getIsleak());
        stmt.bindDouble(30,entity.getLeakagerate());
        stmt.bindDouble(31,entity.getLeakageratepjxs());
        stmt.bindString(32,entity.getDet());
        stmt.bindString(33,entity.getLeak());
        stmt.bindString(34,entity.getLeaksource());
        stmt.bindString(35,entity.getRepairmethod());
        stmt.bindString(36,entity.getOriginalfcvalue());
        stmt.bindLong(37,entity.getPvid());
        stmt.bindString(38,entity.getCheckpeople());
    }

    @Override
    protected Long updateKeyAfterInsert(CheckInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    protected Long getKey(CheckInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean hasKey(CheckInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }

}
