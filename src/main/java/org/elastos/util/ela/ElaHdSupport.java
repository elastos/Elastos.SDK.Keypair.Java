/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.util.ela;

import io.github.novacrypto.bip32.ExtendedPrivateKey;
import io.github.novacrypto.bip44.AddressIndex;
import io.github.novacrypto.bip44.BIP44;
import io.github.novacrypto.bip44.Change;
import io.github.novacrypto.hashing.Sha256;
import io.github.novacrypto.toruntime.CheckedExceptionToRuntime;
import org.apache.commons.codec.binary.Hex;
import org.elastos.ela.Ela;
import org.web3j.crypto.CipherException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.*;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.text.Normalizer;
import java.util.UUID;

import static io.github.novacrypto.toruntime.CheckedExceptionToRuntime.toRuntime;

/**
 * clark
 * <p>
 * 10/17/18
 */
public class ElaHdSupport {

    private static final String[] dict =
            {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000",
                    "1001", "1010", "1011", "1100", "1101", "1110", "1111"};

    // 2^11
    private static String[] wordlist = new String[2048];
    private static Change acctType = BIP44.m().purpose44().coinType(0).account(0).external();
    private static final String SALT = "mnemonic";

    private static String createEntropy() {
        UUID uuid = UUID.randomUUID();
        String[] digits = uuid.toString().split("\\-");
        StringBuilder randomDigits = new StringBuilder();
        for (String digit : digits) {
            randomDigits.append(digit);
        }
        return randomDigits.toString();
    }

    public static String generateMnemonic() {
        String entropy = createEntropy();
        String encodeStr = "";
        byte[] hash = Sha256.sha256(hexStringToByteArray(entropy));
        encodeStr = String.valueOf(Hex.encodeHex(hash));
        char firstSHA = encodeStr.charAt(0);
        String new_entropy = entropy + firstSHA;
        String bin_entropy = "";
        for (int i = 0; i < new_entropy.length(); i++) {
            bin_entropy += dict[Integer.parseInt(new_entropy.substring(i, i + 1), 16)];
        }
        String[] segments = new String[12];
        //hardcode
        for (int i = 0; i <= 11; i++) {
            segments[i] = bin_entropy.substring(i * 11, (i + 1) * 11);
        }
        readTextFile();
        String mnemonic = "";
        mnemonic += wordlist[Integer.valueOf(segments[0], 2)];
        for (int j = 1; j < segments.length; j++) {
            mnemonic += " " + (wordlist[Integer.valueOf(segments[j], 2)]);
        }
        return mnemonic;
    }

    private static String findFilePath(String fileName){

        URL url = ElaHdSupport.class.getClassLoader().getResource(fileName);
        if (url != null){
            return url.getPath();
        }
        return null;
    }

    private static void readTextFile() {
        try {
            String encoding = "utf-8";
            InputStream is = ElaHdSupport.class.getClassLoader().getResourceAsStream("english");
            if (is != null) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        is, encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int index = 0;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    wordlist[index++] = lineTxt;
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

    public static String generate(String mnemonic,int index) throws InvalidKeySpecException, NoSuchAlgorithmException, CipherException {
        String seed = getSeed(mnemonic, SALT);
        ExtendedPrivateKey rootKey = ExtendedPrivateKey.fromSeed(hexStringToByteArray(seed),null);
        AddressIndex addressIndex = acctType.address(index);
        ExtendedPrivateKey childPrivateKey = rootKey.derive(addressIndex, AddressIndex.DERIVATION);
        return  genAddress(childPrivateKey);
    }

    /**
     * generate bitcoin privatekey, publickey and address.
     *
     * @param childPrivateKey
     */
    private static String genAddress(ExtendedPrivateKey childPrivateKey) {
        String privateKey = childPrivateKey.getPrivKey();
        String chainCode = childPrivateKey.getChainCode();
        System.out.println("ChainCode :" + chainCode);
        String publicKey  = Ela.getPublicFromPrivate(privateKey);
        String publicAddr = Ela.getAddressFromPrivate(privateKey);
        return "{\"privateKey\":\""+privateKey+"\",\"publicKey\":\""+publicKey+"\",\"publicAddress\":\""+publicAddr+"\"}";
    }

    private static String getSeed(String mnemonic, String salt) throws NoSuchAlgorithmException,
            InvalidKeySpecException {

        char[] chars = Normalizer.normalize(mnemonic, Normalizer.Form.NFKD).toCharArray();
        byte[] salt_ = getUtf8Bytes(salt);
        KeySpec spec = new PBEKeySpec(chars, salt_, 2048, 512);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        return String.valueOf(Hex.encodeHex(f.generateSecret(spec).getEncoded()));
    }


    private static byte[] getUtf8Bytes(final String str) {
        return toRuntime(new CheckedExceptionToRuntime.Func<byte[]>() {
            @Override
            public byte[] run() throws Exception {
                return str.getBytes("UTF-8");
            }
        });
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
