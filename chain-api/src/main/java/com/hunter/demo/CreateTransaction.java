package com.hunter.demo;


import lombok.extern.slf4j.Slf4j;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;
import org.web3j.utils.Strings;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 发送交易
 */
@Slf4j
public class CreateTransaction {

    /**
     *
     * @param num 数量
     * @param to 地址
     * @return
     */
    private static void sendTokenTx(BigDecimal num, String to) {
        Web3j web3j = Web3j.build(new HttpService("https://goerli.infura.io/v3/32f4baceb5bc428ca0d7908a8b9594c5"));
        Credentials credentials = Credentials.create(ContractUtil.key);
        StringBuilder data = new StringBuilder();
        data.append("0xa9059cbb");
        data.append(to.toLowerCase().replace("0x", Strings.zeros(24)));
        Transaction ethCallTransaction = Transaction.createEthCallTransaction(credentials.getAddress(), "0x2ac3c1d3e24b45c6c310534bc2dd84b5ed576335","0x313ce567");
        RawTransaction contractTransaction = null;
        try {
            String value = web3j.ethCall(ethCallTransaction, DefaultBlockParameterName.LATEST).send().getValue();

            data.append(Numeric.toHexStringNoPrefixZeroPadded(num.multiply(BigDecimal.TEN.pow(Numeric.decodeQuantity(value).intValue())).toBigInteger(), 64));
            // get the next available nonce
            EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.PENDING).send();
            //nonce
            BigInteger nonce  = ethGetTransactionCount.getTransactionCount();

            BigInteger gasPrice;
            gasPrice = web3j.ethGasPrice().send().getGasPrice()
                    .add(BigInteger.valueOf(10).multiply(BigDecimal.TEN.pow(9).toBigInteger()));

            // create our com.hunter.demo
            contractTransaction = RawTransaction.createTransaction(nonce, gasPrice,
                    BigInteger.valueOf(100000), "0x2ac3c1d3e24b45c6c310534bc2dd84b5ed576335", data.toString());

            // sign & send our com.hunter.demo
            byte[] signedMessage = TransactionEncoder.signMessage(contractTransaction, credentials);

            String hexValue = Numeric.toHexString(signedMessage);

            EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();

            System.out.println("response bgzz hash:" +ethSendTransaction.getTransactionHash());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            web3j.shutdown();
        }

    }


    /**
     * 发送eth
     * @param num
     * @param to
     * @return
     */
    private static void sendEthTx(BigDecimal num, String to) {
        Web3j web3j = Web3j.build(new HttpService("https://goerli.infura.io/v3/32f4baceb5bc428ca0d7908a8b9594c5"));
        Credentials credentials = Credentials.create(ContractUtil.key);
        RawTransaction etherTransaction = null;
        try {
            BigInteger transactionCount = web3j.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.PENDING).send().getTransactionCount();
            BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice()
                    .add(BigInteger.valueOf(5).multiply(BigDecimal.TEN.pow(9).toBigInteger()));

            etherTransaction = RawTransaction.createEtherTransaction(transactionCount, gasPrice, BigInteger.valueOf(21000),
                    to, num.multiply(BigDecimal.TEN.pow(18)).toBigInteger());
        } catch (Exception e) {
            e.printStackTrace();
        }


        // sign & send our com.hunter.demo
        byte[] signedMessage = TransactionEncoder.signMessage(etherTransaction, credentials);

        String hexValue = Numeric.toHexString(signedMessage);

        EthSendTransaction ethSendTransaction = null;
        try {
            ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();

            System.out.println("response eth hash:" +ethSendTransaction.getTransactionHash());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            web3j.shutdown();
        }
    }



    public static void main(String[] args){
        //sendTokenTx(BigDecimal.valueOf(5),"0x03b9cBF7e51205e5ED5e7B89CC1216DdA5aD8dA5");

        //0x7D6Fbb71131D4d309DcC29197707624aE7C9D8f8 -> 0x03b9cBF7e51205e5ED5e7B89CC1216DdA5aD8dA5
        //sendEthTx(BigDecimal.valueOf(5),"0x03b9cBF7e51205e5ED5e7B89CC1216DdA5aD8dA5");

        batchSend();
    }


    /**
     * 批量发送
     */
    public static void batchSend(){
         //String [] to_address = {"0x7825f05ae9249870e1e31eafd0225a6cde1cf21d","0xfb6f2b26b4ebd595ea99ff50b1f37856f0fcb524","0x1ce27282df1e2f7a46eabcf817a34eb993ac5383",
         //       "0x300f0cef105e6d23c13a7ffe2d10935baf0ae2b1","0xe8038e9b4178b6a790231bf9316138e7d3fa388d"};

        String[] to_address = {

                "0x37ba88fdc8741bb2028adc90fcaa28e457610216"
        };
        for (String address : to_address) {
            //发送代币
            sendTokenTx(BigDecimal.valueOf(1),address);
            //发送eth
           /// sendEthTx(BigDecimal.valueOf(2),address);
        }
    }





}
