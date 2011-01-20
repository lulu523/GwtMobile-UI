/*
 * Copyright (c) 2010 Zhihua (Dennis) Jiang
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gwtmobile.ui.client.widgets;

import java.util.Iterator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class HeaderPanel extends WidgetBase implements HasWidgets {
   
    public HeaderPanel() {
    	FlowPanel container = new FlowPanel();
    	container.add(new SimplePanel());	//left button placeholder
    	container.add(new FlowPanel());		//contents
    	container.add(new SimplePanel());	//right button placeholder
        initWidget(container);
        setStyleName("Header");
    }
    
    @Override
    public void add(Widget w) {
    	FlowPanel contents = ((FlowPanel)((FlowPanel)getWidget()).getWidget(1));
    	contents.add(w);
    }
    
    @Override
    protected void onInitialLoad() {
    }
    
    public void setLeftButton(String buttonName) {
    	SimplePanel leftButton = ((SimplePanel)((FlowPanel)getWidget()).getWidget(0));
    	ClickHandler clickHandler = new ClickHandler() {				
			@Override
			public void onClick(ClickEvent event) {
				onLeftButtonClick(event);
			}
		};
    	if (buttonName.toUpperCase().equals("BACK")) {
    		leftButton.setWidget(new BackButton());
    	}
    	else {
    		leftButton.setWidget(new Button(buttonName, clickHandler));
    	}
    }
    
    public void setRightButton(String buttonName) {
    	SimplePanel rightButton = ((SimplePanel)((FlowPanel)getWidget()).getWidget(2));
    	ClickHandler clickHandler = new ClickHandler() {				
			@Override
			public void onClick(ClickEvent event) {
				onRightButtonClick(event);
			}
		};
    	if (buttonName.toUpperCase().equals("NEXT")) {
    		//FIXME
    		rightButton.setWidget(new Button(buttonName, clickHandler));
    	}
    	else {
    		rightButton.setWidget(new Button(buttonName, clickHandler));
    	}
    }
    
    public void onLeftButtonClick(ClickEvent event) {    	
    }
    
    public void onRightButtonClick(ClickEvent event) {    	
    }
    
    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Iterator<Widget> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(Widget w) {
        // TODO Auto-generated method stub
        return false;
    }
    
}
