package com.ye.service;

import com.ye.mapper.BrandMapper;
import com.ye.pojo.Brand;
import com.ye.pojo.PageBean;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface BrandService {

    //定义接口，可以实习泛型对象创建
    List<Brand> selectAll(int id);
    void addBrand(Brand brand);
    Brand selectBrandById(int id);
    void updateBrand(Brand brand);
    void deleteBrandById(int id);
    void deleteBrand(Brand brand);
    void  deleteSelectBrand(List<Brand> brands);
    List<Brand> selectPage(int id,int currentPage, int pageSize);
    int allBrands(int id);
    List<Brand> selectByCondition(Brand brand,int currentPage,int pageSize);
    int selectByConditionCount(Brand brand);

}
