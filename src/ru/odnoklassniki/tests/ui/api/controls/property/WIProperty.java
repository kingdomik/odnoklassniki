package ru.odnoklassniki.tests.ui.api.controls.property;

import static ru.odnoklassniki.tests.ui.api.Messages.TEST_EXPECTED_VALUE;
import static ru.odnoklassniki.tests.ui.api.Messages.TEST_UNEXPECTED_VALUE;
import static ru.odnoklassniki.tests.ui.api.Requirements.argumentNotNull;

import org.testng.Assert;

import ru.odnoklassniki.tests.common.Time;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.ui.api.controls.WIElement;

import com.thoughtworks.selenium.Wait;

public abstract class WIProperty<T> extends WIElement {

	public static final String WI_PROPERTY_TYPE = "property";

	public WIProperty(IWIRoad road, String id, String name) {
		super(road, id, name, WI_PROPERTY_TYPE);
	}

	public T getValue() {
		return convert(getBrowser().getText(getGlobalID()));
	}
	
	protected abstract T convert(String text);
	
	public void assertValue(T value) {
		Assert.assertEquals(getValue(), value, this + " value");
	}
	
	public void assertAccessible() {
		Assert.assertTrue(this.isAccessible(), this + " expected to be accessible");
	}

	public void assertInaccessible() {
		Assert.assertTrue(!this.isAccessible(), this + " expected to be inaccessible");
	}

	public void assertVisible() {
		Assert.assertTrue(this.isVisible(), this + " expected to be visible");
	}

	public void assertInvisible() {
		Assert.assertTrue(!this.isVisible(), this + " expected to be invisible");
	}

	public void waitValue(Time timeout, final T aValue) {
		argumentNotNull(aValue, "value");
        new Wait() {
        	@Override
            public boolean until() {
                return aValue.equals(getValue());
            }
        }.wait(TEST_EXPECTED_VALUE.getProblem(this, aValue), timeout.toMilliseconds());
	}

	public void waitNoValue(Time timeout, final T aValue) {
		argumentNotNull(aValue, "value");
        new Wait() {
        	@Override
            public boolean until() {
                return !aValue.equals(getValue());
            }
        }.wait(TEST_UNEXPECTED_VALUE.getProblem(this, aValue), timeout.toMilliseconds());
	}

}