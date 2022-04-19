package com.ye.service.impl;

import com.ye.mapper.BrandMapper;

import com.ye.pojo.Brand;

import com.ye.pojo.PageBean;
import com.ye.service.BrandService;
import com.ye.util.SqlSessionFactoryUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    //创建sqlSessionFactory 工厂对象
    private final SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    @Override
    public List<Brand> selectAll(int id) {
        //获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        List<Brand> brands = mapper.selectAll(id);
        //释放资源
        sqlSession.close();
        //返回brands对象集合
        return brands;
    }

    @Override
    public void addBrand(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.addBrand(brand);
        sqlSession.commit();
        sqlSession.close();
    }


    @Override
    public Brand selectBrandById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        Brand brand = mapper.selectById(id);
        sqlSession.close();
        return brand;
    }

    @Override
    public void updateBrand(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.updateBrand(brand);
        sqlSession.commit();
        sqlSession.close();


    }

    @Override
    public void deleteBrandById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();

    }


    @Override
    public void deleteBrand(Brand brand) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteBrand(brand);
        sqlSession.commit();
        sqlSession.close();
    }


    @Override
    public void deleteSelectBrand(List<Brand> brands) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteSelectBrand(brands);
        sqlSession.commit();
        sqlSession.close();
    }

    public List<Brand> selectPage(int id, int currentPage, int pageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectPage(id, currentPage, pageSize);
        sqlSession.close();
        return brands;

    }

    public int allBrands(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int brands = mapper.allBrands(id);
        sqlSession.close();
        return brands;
    }


    @Override
    public List<Brand> selectByCondition(Brand brand,int currentPage,int pageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectByCondition(brand,currentPage,pageSize);
        sqlSession.close();
        return brands;
    }


    public int selectByConditionCount(Brand brand){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int count = mapper.selectByConditionCount(brand);
        return count;
    }

}
