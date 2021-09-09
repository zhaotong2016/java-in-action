package com.hunter.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DeployContract {



    static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        sendEthTx();
    }
    private static void sendEthTx() {
        Web3j web3j = Web3j.build(new HttpService("https://goerli.infura.io/v3/32f4baceb5bc428ca0d7908a8b9594c5"));
        Credentials credentials = Credentials.create("dbbc8b7cd2c4d5d995e47fef26217fb0309c4c9f6e7137f1140d4b046e5fcbcc");//0x475c9c50cB8E43F2c3abd6cfe4314E0dfF7Cfb34
        /*Admin web3j = Admin.build(new HttpService("https://goerli.infura.io/v3/32f4baceb5bc428ca0d7908a8b9594c5"));
        PersonalUnlockAccount personalUnlockAccount = web3j.personalUnlockAccount("0x000...", "a password").send();
        if (personalUnlockAccount.accountUnlocked()) {
            // send a com.hunter.demo
        }*/
        System.out.println(credentials.getAddress());
    }


    /**
     * 导出KeyStore
     */
    @SneakyThrows
    public void exportKeyStore(){
        try {
            Credentials credentials = Credentials.create("dbbc8b7cd2c4d5d995e47fef26217fb0309c4c9f6e7137f1140d4b046e5fcbcc");
            //验证的密码，
            WalletFile walletFile = Wallet.createLight("123456", credentials.getEcKeyPair());
            DateTimeFormatter format =
                    DateTimeFormatter.ofPattern("'UTC--'yyyy-MM-dd'T'HH-mm-ss.nVV'--'");
            ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
            String fileName = now.format(format) + walletFile.getAddress() + ".json";
            File destination = new File("D:\\hunter\\dev_tool", fileName);
            try {
                objectMapper.writeValue(destination, walletFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (CipherException e) {
            e.printStackTrace();
        }
    }
}
