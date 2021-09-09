package com.hunter.demo;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class JsonRpcReqEntity {

    private String jsonrpc;

    private String method;

    private String params;

    private int id;
}
