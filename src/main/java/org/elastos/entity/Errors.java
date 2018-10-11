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
 * 9/29/18
 */
public enum Errors {

    NOT_ENOUGH_UTXO("Not Enough UTXO"),

    DID_NO_SUCH_INFO("No such info"),

    ELA_ADDRESS_INVALID("address is not valid");

    private Errors(String str){
        this.str = str;
    }

    private String str;

    public String val(){
        return str;
    }

}
