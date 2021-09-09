package com.hunter.demo;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;
import org.web3j.utils.Strings;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class ContractUtil {

    public static final String key = "16ae01dd038407caa478e29cd5e8dfe59ee398da3a03b7e83ec86d6b1c655c97";//bee3 -> address:0x7D6Fbb71131D4d309DcC29197707624aE7C9D8f8

    public static final String INFURA_URL = "https://goerli.infura.io/v3/32f4baceb5bc428ca0d7908a8b9594c5";

    public static final String CONTRACT_ADDRESS = "0x2ac3c1d3e24b45c6c310534bc2dd84b5ed576335";

    private static Web3j web3j;

    public ContractUtil(){
        this.web3j = Web3j.build(new HttpService(INFURA_URL));
    }

    private String createTokenTx(String to) {

        Credentials credentials = Credentials.create(key);
        StringBuilder data = new StringBuilder();
        data.append("0xa9059cbb");
        data.append(to.toLowerCase().replace("0x", Strings.zeros(24)));
        Transaction ethCallTransaction = Transaction.createEthCallTransaction(credentials.getAddress(), CONTRACT_ADDRESS,"0x313ce567");
        RawTransaction contractTransaction = null;
        try {
            String value = web3j.ethCall(ethCallTransaction, DefaultBlockParameterName.LATEST).send().getValue();

            //nonce
            data.append(Numeric.toHexStringNoPrefixZeroPadded(new BigDecimal("1").multiply(BigDecimal.TEN.pow(Numeric.decodeQuantity(value).intValue())).toBigInteger(), 64));
            BigInteger transactionCount = web3j.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.PENDING).send().getTransactionCount();
            BigInteger gasPrice;
            gasPrice = web3j.ethGasPrice().send().getGasPrice()
                    .add(BigInteger.valueOf(5).multiply(BigDecimal.TEN.pow(9).toBigInteger()));

            contractTransaction = RawTransaction.createTransaction(transactionCount, gasPrice,
                    BigInteger.valueOf(100000), CONTRACT_ADDRESS, data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (contractTransaction == null) {
            return null;
        }
        byte[] bytes = TransactionEncoder.signMessage(contractTransaction, credentials);
        return Numeric.toHexString(bytes);

    }



    private static String sendEthRawTx(String rawTx) {
       // Web3j web3j = Web3j.build(new HttpService("https://goerli.infura.io/v3/32f4baceb5bc428ca0d7908a8b9594c5"));
        try {
            return web3j.ethSendRawTransaction(rawTx).send().getTransactionHash();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
