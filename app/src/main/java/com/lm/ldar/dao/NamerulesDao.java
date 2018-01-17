package com.lm.ldar.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.lm.ldar.entity.Area;
import com.lm.ldar.entity.Namerules;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * Created by xieweikun on 2018/1/14.
 */

public class NamerulesDao extends AbstractDao<Namerules, Long> {

    public static final String TABLENAME = "NAMERULES";

    /**
     * Properties of entity VideoPlayEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        //序号
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        //企业ID
        public final static Property Eid = new Property(1, int.class, "eid", false, "EID");
        //命名规则名称
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        //阀门
        public final static Property Famen = new Property(3, String.class, "famen", false, "FAMEN");
        //泵
        public final static Property Beng = new Property(4, String.class, "beng", false, "BENG");
        //法兰
        public final static Property Falan = new Property(5, String.class, "falan", false, "FALAN");
        //压缩机
        public final static Property Yasuoji = new Property(6, String.class, "yasuoji", false, "YASUOJI");
        //搅拌器
        public final static Property Jiaoban = new Property(7, String.class, "jiaoban", false, "JIAOBAN");
        //泄压装置
        public final static Property Xieya = new Property(8, String.class, "xieya", false, "XIEYA");
        //连接件
        public final static Property Lianjie = new Property(9, String.class, "lianjie", false, "LIANJIE");
        //开口管线或开口阀
        public final static Property Kaikou = new Property(10, String.class, "kaikou", false, "KAIKOU");
        //采样连接系统
        public final static Property Caiyang = new Property(11, String.class, "caiyang", false, "CAIYANG");
        //其它
        public final static Property Other = new Property(12, String.class, "other", false, "OTHER");
    };

    public NamerulesDao(DaoConfig config) {
        super(config);
    }

    public NamerulesDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"NAMERULES\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"EID\" INTEGER," + //1.eid
                "\"NAME\" TEXT," + //2: name
                "\"FAMEN\" TEXT," + //3: famen
                "\"BENG\" TEXT," + //4: beng
                "\"FALAN\" TEXT," + //5: falan
                "\"YASUOJI\" TEXT," + //6: yasuoji
                "\"JIAOBAN\" TEXT," + //7: jiaoban
                "\"XIEYA\" TEXT," + //8: xieya
                "\"LIANJIE\" TEXT," + //9: lianjie
                "\"KAIKOU\" TEXT," + //10: kaikou
                "\"CAIYANG\" TEXT," + //11: caiyang
                "\"OTHER\" TEXT);");//12.other
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"NAMERULES\"";
        db.execSQL(sql);
    }

    @Override
    protected Namerules readEntity(Cursor cursor, int offset) {
        Namerules namerules = new Namerules(
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), //id
                cursor.getInt(offset + 1),//eid
                cursor.getString(offset + 2),//name
                cursor.getString(offset + 3),//famen
                cursor.getString(offset + 4),//beng
                cursor.getString(offset + 5),//falan
                cursor.getString(offset + 6),//yasuoji
                cursor.getString(offset + 7),//jiaoban
                cursor.getString(offset + 8),//xieya
                cursor.getString(offset + 9),//lianjie
                cursor.getString(offset + 10),//kaikou
                cursor.getString(offset + 11),//caiyang
                cursor.getString(offset + 12)//other
        );
        return namerules;
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected void readEntity(Cursor cursor, Namerules entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setEid(cursor.getInt(offset + 1));
        entity.setName(cursor.getString(offset + 2));
        entity.setFamen(cursor.getString(offset + 3));
        entity.setBeng(cursor.getString(offset + 4));
        entity.setFalan(cursor.getString(offset + 5));
        entity.setYasuoji(cursor.getString(offset + 6));
        entity.setJiaoban(cursor.getString(offset + 7));
        entity.setXieya(cursor.getString(offset + 8));
        entity.setLianjie(cursor.getString(offset + 9));
        entity.setKaikou(cursor.getString(offset + 10));
        entity.setCaiyang(cursor.getString(offset + 11));
        entity.setOther(cursor.getString(offset + 12));
    }

    @Override
    protected void bindValues(DatabaseStatement stmt, Namerules entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2,entity.getEid());
        stmt.bindString(3, entity.getName());
        stmt.bindString(4, entity.getFamen());
        stmt.bindString(5, entity.getBeng());
        stmt.bindString(6, entity.getFalan());
        stmt.bindString(7, entity.getYasuoji());
        stmt.bindString(8, entity.getJiaoban());
        stmt.bindString(9, entity.getXieya());
        stmt.bindString(10, entity.getLianjie());
        stmt.bindString(11, entity.getKaikou());
        stmt.bindString(12, entity.getCaiyang());
        stmt.bindString(13, entity.getOther());
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, Namerules entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2,entity.getEid());
        stmt.bindString(3, entity.getName());
        stmt.bindString(4, entity.getFamen());
        stmt.bindString(5, entity.getBeng());
        stmt.bindString(6, entity.getFalan());
        stmt.bindString(7, entity.getYasuoji());
        stmt.bindString(8, entity.getJiaoban());
        stmt.bindString(9, entity.getXieya());
        stmt.bindString(10, entity.getLianjie());
        stmt.bindString(11, entity.getKaikou());
        stmt.bindString(12, entity.getCaiyang());
        stmt.bindString(13, entity.getOther());
    }

    @Override
    protected Long updateKeyAfterInsert(Namerules entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    protected Long getKey(Namerules entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean hasKey(Namerules entity) {
        return entity.getId() != null;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }

}
