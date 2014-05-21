package ru.odnoklassniki.tests.ui.api.controls;

import static ru.odnoklassniki.tests.ui.api.Messages.LOG_CLICK;
import static ru.odnoklassniki.tests.ui.api.Messages.TEST_EXPECTED_VISIBLE;

import org.testng.Assert;

import ru.odnoklassniki.tests.common.Loggers;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.common.WIDefaultRoad;
import ru.odnoklassniki.tests.ui.api.locale.Text;

public class WIButton extends WIElement {

	public static class Road extends WIDefaultRoad {

		private WIButton mButton;

		public Road(WIButton aButton) {
			super(aButton);
			mButton = aButton;
		}

		@Override
		public void go() {
			mButton.click();
		}

	}

	public static class Submit extends WIButton {

		public Submit(IWIRoad aRoad, Text aName) {
			super(aRoad, "//input[@type='submit' and @value='" + aName.getValue() + "']", aName.getName());
		}

	}

	public static class Link extends WIButton {

		public Link(IWIRoad aRoad, Text aName) {
			super(aRoad, "//a[text()='" + aName.getValue() + "']", aName.getValue());
		}

	}

	public static final String WI_BUTTON_TYPE = "button";

	public WIButton(IWIRoad aRoad, String aId, String aName) {
		super(aRoad, aId, aName, WI_BUTTON_TYPE);
	}

	public void click() {
		Loggers.ui.info(LOG_CLICK.getValue(this));
		if (isVisible()) {
			getBrowser().click(getGlobalID());
		} else {
			Assert.fail(TEST_EXPECTED_VISIBLE.getValue(this));
		}
	}

}
