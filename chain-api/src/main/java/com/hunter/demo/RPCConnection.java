package com.hunter.demo;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

@Slf4j
@Data
@Builder
public class RPCConnection {

    private String rpcUrl;

    private String rpcUser;

    private String rpcPassword;



    public RPCConnection(String rpcUrl, String rpcUser, String rpcPassword) {
        this.rpcUrl = rpcUrl;
        this.rpcUser = rpcUser;
        this.rpcPassword = rpcPassword;
    }

    public static RPCConnection getRpcConnection(String rpcUrl, String rpcUser, String rpcPassword) {
        return new RPCConnection(rpcUrl, rpcUser, rpcPassword);
    }

    /**
     * rpc进行查询请求
     * @param method RPC调用方法名
     * @param params RPC调用参数
     * @return 请求结果
     * @throws Exception 异常
     */
    public String query(String method, String params) throws Exception {
        String tonce = "" + (System.currentTimeMillis() * 1000);
        if (rpcUser != null && rpcPassword != null) {
            authenticator();
        }

        URL obj = new URL(rpcUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Json-Rpc-Tonce", tonce);
        con.setRequestProperty("Content-Type", "application/json");
        //con.setRequestProperty("Authorization", basicAuth);

        JsonRpcReqEntity rpcReqEntityEntity = new JsonRpcReqEntity();
        rpcReqEntityEntity.setId(1);
        rpcReqEntityEntity.setJsonrpc("2.0");
        rpcReqEntityEntity.setMethod(method);
        rpcReqEntityEntity.setParams(params);

        String postdata = "{\"jsonrpc\":\"2.0\", \"method\":\""+method+"\", \"params\":"+params+", \"id\": 1}";
        log.info("postData:{}", postdata);

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postdata);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        String responseMessage = con.getResponseMessage();
        log.info("responseCode:{}, responseMessage:{}", responseCode, responseMessage);
        if(responseCode != 200){
            return "{\"result\":null,\"error\":"+responseCode+",\"id\":1}";
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        inputLine = in.readLine();
        response.append(inputLine);
        in.close();
        log.info("response:{}", response.toString());
        /*boolean contains = Arrays.asList(CoinConstant.IGNORE_LOG_METHOD).contains(method);
        if (!contains) {
            log.info("response:{}", response.toString());
        }*/
        return response.toString();
    }

    /**
     * rpc进行查询请求
     * @param method RPC调用方法名
     * @param params RPC调用参数
     * @return 请求结果
     * @throws Exception 异常
     */
    public String query(String module, String method, String params) throws Exception {
        String tonce = "" + (System.currentTimeMillis() * 1000);
        if (rpcUser != null && rpcPassword != null) {
            authenticator();
        }
        String url = rpcUrl + "/v1/" + module + "/" + method;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Json-Rpc-Tonce", tonce);
        con.setRequestProperty("Content-Type", "application/json");
        //con.setRequestProperty("Authorization", basicAuth);

        JsonRpcReqEntity rpcReqEntityEntity = new JsonRpcReqEntity();
        rpcReqEntityEntity.setId(1);
        rpcReqEntityEntity.setJsonrpc("2.0");
        rpcReqEntityEntity.setMethod(method);
        rpcReqEntityEntity.setParams(params);

        log.info("url:{}, postData:{}", url, params);

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(params);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        String responseMessage = con.getResponseMessage();
        log.info("responseCode:{}, responseMessage:{}", responseCode, responseMessage);
        if(responseCode != 200){
            return "{\"result\":null,\"error\":"+responseCode+",\"id\":1}";
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        inputLine = in.readLine();
        response.append(inputLine);
        in.close();
      /*  boolean contains = Arrays.asList(CoinConstant.IGNORE_LOG_METHOD).contains(method);
        if (!contains) {
            log.info("response:{}", response.toString());
        }*/
        log.info("response:{}", response.toString());
        return response.toString();
    }

    /**
     * 认证
     */
    private void authenticator() {
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(rpcUser, rpcPassword.toCharArray());
            }
        });
    }
}
