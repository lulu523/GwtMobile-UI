package com.gwtmobile.ui.kitchensink.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.gwtmobile.ui.client.page.Page;
import com.gwtmobile.ui.client.page.PageHistory;

public class KitchenSink implements EntryPoint {

	MainPage mainPage = new MainPage();
	
	@Override
	public void onModuleLoad() {
		Page.load(mainPage);
	}

}
