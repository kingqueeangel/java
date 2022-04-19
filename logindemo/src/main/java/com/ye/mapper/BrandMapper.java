package com.ye.mapper;

import com.ye.pojo.Brand;
import com.ye.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {

    List<Brand> selectAll(int user);
    void addBrand(Brand brand);
    Brand selectById(int id);
    void updateBrand(Brand brand);
    void deleteById(int id);
    void deleteBrand(Brand brand);
    void deleteSelectBrand(List<Brand> brands);
    List<Brand> selectPage(@Param("id")int id,@Param("currentPage") int currentPage,@Param("pageSize") int pageSize);
    int allBrands(@Param("id")int id);
    List<Brand> selectByCondition(@Param("brand") Brand brand,@Param("currentPage") int currentPage,@Param("pageSize") int pageSize);
    int  selectByConditionCount(Brand brand);



}
