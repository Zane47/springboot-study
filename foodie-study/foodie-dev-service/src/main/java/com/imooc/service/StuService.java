package com.imooc.service;

import com.imooc.pojo.Stu;

public interface StuService {

    /**
     * 根据id获取student info
     *
     * @param id
     * @return
     */
    public Stu getStuInfo(Integer id);

    /**
     * 保存学生数据, 一般由前端穿来, 这边直接写死
     */
    public void saveStu();

    /**
     * update
     *
     * @param id
     */
    public void updateStu(Integer id);


    /**
     * delete
     *
     * @param id
     */
    public void deleteStu(Integer id);


    public void saveParent();


    public void saveChildren();

}
