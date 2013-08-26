package com.example.sodacloudsmsexampleclient;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

public class ConfigurationMapper implements Module {

    private static final String TAG = ConfigurationMapper.class.getSimpleName();
    private static final ConfigurationMapper instance = new ConfigurationMapper();

    private final Map<Class, Object> mComponentList = new HashMap<Class, Object>();

	private ConfigurationMapper() {}

	public static ConfigurationMapper getInstance() {
        return instance;
    }

	@Override
	public <T> void setComponent(final Class<T> type, final T component) {
	    if (!mComponentList.containsKey(type)) {
	        mComponentList.put(type, component);
	    }
	}

    @Override
    public <T> T getComponent(final Class<T> type) {
        try {
            return (T) mComponentList.get(type);
        } catch (final ClassCastException e) {
            //This should never be hit since by design the setComponent method forces the type and component to match.
            Log.e(TAG, "Tried to retrieve component but type did not match class argument!", e);
            return null;
        }
    }
}
