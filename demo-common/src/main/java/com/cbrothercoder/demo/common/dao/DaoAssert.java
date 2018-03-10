package com.cbrothercoder.demo.common.dao;

import java.util.Date;

/**
 * @author trydofor
 * @since 2015-12-12.
 */
public class DaoAssert {

    public static <T> void insertOne(CommonDao<T> dao, T po) {
        int affected = dao.insert(po);
        if (affected != 1) {
            throw new IllegalStateException(affected + " records, failed to insert po=" + po);
        }
    }

    public static <T extends CommonPo> void updateOne(CommonDao<T> dao, T po, Date modifyTime, Long logno) {
        po.setLogno(logno);
        po.setModifyTime(modifyTime);
        int affected = dao.updateById(po);
        if (affected != 1) {
            throw new IllegalStateException(affected + " records, failed to update po=" + po);
        }
    }

    public static <T> void updateOne(CommonDao<T> dao, T po) {
        int affected = dao.updateById(po);
        if (affected != 1) {
            throw new IllegalStateException(affected + " records, failed to update po=" + po);
        }
    }

    public static <T extends CommonPo> void updateOneSelective(CommonDao<T> dao, T po, Date modifyTime, Long logno) {
        po.setLogno(logno);
        po.setModifyTime(modifyTime);
        int affected = dao.updateByIdSelective(po);
        if (affected != 1) {
            throw new IllegalStateException(affected + " records, failed to update po=" + po);
        }
    }

    public static <T> void updateOneSelective(CommonDao<T> dao, T po) {
        int affected = dao.updateByIdSelective(po);
        if (affected != 1) {
            throw new IllegalStateException(affected + " records, failed to update po=" + po);
        }
    }

    public static <T> void deleteOne(CommonDao<T> dao, Long id) {
        int affected = dao.deleteLogicById(id);
        if (affected != 1) {
            throw new IllegalStateException(affected + " records, failed to delete po=" + id);
        }
    }

    public static void insertOne(int affected, Object po) {
        if (affected != 1) {
            throw new IllegalStateException(affected + " records, failed to insert po=" + po);
        }
    }

    public static void updateOne(int affected, Object po) {
        if (affected != 1) {
            throw new IllegalStateException(affected + " records, failed to update po=" + po);
        }
    }

    public static void deleteOne(int affected, Object po) {
        if (affected != 1) {
            throw new IllegalStateException(affected + " records, failed to delete po=" + po);
        }
    }

    public static void affectOnly(int affected, int expected, Object obj) {
        if (affected != expected) {
            throw new IllegalStateException(affected + " records affected, but expect " + expected + ", " + obj);
        }
    }

    public static void affectLess(int affected, int expected, Object obj) {
        if (affected >= expected) {
            throw new IllegalStateException(affected + " records affected, but expect less than " + expected + ", " + obj);
        }
    }

    public static void affectMore(int affected, int expected, Object obj) {
        if (affected >= expected) {
            throw new IllegalStateException(affected + " records affected, but expect more than " + expected + ", " + obj);
        }
    }

    public static void affectNoLess(int affected, int expected, Object obj) {
        if (affected < expected) {
            throw new IllegalStateException(affected + " records affected, but expect no less than " + expected + ", " + obj);
        }
    }

    public static void affectNoMore(int affected, int expected, Object obj) {
        if (affected > expected) {
            throw new IllegalStateException(affected + " records affected, but expect no more than " + expected + ", " + obj);
        }
    }
}
