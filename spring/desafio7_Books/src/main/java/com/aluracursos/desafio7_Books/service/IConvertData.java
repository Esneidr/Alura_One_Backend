package com.aluracursos.desafio7_Books.service;

public interface IConvertData {
    <T> T searchData(String JSON, Class<T> tClass);
}
