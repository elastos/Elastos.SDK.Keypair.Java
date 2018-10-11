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
public class HdTxEntity {
    private String[] inputs;
    private Output[] outputs;
    private String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String[] getInputs() {
        return inputs;
    }

    public void setInputs(String[] inputs) {
        this.inputs = inputs;
    }

    public Output[] getOutputs() {
        return outputs;
    }

    public void setOutputs(Output[] outputs) {
        this.outputs = outputs;
    }

    public static class Output {
        private String addr;
        private Long amt;

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public Long getAmt() {
            return amt;
        }

        public void setAmt(Long amt) {
            this.amt = amt;
        }

    }
}
