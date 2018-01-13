package com.lm.ldar.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.lm.ldar.entity.User;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * Created by fanshengyue on 2018/1/13.
 */

public class UserDao extends AbstractDao<User, Long> {
    public static final String TABLENAME = "USER";


    /**
     * Properties of entity VideoPlayEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Username = new Property(1, String.class, "username", false, "USERNAME");
        public final static Property Password = new Property(2, String.class, "password", false, "PASSWORD");
        public final static Property Truename = new Property(3, String.class, "truename", false, "TRUENAME");
        public final static Property Last_login_time = new Property(4, String.class, "lastlogintime", false, "LAST_LOGIN_TIME");
        public final static Property Last_login_ip = new Property(5, String.class, "lastloginip", false, "LAST_LOGIN_IP");
        public final static Property Valid = new Property(6, int.class, "valid", false, "VALID");
        public final static Property Create_time = new Property(7, String.class, "createtime", false, "CREATE_TIME");
        public final static Property Eid = new Property(8, String.class, "eid", false, "EID");
        public final static Property Login_times = new Property(9, int.class, "logintimes", false, "LOGIN_TIMES");
        public final static Property Phone = new Property(10, String.class, "phone", false, "PHONE");
        public final static Property Email = new Property(11, String.class, "email", false, "EMAIL");
        public final static Property User_groups = new Property(12, String.class, "usergroups", false, "USER_GROUPS");
    };


    public UserDao(DaoConfig config) {
        super(config);
    }
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"USERNAME\" TEXT NOT NULL," + // 1: username
                "\"PASSWORD\" TEXT NOT NULL," + // 2: password
                "\"TRUENAME\" TEXT," + // 3: truename
                "\"LAST_LOGIN_TIME\" TEXT," + // 4: last_login_time
                "\"LAST_LOGIN_IP\" TEXT," + // 5: last_login_ip
                "\"VALID\" INTEGER," + //6.valid
                "\"CREATE_TIME\" TEXT," + //7.create_time
                "\"EID\" TEXT," + //8.eid
                "\"LOGIN_TIMES\" INTEGER," + //9.login_times
                "\"PHONE\" TEXT," + //10.phone
                "\"EMAIL\" TEXT," + //11.email
                "\"USER_GROUPS\" TEXT);"); // 12: user_groups
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected User readEntity(Cursor cursor, int offset) {

        User entity = new User( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getString(offset + 1), // username
                cursor.getString(offset + 2), // password
                cursor.getString(offset + 3), // truename
                cursor.getString(offset + 4), // lastlogintime
                cursor.getString(offset + 5), // lastloginip
                cursor.getInt(offset + 6),// valid
                cursor.getString(offset + 7),// createtime
                cursor.getString(offset + 8),// eid
                cursor.getInt(offset + 9),// login_times
                cursor.getString(offset + 10),// phone
                cursor.getString(offset + 11),// email
                cursor.getString(offset + 12)// usergroups

        );
        return entity;
    }

    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    protected void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUsername(cursor.getString(offset + 1));
        entity.setPassword(cursor.getString(offset + 2));
        entity.setTruename(cursor.getString(offset + 3));
        entity.setLastlogintime(cursor.getString(offset + 4));
        entity.setLastloginip(cursor.getString(offset + 5));
        entity.setValid(cursor.getInt(offset + 6));
        entity.setCreatetime(cursor.getString(offset + 7));
        entity.setEid(cursor.getString(offset + 8));
        entity.setLogintimes(cursor.getInt(offset + 9));
        entity.setPhone(cursor.getString(offset + 10));
        entity.setEmail(cursor.getString(offset + 11));
        entity.setUsergroups(cursor.getString(offset + 12));
    }

    @Override
    protected void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getUsername());
        stmt.bindString(3, entity.getPassword());
        stmt.bindString(4, entity.getTruename());
        stmt.bindString(5, entity.getLastlogintime());
        stmt.bindString(6, entity.getLastloginip());
        stmt.bindLong(7, entity.getValid());
        stmt.bindString(8, entity.getCreatetime());
        stmt.bindString(9, entity.getEid());
        stmt.bindLong(10, entity.getLogintimes());
        stmt.bindString(11, entity.getPhone());
        stmt.bindString(12, entity.getEmail());
        stmt.bindString(13, entity.getUsergroups());
    }

    @Override
    protected void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getUsername());
        stmt.bindString(3, entity.getPassword());
        stmt.bindString(4, entity.getTruename());
        stmt.bindString(5, entity.getLastlogintime());
        stmt.bindString(6, entity.getLastloginip());
        stmt.bindLong(7, entity.getValid());
        stmt.bindString(8, entity.getCreatetime());
        stmt.bindString(9, entity.getEid());
        stmt.bindLong(10, entity.getLogintimes());
        stmt.bindString(11, entity.getPhone());
        stmt.bindString(12, entity.getEmail());
        stmt.bindString(13, entity.getUsergroups());
    }

    @Override
    protected Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    @Override
    protected Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected boolean hasKey(User entity) {
        return entity.getId() != null;
    }

    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }
}
