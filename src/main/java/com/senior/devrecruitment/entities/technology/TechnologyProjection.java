package com.senior.devrecruitment.entities.technology;

import com.senior.devrecruitment.enums.Framework;
import com.senior.devrecruitment.enums.Language;
import com.senior.devrecruitment.enums.PerformanceArea;

import java.util.List;

public interface TechnologyProjection {


    Language getName();

    Framework getFramework();

    List<PerformanceArea> getRecommendedArea();

    String getDescription();
}
