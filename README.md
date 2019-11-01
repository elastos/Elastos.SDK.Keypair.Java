## Quick Start

Run with `Maven`：

Create a basic `Maven` project

```xml
<dependency>
    <groupId>org.elastos</groupId>
    <artifactId>wallet.lib</artifactId>
    <version>0.0.2</version>
</dependency>
```

or `Gradle`:

```sh
compile 'org.elastos:wallet.lib:0.0.2'
```

Sample of how to use some of these api:

* generate mnemonic phrase

```java
String mnemonic = ElaHdSupport.generateMnemonic(MnemonicType.ENGLISH); 
```

* get single wallet of specific mnemonic index

```java
String mnemonic = "purchase fantasy quit cement cruise hobby poem obvious warm climb amateur merit"; 
String singleAddress = ElaHdSupport.generate(mnemonic,1); 
```

* generate raw transaction
```java
String data = "{\"Transactions\":[{\"UTXOInputs\":[{\"privateKey\":\"C6B0CA5B61984E2FCACCB255D4A57AB3F6A2B7AB753999CC593B0DEF64FCE7A8\",\"address\":\"EeJ6yExPneTvMXuEXhnwbWL3Mx2Spb55BY\",\"txid\":\"4ee71e4c2b72b628d837fd7a0ff5a44db3d80805f5d644a8628f3a3996c74c01\",\"index\":0},{\"privateKey\":\"C6B0CA5B61984E2FCACCB255D4A57AB3F6A2B7AB753999CC593B0DEF64FCE7A8\",\"address\":\"EeJ6yExPneTvMXuEXhnwbWL3Mx2Spb55BY\",\"txid\":\"c456a8619d4b8d3e1f185fe6799a9dcae57135f9a895f73572d5256e5e32417f\",\"index\":0},{\"privateKey\":\"C6B0CA5B61984E2FCACCB255D4A57AB3F6A2B7AB753999CC593B0DEF64FCE7A8\",\"address\":\"EeJ6yExPneTvMXuEXhnwbWL3Mx2Spb55BY\",\"txid\":\"14c9754270e39b61c9a8c6925ab2f6d6763483c0ec2ba954074ef320a8e6cc70\",\"index\":0},{\"privateKey\":\"C6B0CA5B61984E2FCACCB255D4A57AB3F6A2B7AB753999CC593B0DEF64FCE7A8\",\"address\":\"EeJ6yExPneTvMXuEXhnwbWL3Mx2Spb55BY\",\"txid\":\"0ac2cb3f4aeb9d424635ead84bd83983000e1cd5b96327d0569c5327cadf6917\",\"index\":10530}],\"Outputs\":[{\"amount\":10,\"address\":\"EdyqqiJcdkTDtfkvxVbTuNXGMdB3FEcpXA\"},{\"amount\":9452322,\"address\":\"EeJ6yExPneTvMXuEXhnwbWL3Mx2Spb55BY\"}],\"Memo\":\"type:text,msg:hello123\"}]}\n";
JSONObject req = JSONObject.fromObject(data);
String rawTx = ElaKit.genRawTransaction(req);
```

* generate did from public key 
```java
String did = ElaKit.getIdentityFromPublicKey("03BA05B26686DE8C7154C660B2B67B48561A423D1ABA532D514571AA9D821101F9");
```

* sign message using private key 
```java
byte[] msg  = "abc".getBytes("utf-8");
byte[] priv = DatatypeConverter.parseHexBinary("A6911F5E9410AE84FB262E6346F39565520A29B67EDC7C7B925BBF13BDCA9DB4");
byte[] sig  = ElaSignTool.doSign(msg,priv);
```

* verify message using public key 

```java
byte[] pub  = DatatypeConverter.parseHexBinary("03BA05B26686DE8C7154C660B2B67B48561A423D1ABA532D514571AA9D821101F9");
boolean ok = ElaSignTool.verify(msg,sig,pub);
```

* check if an ela address is valid
```java
boolean isValid = ElaKit.checkAddress("ELw1tzBuursLo9LnWeCATWv35hCD1kYygt");
```
