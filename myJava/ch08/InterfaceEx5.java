package ch08;

abstract class Tv {
	
	String color;
	boolean power;
	int ch;
	
	void onOff() {
		power = !power;
	}
	void upCh() {ch++;}
	void downCh() {ch--;}
	
	abstract void setModel();
}

interface RemoteControl {
	void turnOn();
	void turnOff();
}

class SMTv extends Tv implements RemoteControl {
	@Override
	void setModel() {}
	@Override
	public void turnOff() {}
	@Override
	public void turnOn() {}
}
class LGTv extends Tv implements RemoteControl {
	@Override
	void setModel() {}
	@Override
	public void turnOff() {}
	@Override
	public void turnOn() {}
}

public class InterfaceEx5 {
	public static void main(String[] args) {
		
	}
}
