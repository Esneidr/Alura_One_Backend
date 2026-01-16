package com.aluracursos.Literatura.services;

public interface IMapData {
    <T> T getData(String json, Class<T> tClass);
}
