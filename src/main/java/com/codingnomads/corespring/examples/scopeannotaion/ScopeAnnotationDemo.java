/* CodingNomads (C)2024 */
package com.codingnomads.corespring.examples.scopeannotaion;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScopeAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ScopeAnnotationDemoConfig.class);
        ctx.refresh();
        SingletonBean singletonBean1 = ctx.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ctx.getBean(SingletonBean.class);

        System.out.println("-----Hashcode of SingletonBean-----");
        System.out.println(singletonBean1.hashCode());
        System.out.println(singletonBean2.hashCode());

        final PrototypeBean prototypeBean1 = ctx.getBean(PrototypeBean.class);
        final PrototypeBean prototypeBean2 = ctx.getBean(PrototypeBean.class);

        System.out.println("-----Hashcode of PrototypeBean-----");
        System.out.println(prototypeBean1.hashCode());
        System.out.println(prototypeBean2.hashCode());
        System.out.println();

        BananaBean bananaBean1 = ctx.getBean(BananaBean.class);
        BananaBean bananaBean2 = ctx.getBean(BananaBean.class);

        System.out.println("-----Hashcode of SingletonBean: BananaBean-----");
        System.out.println(bananaBean1.hashCode());
        System.out.println(bananaBean2.hashCode());

        final PenguinBean penguinBean1 = ctx.getBean(PenguinBean.class);
        final PenguinBean penguinBean2 = ctx.getBean(PenguinBean.class);

        System.out.println("-----Hashcode of PrototypeBean:PenguinBean-----");
        System.out.println(penguinBean1.hashCode());
        System.out.println(penguinBean2.hashCode());
        System.out.println();
        ctx.close();
    }
}
