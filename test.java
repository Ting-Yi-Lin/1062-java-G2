package mysql;

import menu.Russia;

public class test {
	public static void main(String[] arg0){
		InsertData in = new InsertData();
		in.insert("Aux",1000);
		ReadData to = new ReadData();
		to.read();
	}
}
