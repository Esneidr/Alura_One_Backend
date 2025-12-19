package com.aluracursos.screenmatch.services;

public interface IMapData {
    <T> T getData(String json, Class<T> tClass);
}
