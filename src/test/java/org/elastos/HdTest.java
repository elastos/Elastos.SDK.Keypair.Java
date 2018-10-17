/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos;

import org.elastos.entity.HdWalletEntity;
import org.elastos.util.JsonUtil;
import org.elastos.util.ela.ElaHdSupport;
import org.junit.Test;

/**
 * clark
 * <p>
 * 10/17/18
 */
public class HdTest {

    @Test
    public void test01() throws Exception{
        String mnemonic = ElaHdSupport.generateMnemonic();
        String wallet   = ElaHdSupport.generate(mnemonic,0);
        String wallet1  = ElaHdSupport.generate(mnemonic,1);
        String wallet2  = ElaHdSupport.generate(mnemonic,2);
        System.out.println(wallet +"\n" + wallet1 +"\n" + wallet2);
    }

    @Test
    public void test02(){

        String str = "{\"mnemonic\":\"salute afford few tackle feel right sea member shop choose borrow mosquito\",\"index\":10}";

        HdWalletEntity entity = JsonUtil.jsonStr2Entity(str,HdWalletEntity.class);
        Integer i = entity.getStart();
        System.out.println();
    }
}
