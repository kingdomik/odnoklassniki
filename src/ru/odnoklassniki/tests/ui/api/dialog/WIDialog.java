package ru.odnoklassniki.tests.ui.api.dialog;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.locale.Locale;
import ru.odnoklassniki.ui.api.controls.WIContainer;


public class WIDialog extends WIContainer {

	public static final String WI_DIALOG_TYPE = "dialog";

	public WIDialog(IWIRoad aRoad, Locale aName) {
		super(aRoad, "//*[@id='mp_mm_cont' and .//*[@class='panelLayer_head_headerSimple' and text()='"	+ aName.getValue() + "']]", aName.getName(), WI_DIALOG_TYPE);
	}

	public WIDialog(IWIRoad aRoad, String aLocator, String aName) {
		super(aRoad, aLocator, aName, WI_DIALOG_TYPE);
	}
	
	@Override
	public XPathBuilderBehaviour getRoadBuilderType() {
		return XPathBuilderBehaviour.GLOBAL;
	}
	
}