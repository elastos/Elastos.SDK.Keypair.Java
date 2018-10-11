package org.elastos.entity;

import java.util.List;

/**
 * 
 * @author clark
 * May 23, 2018 10:48:16 AM
 */
public class TransferParamEntity<T,V> {
	private V sender;
	private String memo;
	private T receiver;
	private ChainType type;

    public V getSender() {
        return sender;
    }

    public void setSender(V sender) {
        this.sender = sender;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public T getReceiver() {
        return receiver;
    }

    public void setReceiver(T receiver) {
        this.receiver = receiver;
    }

    public ChainType getType() {
        return type;
    }

    public void setType(ChainType type) {
        this.type = type;
    }
}