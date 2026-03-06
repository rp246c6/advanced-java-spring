/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.beanscopes.prototype;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PrototypeDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(PrototypeDemoConfig.class);
        ctx.refresh();

        SpringBean springBean1 = ctx.getBean(SpringBean.class);
        System.out.println("Hash code: " + springBean1.hashCode());

        SpringBean springBean2 = ctx.getBean(SpringBean.class);
        System.out.println("Hash code: " + springBean2.hashCode());

        CloudBean cloudBean1 = ctx.getBean(CloudBean.class);
        System.out.println("Hash code: " + cloudBean1.hashCode());

        CloudBean cloudBean2 = ctx.getBean(CloudBean.class);
        System.out.println("Hash code: " + cloudBean2.hashCode());
        ctx.close();
    }
}
