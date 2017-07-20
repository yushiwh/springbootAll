package com.company.project.service.impl;

import com.company.project.dao.UsertestMapper;
import com.company.project.model.Usertest;
import com.company.project.service.UsertestService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/07/18.
 */
@Service
@Transactional
public class UsertestServiceImpl extends AbstractService<Usertest> implements UsertestService {
    @Resource
    private UsertestMapper usertestMapper;

}
