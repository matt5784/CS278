package com.example.sodacloudsmsexampleclient;

import org.magnum.soda.proxy.ObjRef;

public class ExternalObjRefImpl implements ExternalObjRef {

    private final String mPubSubHost;
    private final ObjRef mObjRef;

    public ExternalObjRefImpl(final String server, final ObjRef oref) {
        mPubSubHost = server;
        mObjRef = oref;
    }

    @Override
    public ObjRef getObjRef() {
        return mObjRef;
    }

    @Override
    public String getPubSubHost() {
        return mPubSubHost;
    }

    @Override
    public String toString() {
        return getPubSubHost()+"|"+getObjRef().getUri();
    }

}
