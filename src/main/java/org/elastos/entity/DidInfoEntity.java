/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.entity;

import java.util.List;
import java.util.Map;

/**
 * clark
 * <p>
 * 9/27/18
 */
public class DidInfoEntity<T> {

    private String privateKey;
    private T info;
    private List<String> txIds;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getInfo() {

        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public List<String> getTxIds() {
        return txIds;
    }

    public void setTxIds(List<String> txIds) {
        this.txIds = txIds;
    }
}
