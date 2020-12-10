import java.util.*;

public class Configuration {

	// bad smells:
	// long method (extract method),
	// duplicated code (extract method & change method signature),
	// data class (encapsulate fields)

	private int interval;
	private int duration;
	private int departure;

	public void load(Properties props) throws ConfigurationException {
		setMonitorInterval(props);
		setDuration(props);
		setDeparture(props);
	}
	private void setMonitorInterval(Properties props) throws ConfigurationException {
		String valueString;
		int value;
		valueString = props.getProperty("interval");
		condition(valueString == null, "monitor interval");
		value = Integer.parseInt(valueString);
		condition(value <= 0, "monitor interval > 0");
		interval = value;
	}
	private void setDuration(Properties props) throws ConfigurationException {
		String valueString;
		int value;
		valueString = props.getProperty("duration");
		condition(valueString == null, "duration");
		value = Integer.parseInt(valueString);
		condition(value <= 0, "duration > 0");
		condition((value % getInterval()) != 0, "duration % interval");
		duration = value;
	}
	private void setDeparture(Properties props) throws ConfigurationException {
		String valueString;
		int value;
		valueString = props.getProperty("departure");
		condition(valueString == null, "departure offset");
		value = Integer.parseInt(valueString);
		condition(value <= 0, "departure > 0");
		condition((value % getInterval()) != 0, "departure % interval");
		departure = value;
	}
	private void condition(boolean b, String s) throws ConfigurationException {
		if (b) {
			throw new ConfigurationException(s);
		}
	}
	public int getInterval() {
		return interval;
	}
	public int getDuration() {
		return duration;
	}
	public int getDeparture() {
		return departure;
	}
}