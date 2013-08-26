/*
 **
 ** Copyright 2013, Jules White
 **
 **
 */
package org.magnum.soda.example.sms;

import org.magnum.soda.proxy.SodaByValue;

@SodaByValue
public class SMS {

	private String to;
	private String from;
	private String content;

	public String getTo() {
		return this.to;
	}

	public SMS setTo(final String to) {
		this.to = to;
		return this;
	}

	public String getFrom() {
		return this.from;
	}

	public SMS setFrom(final String from) {
		this.from = from;
		return this;
	}

	public String getContent() {
		return content;
	}

	public SMS setContent(final String content) {
		this.content = content;
		return this;
	}

}
