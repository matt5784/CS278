/*
**
** Copyright 2013, Jules White
**
**
*/
package edu.vu.task;

import org.magnum.soda.proxy.SodaAsync;

public interface TaskListener {

    @SodaAsync
	public void itemAdded(TaskItem item);

	@SodaAsync
	public void itemChanged(TaskItem item);

}
