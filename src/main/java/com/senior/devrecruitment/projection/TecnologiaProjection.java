package com.senior.devrecruitment.projection;

import com.senior.devrecruitment.enums.AreaAconselhavel;
import com.senior.devrecruitment.enums.Framework;
import com.senior.devrecruitment.enums.Linguagens;

import java.util.List;

public interface TecnologiaProjection {


    Linguagens getNome();

    Framework getFramework();

    List<AreaAconselhavel> getAreaaconselhavel();

    String getDescricao();
}
