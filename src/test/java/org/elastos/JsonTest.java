/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos;

import org.elastos.ela.Ela;
import org.elastos.ela.PayloadRegisterIdentification;
import org.elastos.ela.Util;
import org.elastos.ela.bitcoinj.Base58;
import org.elastos.util.JsonUtil;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;

/**
 * clark
 * <p>
 * 10/13/18
 */
public class JsonTest {


    @Test
    public void test(){

        String str = "{\n" +
                "\t\t\"ID\":\"igRn4VtAUB7hPkMrYMHt5f3xiQUMQJUFD2\",\n" +
                "\t\t\"Sign\":\"40bc9424152e20c20909909d87036a4f54e8e30e7ecce65b235e41dac2cf0ea8954c93453e6b275963baf77ea71470123f70a83053327d071ead86315e685e564b\",\n" +
                "\t\t\"Contents\":[\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"Path\":\"kyc/person/identityCard\",\n" +
                "\t\t\t\t\"Values\":[\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\"DataHash\":\"bd117820c4cf30b0ad9ce68fe92b0117ca41ac2b6a49235fabd793fc3a9413c0\",\n" +
                "\t\t\t\t\t\"Proof\":\"\"\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t]\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}";

        PayloadRegisterIdentification pri = JsonUtil.jsonStr2Entity(str,PayloadRegisterIdentification.class);

        System.out.println(pri);
    }

    @Test
    public void test02(){
        String payloadStr = "{\"Id\":\"igRn4VtAUB7hPkMrYMHt5f3xiQUMQJUFD2\",\"IdPrivKey\":\"C740869D015E674362B1F441E3EDBE1CBCF4FE8B709AA1A77E5CCA2C92BAF99D\",\"Sign\":\"40bc9424152e20c20909909d87036a4f54e8e30e7ecce65b235e41dac2cf0ea8954c93453e6b275963baf77ea71470123f70a83053327d071ead86315e685e564b\",\"Contents\":[{\"Path\":\"kyc/person/identityCard\",\"Values\":[{\"DataHash\":\"bd117820c4cf30b0ad9ce68fe92b0117ca41ac2b6a49235fabd793fc3a9413c0\",\"Proof\":\"\\\"signature\\\":\\\"30450220499a5de3f84e7e919c26b6a8543fd24129634c65ee4d38fe2e3386ec\\n 8a5dae57022100b7679de8d181a454e2def8f55de423e9e15bebcde5c58e871d20aa0d91162ff6\\\",\\\"notary\\n \\\":\\\"COOIX\\\"\"}]}]}";
        PayloadRegisterIdentification payload = null;
            payload = JsonUtil.jsonStr2Entity(payloadStr,PayloadRegisterIdentification.class);
            String privKey = payload.getIdPrivKey();
            String address = Ela.getAddressFromPrivate(privKey);
            String programHash = DatatypeConverter.printHexBinary(Util.ToScriptHash(address));
            payload.setProgramHash(programHash);
        System.out.println(1);
    }

    @Test
    public void test03(){

        byte str[] = "hellowolrdhellowolrd1".getBytes();

        byte str1[] = new byte[str.length + 4];

        System.arraycopy(str,0,str1,0,str.length);

        System.arraycopy(str,0 ,str1,str.length,4);

        System.out.println(Base58.encode(str1));
        System.out.println(Base58.encode(str));

    }
}
