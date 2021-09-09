package com.hunter.demo;

import org.apache.commons.lang3.StringUtils;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ExchangeMain {


    public static void createErc20Transaction() {
        Web3j web3j = Web3j.build(new HttpService("https://goerli.infura.io/v3/0f1cf0716cdf4a4db46488d8e2600faf"));
        Credentials credentials = Credentials.create("16ae01dd038407caa478e29cd5e8dfe59ee398da3a03b7e83ec86d6b1c655c97");//0x7D6Fbb71131D4d309DcC29197707624aE7C9D8f8

        StringBuilder data = new StringBuilder();
        data.append("0xb8192205" +
                "0000000000000000000000000000000000000000000000001bc16d674ec80000" +
                "00000000000000000000000000000000000000000001dca7069d3f7b89003800" +
                "0000000000000000000000000000000000000000000000000000000060b52c64");
        // data.append(rawTxDto.getToAddress().toLowerCase().replace("0x", Strings.zeros(24)));
        // from，to(合约地址) 0x7c1ed097af300c85f3e9aaf51a15de5c967f828e
        Transaction ethCallTransaction = Transaction.createEthCallTransaction(credentials.getAddress(), "0x7c1ed097af300c85f3e9aaf51a15de5c967f828e", "0xc4dbf622000000000000000000000000000000000000000000000000002386f26fc10000");
        for (int i = 0; i < 10000; i++) {
           // System.out.println("start" + i);
            RawTransaction contractTransaction = null;
            try {
                String value = web3j.ethCall(ethCallTransaction, DefaultBlockParameterName.LATEST).send().getValue();
                if (StringUtils.isEmpty(value)){
                    return;
                }
                //获取geth最新价
                BigDecimal price = new BigDecimal(Numeric.toBigInt(value)).movePointLeft(18);

                System.out.println("price: " + price.doubleValue());
                if (price.doubleValue() >= 1.2) {
                    BigInteger transactionCount = web3j.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.PENDING).send().getTransactionCount();
                    BigInteger gasPrice;
                    gasPrice = web3j.ethGasPrice().send().getGasPrice()
                            .add(BigInteger.valueOf(10).multiply(BigDecimal.TEN.pow(9).toBigInteger()));
                    contractTransaction = RawTransaction.createTransaction(transactionCount, gasPrice,
                            BigInteger.valueOf(500000), "0x7c1ed097af300c85f3e9aaf51a15de5c967f828e", data.toString());
                    byte[] bytes = TransactionEncoder.signMessage(contractTransaction, credentials);
                    String transactionHash = web3j.ethSendRawTransaction(Numeric.toHexString(bytes)).send().getTransactionHash();
                    System.out.println(transactionHash);

                }
                Thread.sleep(30000);
                //System.out.println("end" + i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args){
        createErc20Transaction();


        //System.out.println(new BigDecimal(0.3).compareTo(BigDecimal.ZERO) <= 0);
    }

   /** public static void test(){
        Web3j web3j = Web3j.build(new HttpService("https://goerli.infura.io/v3/0f1cf0716cdf4a4db46488d8e2600faf"));


        // 加载钱包
        Credentials credentials = WalletUtils.loadCredentials("钱包密码", "钱包路径");
        // 加载合约
        Key keySCode = Key.load(SMART_CONTRACT_ADDRESS, web3, credentials, BigInteger.valueOf(27000000000L), BigInteger.valueOf(250000));
        // 调用转账方法
        TransactionReceipt receipt = keySCode.transfer("目的账户", BigInteger.valueOf(1)).send();
        // 打印交易Hash
        System.out.println(receipt.getTransactionHash());


    }**/
}
