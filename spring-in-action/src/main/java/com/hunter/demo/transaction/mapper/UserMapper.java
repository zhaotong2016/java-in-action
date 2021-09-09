package com.hunter.demo.transaction.mapper;

import com.hunter.demo.bean.Order;
import com.hunter.demo.bean.User;
import org.apache.ibatis.annotations.*;

import java.text.MessageFormat;
import java.util.List;


public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{userId}")
    User getUser(@Param("userId") Integer userId);


    @Select("SELECT * FROM `order` WHERE order_code = #{orderCode}")
    Order getOrder(String orderCode);


    @SelectProvider(type = OrderSqlBuilder.class, method = "buildGetOrders")
    List<Order> getUsersByName(List<String> name);

    @InsertProvider(type = OrderSqlBuilder.class, method = "insertUser")
    void insertUser(@Param("list")  List<Order> orders);

    class OrderSqlBuilder {
        public static String buildGetOrders(final String name) {
           /* return new SQL(){{
                SELECT("*");
                FROM("user");
                if (name != null) {
                    WHERE("name = #{value}");
                }
                ORDER_BY("id");
            }}.toString();*/

            StringBuilder sql = new StringBuilder()
                    .append("SELECT * FROM ")
                    .append("order where order_code in ")
                    .append(" <foreach item='item' collection='list' open='(' separator=',' close=')'>")
                    .append(" #{item}")
                    .append(" </foreach>");
            return sql.toString();
        }

        public String insertUser(@Param("list") List<Order> orders){
            StringBuilder sb = new StringBuilder();
            sb.append("insert into `order`(order_code,serial_no) values");
            MessageFormat mf = new MessageFormat("(#'{'list[{0}].orderCode},#'{'list[{0}].serialNo})");

            for (int i = 0; i < orders.size(); i++) {
                sb.append(mf.format(new Object[]{i + ""}));
                if (i < orders.size() - 1) {
                    sb.append(",");
                }
            }

            return sb.toString();
        }
    }



}
