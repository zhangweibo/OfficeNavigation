package com.richard.officenavigation.dao;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.richard.officenavigation.dao.INode;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table INODE.
*/
public class INodeDao extends AbstractDao<INode, Long> {

    public static final String TABLENAME = "INODE";

    /**
     * Properties of entity INode.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property X = new Property(1, long.class, "x", false, "X");
        public final static Property Y = new Property(2, long.class, "y", false, "Y");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
        public final static Property Visible = new Property(4, boolean.class, "visible", false, "VISIBLE");
        public final static Property MapId = new Property(5, long.class, "mapId", false, "MAP_ID");
    };

    private DaoSession daoSession;

    private Query<INode> iMap_NodesQuery;

    public INodeDao(DaoConfig config) {
        super(config);
    }
    
    public INodeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'INODE' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'X' INTEGER NOT NULL ," + // 1: x
                "'Y' INTEGER NOT NULL ," + // 2: y
                "'NAME' TEXT NOT NULL ," + // 3: name
                "'VISIBLE' INTEGER NOT NULL ," + // 4: visible
                "'MAP_ID' INTEGER NOT NULL );"); // 5: mapId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'INODE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, INode entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getX());
        stmt.bindLong(3, entity.getY());
        stmt.bindString(4, entity.getName());
        stmt.bindLong(5, entity.getVisible() ? 1l: 0l);
        stmt.bindLong(6, entity.getMapId());
    }

    @Override
    protected void attachEntity(INode entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public INode readEntity(Cursor cursor, int offset) {
        INode entity = new INode( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // x
            cursor.getLong(offset + 2), // y
            cursor.getString(offset + 3), // name
            cursor.getShort(offset + 4) != 0, // visible
            cursor.getLong(offset + 5) // mapId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, INode entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setX(cursor.getLong(offset + 1));
        entity.setY(cursor.getLong(offset + 2));
        entity.setName(cursor.getString(offset + 3));
        entity.setVisible(cursor.getShort(offset + 4) != 0);
        entity.setMapId(cursor.getLong(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(INode entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(INode entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "nodes" to-many relationship of IMap. */
    public List<INode> _queryIMap_Nodes(long mapId) {
        synchronized (this) {
            if (iMap_NodesQuery == null) {
                QueryBuilder<INode> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.MapId.eq(null));
                iMap_NodesQuery = queryBuilder.build();
            }
        }
        Query<INode> query = iMap_NodesQuery.forCurrentThread();
        query.setParameter(0, mapId);
        return query.list();
    }

}
