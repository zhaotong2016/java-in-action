package com.hunter.demo;

import lombok.extern.slf4j.Slf4j;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
//@SpringBootApplication
public class TestMain {


    private static final String PASSWORD = "hunter";
   // @Autowired
   // Web3j web3j;

    public static void main(String[] args) throws Exception {
      //  SpringApplication.run(TestMain.class, args);
       // deployContract();
        loadContract();
       // new TestMain().run();
    }

    Credentials credentials;
    private List<String> contracts = new ArrayList<>();

   // @PostConstruct
    public void init() throws IOException, CipherException, NoSuchAlgorithmException, NoSuchProviderException, InvalidAlgorithmParameterException {
//        String file = WalletUtils.generateLightNewWalletFile(PASSWORD, null);
//        credentials = WalletUtils.loadCredentials(PASSWORD, file);
//        log.info("Credentials created: file={}, address={}", file, credentials.getAddress());
//        EthCoinbase coinbase = web3j.ethCoinbase().send();
//        EthGetTransactionCount transactionCount = web3j.ethGetTransactionCount(coinbase.getAddress(), DefaultBlockParameterName.LATEST).send();
//        Transaction com.hunter.demo = Transaction.createEtherTransaction(coinbase.getAddress(), transactionCount.getTransactionCount(), BigInteger.valueOf(20_000_000_000L), BigInteger.valueOf(21_000), credentials.getAddress(),BigInteger.valueOf(25_000_000_000_000_000L));
//        web3j.ethSendTransaction(com.hunter.demo).send();
//        EthGetBalance balance = web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
//        log.info("Balance: {}", balance.getBalance().longValue());
    }


    public Contract createContract(Contract newContract) throws Exception {
//        String file = WalletUtils.generateLightNewWalletFile(PASSWORD, null);
//        Credentials receiverCredentials = WalletUtils.loadCredentials(PASSWORD, file);
//        log.info("Credentials created: file={}, address={}", file, credentials.getAddress());
//        Transactionfee contract = Transactionfee.deploy(web3j, credentials, GAS_PRICE, GAS_LIMIT, receiverCredentials.getAddress(), BigInteger.valueOf(newContract.getFee())).send();
//        newContract.setReceiver(receiverCredentials.getAddress());
//        newContract.setAddress(contract.getContractAddress());
//        contracts.add(contract.getContractAddress());
//        LOGGER.info("New contract deployed: address={}", contract.getContractAddress());
//        Optional<TransactionReceipt> tr = contract.getTransactionReceipt();
//        if (tr.isPresent()) {
//            LOGGER.info("Transaction receipt: from={}, to={}, gas={}", tr.get().getFrom(), tr.get().getTo(), tr.get().getGasUsed().intValue());
//        }
//        return newContract;
        return null;
    }



    public static void deployContract(){
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));


        Credentials credentials = null;
        try {
            //查看版本信息，验证是否连接成功
            Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            log.debug("{}",clientVersion);

            credentials = WalletUtils.loadCredentials("hunter","E:\\eth_privatechain2\\keystore\\UTC--2021-07-20T07-13-08.146870100Z--6cc5308da0b50da3e58d6be7c3fffbd32c873a3f");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }
        //Credentials credentials = Credentials.create("");

        MyFirstContract
                awToken = null;
        try {


            //部署合约
            awToken = MyFirstContract.deploy(web3j, credentials, BigInteger.valueOf(210000),BigInteger.valueOf(210000)).send();
           // awToken = MyFirstContract.load("0x1A7Dc9454121ed754Ac6661Bbbc0bebd508beefa",web3,credentials,new DefaultGasProvider());
            System.out.println("ContractAddress："+awToken.getContractAddress());


            System.out.println(awToken.set(BigInteger.valueOf(14000000)).send());
            System.out.println(awToken.get().send());

            web3j.ethAccounts().send();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void run() throws Exception {

        // We start by creating a new web3j instance to connect to remote nodes on the network.
        // Note: if using web3j Android, use Web3jFactory.build(...
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        log.info("Connected to Ethereum client version: "
                + web3j.web3ClientVersion().send().getWeb3ClientVersion());

        // We then need to load our Ethereum wallet file
        // FIXME: Generate a new wallet file using the web3j command line tools https://docs.web3j.io/command_line.html
        Credentials credentials =
                WalletUtils.loadCredentials(
                        "hunter","E://eth_privatechain2//keystore//UTC--2021-07-20T07-13-08.146870100Z--6cc5308da0b50da3e58d6be7c3fffbd32c873a3f");
        log.info("Credentials loaded");

        // FIXME: Request some Ether for the Rinkeby test network at https://www.rinkeby.io/#faucet
//        log.info("Sending 1 Wei ("
//                + Convert.fromWei("1", Convert.Unit.ETHER).toPlainString() + " Ether)");
//        TransactionReceipt transferReceipt = Transfer.sendFunds(
//                web3j, credentials,
//                "0x19e03255f667bdfd50a32722df860b1eeaf4d635",  // you can put any address here
//                BigDecimal.ONE, Convert.Unit.WEI)  // 1 wei = 10^-18 Ether
//                .send();
//
//        log.info("Transaction complete, view it at https://rinkeby.etherscan.io/tx/"
//                + transferReceipt.getTransactionHash());

        // Now lets deploy a smart contract
        log.info("Deploying smart contract");
        ContractGasProvider contractGasProvider = new DefaultGasProvider();

        MyFirstContract.deploy(web3j, credentials, contractGasProvider).send();





    }


    //加载已经在链上的合约，并且调用方法
    public static void loadContract() throws Exception {
        //建立私链连接
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));
        //加载钱包账户,需要从geth的data文件中拷贝出来keysotre文件夹里的内容
        Credentials credentials =
                WalletUtils.loadCredentials(
                        "hunter","E:\\eth_privatechain2\\keystore\\UTC--2021-07-20T07-13-08.146870100Z--6cc5308da0b50da3e58d6be7c3fffbd32c873a3f");
        log.info("Credentials loaded");


        //加载合约
        String contractAddr = "0x4907F63cDC93e51D727BE7e1f1508f8013aF6123";
        MyFirstContract contract = MyFirstContract.load(contractAddr, web3j, credentials,new StaticGasProvider(BigInteger.valueOf(2160000),BigInteger.valueOf(2160000)));


        //contract.set(BigInteger.valueOf(150000)).send();


        log.info("结果========================={}",contract.get().send().isStatusOK());

    }
}
