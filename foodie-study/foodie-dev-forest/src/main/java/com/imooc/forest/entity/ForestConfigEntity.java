package com.imooc.forest.entity;

import lombok.Data;

import java.util.List;


@Data
public class ForestConfigEntity {
    private List<ForestInfoEntity> forestInfos;

    private ForestGlobalTimeoutEntity globalTimeout;
}
