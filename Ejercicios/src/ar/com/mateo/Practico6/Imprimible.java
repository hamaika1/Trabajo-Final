package ar.com.mateo.Practico6;

public abstract class Imprimible {

	public String toStringExtraInfo() {
		return "";
	}

	@Override
	public String toString() {
		return String.format("[%s]%s", getClass().getSimpleName(), toStringExtraInfo());
	}

}
