package com.example.sodacloudsmsexampleclient;

import org.magnum.soda.proxy.ObjRef;

public class QRCodeObjRefExtractor implements ObjRefExtractor {

	@Override
	public ExternalObjRef extract(final String data) {
		final String server = data.substring(0,data.indexOf("|"));
		final String ref = data.substring(data.indexOf("|")+1);
		final ObjRef oref = ObjRef.fromObjUri(ref);

		/**
		 * Asgn Step 7: Use the data above to create an
		 * instance of your ExternalObjRef implementation
		 * and return it.
		 *
		 */

		return new ExternalObjRefImpl(server, oref);
	}

}
