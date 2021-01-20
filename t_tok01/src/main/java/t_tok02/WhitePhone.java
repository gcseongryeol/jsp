package t_tok02;


//일체형 스마트폰 - 배터리가 내장되어 있음
public class WhitePhone implements Phone {
	
	private Battery battery;
	
	public WhitePhone() {
		
		this.battery = new Battery();
	}
	
	public void show() {
		System.out.println(this.getClass().getSimpleName() + " : " + battery.getName());
		
	}
	
	

}
