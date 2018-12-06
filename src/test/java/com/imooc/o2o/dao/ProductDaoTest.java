package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Author: Alex
 * @Date: created in 22:11  2018/10/1
 * @Annotation:
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testAInsertProduct() throws Exception{
        Shop shop1 = new Shop();
        shop1.setShopId(1L);
        ProductCategory pc1 = new ProductCategory();
        pc1.setProductCategoryId(1L);
        //初始化三个商品实例并添加进shopId为1的店铺里
        //同时，商品类别Id也为1
        Product product1 = new Product();
        product1.setProductName("测试1");
        product1.setProductDesc("测试Desc1");
        product1.setImgAddr("test1");
        product1.setPriority(1);
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setLastEditTime(new Date());
        product1.setShop(shop1);
        product1.setProductCategory(pc1);

        Product product2 = new Product();
        product2.setProductName("测试2");
        product2.setProductDesc("测试Desc2");
        product2.setImgAddr("test2");
        product2.setPriority(2);
        product2.setEnableStatus(1);
        product2.setCreateTime(new Date());
        product2.setLastEditTime(new Date());
        product2.setShop(shop1);
        product2.setProductCategory(pc1);

        Product product3 = new Product();
        product3.setProductName("测试3");
        product3.setProductDesc("测试Desc3");
        product3.setImgAddr("test3");
        product3.setPriority(3);
        product3.setEnableStatus(1);
        product3.setCreateTime(new Date());
        product3.setLastEditTime(new Date());
        product3.setShop(shop1);
        product3.setProductCategory(pc1);

        int effectedNum = productDao.insertProduct(product1);
        assertEquals(1,effectedNum);

        effectedNum = productDao.insertProduct(product2);
        assertEquals(1,effectedNum);

        effectedNum = productDao.insertProduct(product3);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testBQueryProductList() throws Exception{
        Product productCondition = new Product();
        //分页查询 返回三条结果
        List<Product> productsList = productDao.queryProductList(productCondition,0,3);
        assertEquals(3,productsList.size());
        //查询名称为测试的商品总数
        int count = productDao.queryProductCount(productCondition);
        assertEquals(5,count);
        //使用商品名字模糊查询,预期返回3条结果
        productCondition.setProductName("测");
        productsList = productDao.queryProductList(productCondition,0,3);
        assertEquals(0,productsList.size());
        count = productDao.queryProductCount(productCondition);
        assertEquals(3,count);
    }

    @Test
    public void testCQueryProductByProductId() throws Exception{
        long productId = 1;
        //初始化两个商品详情图实例作为ProductId为1的商品下的详情图片
        //批量插入到商品详情图表中
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("Pic1");
        productImg1.setImgDesc("Pic1 Addr");
        productImg1.setPriority(1);
        productImg1.setCreateTime(new Date());
        productImg1.setProductId(productId);

        ProductImg productImg2 = new ProductImg();
        productImg2.setImgAddr("Pic2");
        productImg2.setImgDesc("Pic2 Addr");
        productImg2.setPriority(1);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(productId);

        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);
        int effectedNum = productImgDao.batchInsertProductImg(productImgList);
        assertEquals(2,effectedNum);
        Product product = productDao.queryProductById(productId);
        assertEquals(2,product.getProductImgList().size());
        //删除新增两个商品的详情图案例
        effectedNum = productImgDao.deleteProductImgByProductId(productId);
        assertEquals(2,effectedNum);
    }

    @Test
    public void testDUpdateProduct() throws Exception{
        Product product = new Product();
        ProductCategory pc = new ProductCategory();
        Shop shop = new Shop();
        shop.setShopId(1L);
        pc.setProductCategoryId(1L);
        product.setProductId(1L);
        product.setShop(shop);
        product.setProductName("一点点奶茶");
        product.setProductCategory(pc);
        //修改productId为1的商品的名称
        //以及商品类别并校验影响的行数是否为1
        int effectedNum = productDao.updateProduct(product);
        assertEquals(1,effectedNum);
    }

    @Test
    public void testEUpdateProductCategoryToNnull(){
        //将productCategoryId为2的商品类别下面的商品的商品类别置为空
        int effectedNum = productDao.updateProductCategoryToNull(27L);
        assertEquals(2,effectedNum);
    }



}
