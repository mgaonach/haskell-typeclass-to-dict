package utils;

public class Pair<U, V> {
	private final U m_first;
	private final V m_second;

	public Pair(U first, V second) {
		m_first = first;
		m_second = second;
	}

	public U first() {
		return m_first;
	}

	public V second() {
		return m_second;
	}
}