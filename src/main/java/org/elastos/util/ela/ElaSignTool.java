/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.util.ela;


import org.elastos.ela.ECKey;
import org.elastos.ela.SignTool;
import org.elastos.ela.bitcoinj.Sha256Hash;
import org.spongycastle.asn1.sec.SECNamedCurves;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.signers.ECDSASigner;
import org.spongycastle.crypto.signers.HMacDSAKCalculator;

import java.math.BigInteger;

/**
 * clark
 * <p>
 * 9/26/18
 */
public class ElaSignTool extends SignTool {

    private static final String CURVE_ALGO = "secp256r1";

    /**
     * verify private key signed message
     * @param msg
     * @param sig
     * @param pub
     * @return
     */
    public static boolean verify(byte[] msg, byte[] sig, byte[] pub) {
        if(sig.length != 64 ) {
            return false;
        }
        byte rb[] = new byte[sig.length/2];
        byte sb[] = new byte[sig.length/2];
        System.arraycopy(sig, 0, rb, 0, rb.length);
        System.arraycopy(sig, sb.length, sb, 0, sb.length);
        BigInteger r =  parseBigIntegerPositive(new BigInteger(rb),rb.length * 8);
        BigInteger s =  parseBigIntegerPositive(new BigInteger(sb),rb.length * 8);

        msg = Sha256Hash.hash(msg);
        X9ECParameters curve = SECNamedCurves.getByName(CURVE_ALGO);
        ECDSASigner signer = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()));
        ECPublicKeyParameters publicKey = new ECPublicKeyParameters(curve.getCurve().decodePoint(pub),ECKey.CURVE);
        signer.init(false, publicKey);
        return signer.verifySignature(msg, r, s);
    }


    public static BigInteger parseBigIntegerPositive(BigInteger b, int bitlen) {
        if (b.compareTo(BigInteger.ZERO) < 0)
            b = b.add(BigInteger.ONE.shiftLeft(bitlen));
        return b;
    }

}
