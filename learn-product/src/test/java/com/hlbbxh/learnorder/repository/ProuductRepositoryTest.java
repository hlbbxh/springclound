package com.hlbbxh.learnorder.repository;

import com.hlbbxh.learnorder.entity.ProductInfo;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author Carrot
 * @Title:
 * @Package
 * @Description:
 * @date 2020-09-0122:02:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProuductRepositoryTest extends TestCase {

    @Autowired
    private ProuductRepository prouductRepository;

    /**
     * 测试 按照 状态获取所有的 商品
     */
    @Test
    public void test01(){
        List<ProductInfo> byProductStatus = prouductRepository.findByProductStatus(0);
        System.out.println(byProductStatus.toString());
    }

    /**
     * 测试按照  ProductId in操作查询 指定的商品信息
     */
    @Test
    public void test02(){
        List<ProductInfo> byProductStatus = prouductRepository.findByProductIdIn(Arrays.asList("157875196366160022","157875227953464068"));
        System.out.println(byProductStatus.toString());
    }
}