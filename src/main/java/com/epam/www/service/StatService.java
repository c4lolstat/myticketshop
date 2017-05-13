package com.epam.www.service;

import com.epam.www.service.impl.StatServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * Created by Farkas on 2017.04.07..
 */
public interface StatService {

    List<StatServiceImpl.EventStats> getEventInfoByParams(Map<String, String> params);


}
