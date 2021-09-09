package com.hunter.demo.expression;

import lombok.Data;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Sample {

    private Integer id;
    private String name;
    private Date birth;

    public Sample(Integer id, String name, Date birth) {
        this.id = id;
        this.name = name;
        this.birth = birth;
    }

    public static void main(String[] args) {
        Sample sample = new Sample(1,"Hunter",new Date());


        ExpressionParser parser = new SpelExpressionParser();

        EvaluationContext context = new StandardEvaluationContext( sample);


        Expression exp = parser.parseExpression("name");

        String value = exp.getValue(sample,String.class);

        // 以指定对象作为root来计算表达式的值
        // 相当于调用person.name表达式的值
        System.out.println("以persn为root，name表达式的值是： "
                + value);


        exp = parser.parseExpression("name==\"Hunter\"");

        StandardEvaluationContext ctx = new StandardEvaluationContext();
        // 将person设为Context的root对象
        ctx.setRootObject(sample);
        // 以指定Context来计算表达式的值
        System.out.println(exp.getValue(ctx , Boolean.class));


        List<Sample> list = new ArrayList();
        list.add(sample);
        sample = new Sample(2,"Hunter2",new Date());
        list.add(sample);
        sample = new Sample(3,"Hunter3",new Date());
        list.add(sample);

        EvaluationContext ctx2 = new StandardEvaluationContext();
        // 将list设置成EvaluationContext的一个变量
        ctx2.setVariable("list" , list);
        // 修改list变量的第一个元素的值
        parser.parseExpression("#list[0]").setValue(ctx2 , sample);
        // list集合的第一个元素被改变
        System.out.println("list集合的第一个元素为："
                + parser.parseExpression("#list[0]").getValue(ctx2));


    }
}
