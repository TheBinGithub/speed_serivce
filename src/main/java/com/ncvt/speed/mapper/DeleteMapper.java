package com.ncvt.speed.mapper;

import com.ncvt.speed.entity.DeleteEntity;

import java.util.List;

public interface DeleteMapper {

    Integer addDelete(DeleteEntity deleteEntity);

    Integer dDelete(Integer deleteId);

}
