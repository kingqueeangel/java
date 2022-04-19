package com.ye.mapper;

import com.ye.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {

    List<Brand> selectAll();
    void add(Brand brand);
    void update(@Param("brand") Brand brand, @Param("id") int id);
    Brand selectById(@Param("id") int id);
    void deleteByIdAfter(int id);

}
