package com.dlion.testproject.blockingqueue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @author lizy
 * @version 1.0
 * @date Created in 2022年03月04日 15:47
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DelayMessage implements Delayed {

    private String moduleFid;

    private Integer type;

    private Integer operationType;

    private Long sendTimeInMs;

    DelayMessage(String moduleFid, Long sendTimeInMs){
        this.moduleFid = moduleFid;
        this.sendTimeInMs = sendTimeInMs;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.sendTimeInMs - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (o instanceof DelayMessage) {
            return Long.compare(this.getSendTimeInMs(), ((DelayMessage) o).getSendTimeInMs());
        }
        return 0;
    }
}
