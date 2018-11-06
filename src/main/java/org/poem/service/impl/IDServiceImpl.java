package org.poem.service.impl;


import org.poem.service.IDWork;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author poem
 */
@Service
public class IDServiceImpl implements IDWork<Long> {

    private class Sequence {
        private final int value;
        private final long timestamp;

        Sequence(int value, long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }

        int getValue() {
            return value;
        }

        long getTimestamp() {
            return timestamp;
        }

        long getId() {
            return ((timestamp - TWEPOCH) << TIMESTAMP_SHIFT) | (instanceId << INSTANCE_ID_SHIFT) | value;
        }
    }
    private final long TWEPOCH = 1483200000000L;
    private final int INSTANCE_ID_BITS = 6;
    private final int SEQUENCE_BITS = 10;
    private final int INSTANCE_ID_SHIFT = SEQUENCE_BITS;
    private final int TIMESTAMP_SHIFT = SEQUENCE_BITS + INSTANCE_ID_BITS;
    private final int SEQUENCE_MASK = ~(-1 << SEQUENCE_BITS);

    private final AtomicReference<Sequence> sequence = new AtomicReference<Sequence>();
    private Integer instanceId;

    @PostConstruct
    public void init() {
        // 写死了实例ID为0，可通过Consul来自动分配实例号
        instanceId = 0;
    }

    @Override
    public Long getId() {
        Sequence currentSequence, nextSequence;
        do {
            currentSequence = sequence.get();
            long currentTimestamp = currentTimestamp();

            if (currentSequence == null || currentSequence.getTimestamp() < currentTimestamp) {
                nextSequence = new Sequence(0, currentTimestamp);
            } else if (currentSequence.getTimestamp() == currentTimestamp) {
                int nextValue = (currentSequence.getValue()) + 1 & SEQUENCE_MASK;
                if (nextValue == 0) {
                    currentTimestamp = waitForNextTimestamp();
                }
                nextSequence =  new Sequence(nextValue, currentTimestamp);
            } else {
                throw new RuntimeException(String.format(
                        "Clock is moving backwards. Rejecting requests for %d milliseconds.",
                        currentSequence.getTimestamp() - currentTimestamp));
            }
        } while(!sequence.compareAndSet(currentSequence, nextSequence));

        return nextSequence.getId();
    }


    private long currentTimestamp() {
        return System.currentTimeMillis();
    }

    private long waitForNextTimestamp() {
        while (currentTimestamp() <= sequence.get().getTimestamp()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
        }

        return currentTimestamp();
    }
}

