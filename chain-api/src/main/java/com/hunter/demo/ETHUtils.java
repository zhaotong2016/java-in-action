package com.hunter.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * ETH钱包操作
 * @author Administrator
 */
@Slf4j
public class ETHUtils {

    private RPCConnection rpcConnection;

    public ETHUtils(String rpcUrl) {
        this.rpcConnection = RPCConnection.getRpcConnection(rpcUrl, null, null);
    }

    public static ETHUtils getEthUtils(String rpcUrl) {
        return new ETHUtils(rpcUrl);
    }

    /**
     * 校验地址
     * @param address
     * @return
     */
    public static boolean validateAddress(String address) {
        return address.toLowerCase().matches("^(0x)?[0-9a-f]{40}$");
    }

    public static boolean validatePcAddress(String address) {
        return address.toLowerCase().matches("^(pc)?[0-9a-f]{40}$");
    }

    /**
     * 解析ETH钱包数量（16进制）为实际数量（10进制）
     * @param hexVal 16进制数
     * @return 十进制ETH数量
     */
    public BigDecimal parseAmount(String hexVal) {
        if(StringUtils.isEmpty(hexVal)){
            return BigDecimal.ZERO;
        }
        String val = hexVal.substring(2);
        BigInteger intVal = new BigInteger(val, 16);
        BigDecimal pow = BigDecimal.TEN.pow(18);
        return new BigDecimal(intVal).divide(pow, 18, BigDecimal.ROUND_DOWN);
    }

    /**
     * 实际数量（10进制）转ETH钱包数量（16进制）
     * @param amount
     * @return
     */
    public String parseAmountHex(BigDecimal amount) {
        BigDecimal multiply = amount.multiply(BigDecimal.TEN.pow(18));
        return multiply.toBigInteger().toString(16);
    }

    /**
     * 获取某地址余额
     * @param address 地址
     * @return 数量
     * @throws Exception 异常
     */
    public BigDecimal getBalance(String address) throws Exception {
        String s = rpcConnection.query("eth_getBalance", "[\"" + address + "\", \"latest\"]");
        return parseAmount(JSONObject.parseObject(s).getString("result"));
    }

    /**
     * 获取钱包内所有的地址
     * @return JSONArray
     * @throws Exception 异常
     */
    public JSONArray listAddress() throws Exception {
        String s = rpcConnection.query("eth_accounts", "[]");
        return JSONObject.parseObject(s).getJSONArray("result");
    }

    /**
     * 获取所有地址余额 （如果数量较大则耗时较长）
     * @return 总数量
     * @throws Exception 异常
     */
    public BigDecimal getAllBalance() throws Exception {
        JSONArray addresses = listAddress();
        BigDecimal balances = BigDecimal.ZERO;
        for (Object address : addresses) {
            balances = balances.add(getBalance(address.toString()));
        }
        return balances;
    }

    /**
     * 获取一个新的地址
     * @param password 密码
     * @return 地址
     * @throws Exception 异常
     */
    public String getNewAddress(String password) throws Exception {
        String s = rpcConnection.query("personal_newAccount", "[\"" + password + "\"]");
        return JSONObject.parseObject(s).getString("result");
    }

    public JSONObject getTransaction(String hash) throws Exception {
        String s = rpcConnection.query("eth_getTransactionByHash", "[\"" + hash + "\"]");
        return JSONObject.parseObject(s).getJSONObject("result");
    }

    /**
     * 获取块的高度
     * @return
     * @throws Exception
     */
    public long getBlockNumber() throws Exception {
        String s = rpcConnection.query("eth_blockNumber", "[]");
        JSONObject jsonObject = JSONObject.parseObject(s);
        //判断是否包含result
        if (jsonObject.containsKey("result")) {
            //判断获取的值是否为空
            if(jsonObject.getString("result") != null){
                BigInteger valD = new BigInteger(jsonObject.getString("result").substring(2), 16);
                return Long.parseLong(valD.toString(10));
            }
        }
        return 0;
    }

    /**
     * 发送交易
     * @param from from地址
     * @param to to地址
     * @param amount 发送的数量
     * @param gas gas
     * @param password 密码
     * @return
     * @throws Exception
     */
    public String sendTransaction(String from, String to, BigDecimal amount, BigInteger gas, String password) throws Exception {
        String s = rpcConnection.query("personal_sendTransaction",
                "[{" + " \"from\": \"" + from + "\"," + "\"to\": \"" + to + "\"," + " \"gas\": \"0x"
                        + gas.toString(16) + "\"," + "\"gasPrice\": \"" + getGasPrice() + "\"," + " \"value\": \""
                        + "0x" + parseAmountHex(amount) + "\" " + "},\"" + password + "\"]");
        log.info("s {}", s);
        return s;
    }


    public String getGasPrice() throws Exception {
        String s = rpcConnection.query("eth_gasPrice", "[]");
        return JSONObject.parseObject(s).getString("result");
    }

    public Boolean unlockAccount(String address, String password) throws Exception {
        String s = rpcConnection.query("personal_unlockAccount", "[" + "\"" + address + "\"," + "\"" + password + "\"" + "]");
        return JSONObject.parseObject(s).getBoolean("result");
    }

    public Boolean lockAccount(String address) throws Exception {
        String s = rpcConnection.query("personal_lockAccount", "[" + "\"" + address + "\"" + "]");
        return JSONObject.parseObject(s).getBoolean("result");
    }


    public void summary(String toAddress, List sendAddresses, BigDecimal minAmount, String password) throws Exception {
        int success = 0;
        BigDecimal successAmount = BigDecimal.ZERO;
        for (Object o : sendAddresses) {
            String sendAddress = o.toString();
            if (sendAddress.toLowerCase().equals(toAddress.toLowerCase())) {
                continue;
            }
            BigDecimal balance = getBalance(sendAddress);
            if (balance.compareTo(minAmount) < 0) {
                continue;
            }
            BigDecimal fee = parseAmount(getGasPrice()).multiply(BigDecimal.valueOf(21000));
            if (balance.compareTo(fee) < 0) {
                continue;
            }
            BigDecimal actualAmount = balance.subtract(fee);
            String s = sendTransaction(sendAddress, toAddress, actualAmount, BigInteger.valueOf(21000), password);
            if (s != null && !"".equals(s)) {
                success++;
                successAmount = successAmount.add(actualAmount);
            }
            log.info("成功汇总：" + success + "条，成功汇总数量：" + successAmount);
        }
    }

    public JSONObject summaryOne(String toAddress, String sendAddress, BigDecimal minAmount, String password) throws Exception {
        if (sendAddress.toLowerCase().equals(toAddress.toLowerCase())) {
            return null;
        }
        BigDecimal balance = getBalance(sendAddress);
        if (balance.compareTo(BigDecimal.ZERO) == 0) {
            return null;
        }

        if (balance.compareTo(minAmount) < 0) {
            return null;
        }
        BigDecimal fee = parseAmount(getGasPrice()).multiply(BigDecimal.valueOf(21000));
        if (balance.compareTo(fee) < 0) {
            return null;
        }
        BigDecimal actualAmount = balance.subtract(fee);

        JSONObject jsonObject = new JSONObject();
        String s = sendTransaction(sendAddress, toAddress, actualAmount, BigInteger.valueOf(21000), password);
        jsonObject.put("sendAddress", sendAddress);
        jsonObject.put("toAddress", toAddress);
        jsonObject.put("actualAmount", actualAmount);
        JSONObject transaction = JSONObject.parseObject(s);
        if (transaction.containsKey("result")) {
            jsonObject.put("txId", transaction.getString("result"));
        }
        return jsonObject;
    }

    public JSONArray getBlockByNumber(BigInteger blockNumber) throws Exception {
        String s = rpcConnection.query("eth_getBlockByNumber",
                "[" + "\"0x" + blockNumber.toString(16) + "\"," + true + "]");
        return JSONObject.parseObject(s).getJSONObject("result").getJSONArray("transactions");
    }

    /**
     *
     * @Title: eth_getBlockByNumber
     * @param:
     * @Description: 根据区块高度查询某区块高度信息 block 区块高度 full 是否获取完整交易记录（true-获取，false-不获取）
     * @return JSONObject
     */
    public JSONObject eth_getBlockByNumber(long block, boolean full) throws Exception {
        String s = rpcConnection.query("eth_getBlockByNumber",
                "[" + "\"0x" + Long.toHexString(block) + "\"," + String.valueOf(full) + "]");
        JSONObject json = JSONObject.parseObject(s);
        return json.getJSONObject("result");
    }

    /**
     *
     * @Title: listtransactionsValue
     * @param:
     * @Description: 某区块高度的交易信息
     * @return List<ETHInfo>
     */
    public List<ETHInfo> listBlockTransactionsValue(long block) throws Exception {
        JSONObject json = eth_getBlockByNumber(block, true);
        List<ETHInfo> all = new ArrayList();

        // 获取区块所有的记录
        if (json != null) {
            JSONArray jsonArray = json.getJSONArray("transactions");
            for (int i = 0; i < jsonArray.size(); i++) {
                ETHInfo info = new ETHInfo();

                // 将json转换成bean对象
                JSONObject item = jsonArray.getJSONObject(i);
                String from = item.getString("from");
                String to = item.getString("to");
                String value = item.getString("value");

                info.setAccount(from);
                info.setAddress(to);
                info.setAmount(parseAmount(value).doubleValue());
                info.setConfirmations(0);
                info.setTime(new Timestamp(System.currentTimeMillis()));
                info.setTxid(item.getString("hash"));
                all.add(info);
            }
        }
        return all;
    }

    /**
     *
     * @param address
     * @return
     * @throws Exception
     */
    public JSONObject phraseToAddress(String address) throws Exception {
        String s = rpcConnection.query("parity_phraseToAddress",
                "[" + address + "]");
        JSONObject json = JSONObject.parseObject(s);
        return json.getJSONObject("result");

    }

    /**
     * 导入地址到钱包中
     * @param address
     * @param password
     * @return
     * @throws Exception
     */
    public JSONObject newAccountFromSecret(String address, String password)throws Exception {
        String s = rpcConnection.query("parity_newAccountFromSecret",
                "[\"" + address + "\",\"" + password + "\"]");
        JSONObject json = JSONObject.parseObject(s);
        return json.getJSONObject("result");
    }
}
