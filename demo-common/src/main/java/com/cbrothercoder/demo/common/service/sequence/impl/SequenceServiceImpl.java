package com.cbrothercoder.demo.common.service.sequence.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.cbrothercoder.demo.common.service.sequence.SequenceEnum;
import com.cbrothercoder.demo.common.dao.mapper.manual.sequence.SequenceDao;
import com.cbrothercoder.demo.common.service.sequence.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author trydofor
 * @since 2016-12-22.
 */
@Service("sequenceService")
public class SequenceServiceImpl implements SequenceService {

    private final static int                            STEP_SIZE = 100;
    private final Object                                lock      = new Object();
    private final ConcurrentHashMap<String, AtomicLong> currentId = new ConcurrentHashMap<String, AtomicLong>();
    private final ConcurrentHashMap<String, Long>       triggerId = new ConcurrentHashMap<String, Long>();
    private SequenceDao sequenceDao;

    @Override
    public Long getSequence(final SequenceEnum table) {
        final String tableName = table.getTable();

        AtomicLong id = currentId.get(tableName);
        //
        if (id == null) {
            // 初始化seq
            synchronized (lock) {
                if (currentId.get(tableName) == null) {
                    long seqId = getSeqId(tableName);
                    // 放入内存
                    currentId.put(tableName, new AtomicLong(seqId));
                }
            }
            id = currentId.get(tableName);
        }
        //
        long seq = triggerId.get(tableName);
        long value = id.incrementAndGet();
        if (value > (triggerId.get(tableName) + 1) * STEP_SIZE) {
            synchronized (lock) {
                if (seq == triggerId.get(tableName)) {
                    id.set(getSeqId(tableName));
                }
            }
            return getSequence(table);
        }
        //
        return value;
    }

    private long getSeqId(String tableName) {
        long seq = sequenceDao.selectForUpdate(tableName);
        int upd = sequenceDao.updateIncrease(tableName);
        if (upd != 1) {
            throw new IllegalStateException("failed to increase sequence, table=" + tableName);
        }
        triggerId.put(tableName, seq);
        // 实际id为seqId扩张STEP_SIZE倍数
        return seq * STEP_SIZE;
    }

    // setter
    @Autowired
    public void setSequenceDao(SequenceDao sequenceDao) {
        this.sequenceDao = sequenceDao;
    }
}
