/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.util.ela;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.elastos.api.Basic;
import org.elastos.common.ErrorCode;
import org.elastos.ela.*;

import java.util.LinkedHashMap;

/**
 * clark
 * <p>
 * 9/27/18
 */
public class ElaKit {

    public static String genRawTransaction(JSONObject inputsAddOutpus) {
        try {
            JSONArray transaction = inputsAddOutpus.getJSONArray("Transactions");
            JSONObject json_transaction = (JSONObject)transaction.get(0);
            JSONArray utxoInputs = json_transaction.getJSONArray("UTXOInputs");
            JSONArray outputs = json_transaction.getJSONArray("Outputs");
            UTXOTxInput[] utxoTxInputs = (UTXOTxInput[])Basic.parseInputs(utxoInputs).toArray(new UTXOTxInput[utxoInputs.size()]);
            TxOutput[] txOutputs = (TxOutput[])Basic.parseOutputs(outputs).toArray(new TxOutput[outputs.size()]);
            PayloadRecord payload = Basic.parsePayloadRecord(json_transaction);
            boolean bool = json_transaction.has("Memo");
            LinkedHashMap<String, Object> resultMap = new LinkedHashMap();
            new RawTx("", "");
            if (payload != null && bool) {
                return ErrorCode.ParamErr("PayloadRecord And Memo can't be used at the same time");
            } else {
                RawTx rawTx;
                if (payload == null && bool == false) {
                    rawTx = Ela.makeAndSignTx(utxoTxInputs, txOutputs);
                } else if (bool ) {
                    String memo = json_transaction.getString("Memo");
                    rawTx = Ela.makeAndSignTx(utxoTxInputs, txOutputs, memo);
                } else {
                    rawTx = Ela.makeAndSignTx(utxoTxInputs, txOutputs, payload);
                }

                resultMap.put("rawTx", rawTx.getRawTxString());
                resultMap.put("txHash", rawTx.getTxHash());
                return Basic.getSuccess("genRawTransaction", resultMap);
            }
        } catch (Exception var12) {
            return var12.toString();
        }
    }

    /**
     * check if address is valid.
     * @param addr
     * @return
     */
    public static boolean checkAddress(String addr) {
        return Util.checkAddress(addr);
    }

}
