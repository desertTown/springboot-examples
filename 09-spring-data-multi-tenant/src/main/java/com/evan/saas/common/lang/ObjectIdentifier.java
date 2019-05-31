package com.evan.saas.common.lang;


public interface ObjectIdentifier<T> {
    T general();

    default String generalIdentifier(){
        return generalIdentifier("");
    }

    String generalIdentifier(String prefix);

}
