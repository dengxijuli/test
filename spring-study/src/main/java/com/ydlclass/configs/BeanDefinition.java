package com.ydlclass.configs;

import org.springframework.beans.BeanMetadataAttribute;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @program: test
 * @description: bean的定义
 * @author: dx
 * @create: 2023/7/6 19:07
 */
public class BeanDefinition {

    public void testRootBeanDefinition() {
        RootBeanDefinition dog = new RootBeanDefinition();
        dog.setBeanClassName("com.ydlclass.Dog");
        BeanMetadataAttribute color = new BeanMetadataAttribute("color", "white");
        BeanMetadataAttribute age = new BeanMetadataAttribute("age", "3");
        dog.addMetadataAttribute(color);
        dog.addMetadataAttribute(age);

        // 子Definition的创建需要依赖父Definition
        ChildBeanDefinition teddy = new ChildBeanDefinition("dog");
        teddy.setBeanClassName("com.ydlclass.TeddyDog");
        BeanMetadataAttribute name = new BeanMetadataAttribute("name", "doudou");
        teddy.addMetadataAttribute(name);
    }

}
