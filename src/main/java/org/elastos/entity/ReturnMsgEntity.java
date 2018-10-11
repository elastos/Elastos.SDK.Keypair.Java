/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.entity;

/**
 * clark
 * <p>
 * 9/3/18
 */
public class ReturnMsgEntity<V> {
    private Long status;
    private V result;

    public Long getStatus() {
        return status;
    }

    public ReturnMsgEntity setStatus(Long status) {
        this.status = status;
        return this;
    }

    public V getResult() {
        return result;
    }

    public ReturnMsgEntity setResult(V result) {
        this.result = result;
        return this;
    }

    public static class ELAReturnMsg<T> {
        private String Desc;
        private Long Error;
        private T Result;

        public String getDesc() {
            return Desc;
        }

        public void setDesc(String desc) {
            this.Desc = desc;
        }

        public Long getError() {
            return Error;
        }

        public void setError(Long error) {
            error = error;
        }

        public T getResult() {
            return Result;
        }

        public void setResult(T result) {
            Result = result;
        }

    }

}
