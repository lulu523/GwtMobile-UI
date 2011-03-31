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

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HTML;
import com.gwtmobile.ui.client.event.DragController;
import com.gwtmobile.ui.client.event.DragEvent;
import com.gwtmobile.ui.client.event.DragEventsHandler;
import com.gwtmobile.ui.client.utils.Utils;

public class FlipSwitch extends WidgetBase 
	implements DragEventsHandler, ClickHandler, HasValueChangeHandlers<Boolean> {

	protected boolean _value = true;
	protected HTML _html = new HTML();
	
    public FlipSwitch() {
    	initWidget(_html);
        setStyleName("FlipSwitch");
        addStyleName("Off");
        _html.addClickHandler(this);
        _html.setHTML("<div></div><div></div><div><div><div>ON</div><div></div><div>OFF</div></div></div>");
    }
    
    @Override
    protected void onInitialLoad() {
    	super.onInitialLoad();
    	updateFlipPosition();
    }
    
    @Override
    public void onLoad() {
        super.onLoad();
        DragController.get().addDragEventsHandler(this);
    }
    
    @Override
    public void onUnload() {
        DragController.get().removeDragEventsHandler(this);
    }
    
    @Override
    public void onDragStart(DragEvent e) {
    	DragController.get().captureDragEvents(this);
    	Utils.setTransitionDuration(getFilpElement(), 0);
    }

    @Override
    public void onDragMove(DragEvent e) {
    	e.stopPropagation();
    	Element flip = getFilpElement();
    	int x = Utils.getTranslateX(flip);
    	int newX = (int) (x + e.OffsetX);
    	int onPosition = getOnPosition();
    	int offPosition = getOffPosition();
    	if (newX > onPosition) {
    		newX = onPosition;
    	}
    	else if (newX < offPosition) {
    		newX = offPosition;
    	}
    	if (newX != x) {
        	Utils.setTranslateX(flip, newX);
    	}
    }

    @Override
    public void onDragEnd(DragEvent e) {
    	DragController.get().releaseCapture(this);
    	Element flip = getFilpElement();
    	int x = Utils.getTranslateX(flip);
    	int onPosition = getOnPosition();
    	int offPosition = getOffPosition();
    	if (x == onPosition) {
    		setValue(true);
    	}
    	else if (x == offPosition) {
    		setValue(false);
    	}
    	else {
	    	boolean newValue = x >= (offPosition - onPosition) / 2;
	    	setValue(newValue, true);
    	}
    }

    public void setValue(boolean value) {
    	setValue(value, false);
    }
    
    public void setValue(boolean value, boolean forceUpdateFlipPosition) {
    	if (_value != value) {
        	_value = value;
        	updateFlipPosition();    	
    		ValueChangeEvent.fire(this, _value);
    	}
    	else if (forceUpdateFlipPosition) {
        	updateFlipPosition();    	
    	}
    }
    
    public boolean getValue() {
    	return _value;
    }

	@Override
	public void onClick(ClickEvent event) {
		setValue(!_value);
	}
	
	private void updateFlipPosition() {
    	Utils.setTransitionDuration(getFilpElement(), 200);
		Element flip = getFilpElement();
		if (_value) {
			Utils.setTranslateX(flip, getOnPosition());
		}
		else {
			Utils.setTranslateX(flip, getOffPosition());
		}
	}
	
	private Element getFilpElement() {
    	return (Element) getElement().getChild(2).getChild(0);
	}

	private int getOnPosition() {
		return 0;
	}
	
	private int getOffPosition() {
		Element flip = getFilpElement();
		Element parent = flip.getParentElement();
		int flipWidth = flip.getScrollWidth();
		int parentWidth = parent.getClientWidth();
		return parentWidth - flipWidth;
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Boolean> handler) {
		return this.addHandler(handler, ValueChangeEvent.getType());
	}
}