package ru.odnoklassniki.tests.ui.api.locale;

import static ru.odnoklassniki.tests.ui.api.Messages.FAILED_READ_PROPERTIES;
import static ru.odnoklassniki.tests.ui.api.Messages.INVALID_LOCALE;
import static ru.odnoklassniki.tests.ui.api.Messages.LOCALE_NOT_FOUND;
import static ru.odnoklassniki.tests.ui.api.Messages.LOG_SET_LOCALE;
import static ru.odnoklassniki.tests.ui.api.Messages.PROPERTY_UNDEFINED;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import ru.odnoklassniki.tests.common.Loggers;
import ru.odnoklassniki.tests.common.Requirements;
import ru.odnoklassniki.tests.runner.TestboxException;
import ru.odnoklassniki.tests.ui.api.WIBrowser;

/**
 * Locale manager class
 * 
 */
public class LocaleManager {

	private static Properties propDefault = getProperties(System.getProperty("locale.default", "en"));
	private static Properties propCurrent;

	/**
	 * Auto detect locale by current browser page
	 * 
	 * @param browser
	 */
	public static void autoDetect(WIBrowser browser) {
		Requirements.notNull(browser, "browser");
		// Auto-detect locale
		String locale = browser.getAttribute("//head/meta[@name='gwt:property']/@content");
		if (!locale.startsWith("locale=")) {
			throw new TestboxException(INVALID_LOCALE, locale);
		}
		setLocale(locale.substring(locale.indexOf("=") + 1));
	}

	private static Properties getProperties(String locale) {
		Requirements.notNull(locale, "locale");
		Properties properties = new Properties();
		InputStream is = LocaleManager.class.getResourceAsStream(locale + ".properties");
		if (null == is) {
			throw new TestboxException(LOCALE_NOT_FOUND, locale);
		}
		try {
			try {
				properties.load(new InputStreamReader(is, "UTF-8"));
			} finally {
				is.close();
			}
		} catch (IOException e) {
			throw new TestboxException(FAILED_READ_PROPERTIES, locale);
		}

		// Check property file defines all enum values
		for (Text l : Text.values()) {
			if (null == properties.getProperty(l.name())) {
				throw new TestboxException(PROPERTY_UNDEFINED, l.name(), locale);
			}
		}

		return properties;
	}

	/**
	 * Set locale
	 * 
	 * @param locale
	 *            name of locale
	 */
	public static void setLocale(String locale) {
		Requirements.notNull(locale, "locale");
		propCurrent = getProperties(locale);
		Loggers.ui.debug(LOG_SET_LOCALE.getValue(locale));
	}

	/**
	 * Get default locale
	 * 
	 * @return properties
	 */
	public static Properties getDefault() {
		return propDefault;
	}

	/**
	 * Get curent locale
	 * 
	 * @return properties
	 */
	public static Properties getCurrent() {
		return null == propCurrent ? propDefault : propCurrent;
	}

}
