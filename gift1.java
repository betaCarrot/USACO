/*
ID: majesti2
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;
import java.lang.Math;

public class gift1 {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
    StringTokenizer st = new StringTokenizer(f.readLine());
    int size=Integer.parseInt(st.nextToken());
    Person[] persons = new Person[size];
    for(int i=0;i<size;i++){
	StringTokenizer st1 = new StringTokenizer(f.readLine());
	persons[i] = new Person(st1.nextToken());
    }
	int n = 0;
    while(n<size){
	StringTokenizer st1 = new StringTokenizer(f.readLine());
	String potentialName = st1.nextToken();
	Person giver = findPerson(potentialName, persons);
	StringTokenizer st2 = new StringTokenizer(f.readLine());
	int money = Integer.parseInt(st2.nextToken());
	giver.setInitAccount(money);
	giver.addMoney(money);
	int length = Integer.parseInt(st2.nextToken());
	if(length!=0){
		int giveMoneyAmount = money/length;
		for(int k=0;k<length;k++){
			StringTokenizer st3 = new StringTokenizer(f.readLine());
			String pName = st3.nextToken();
			Person receiver = findPerson(pName,persons);
			giver.giveMoney(giveMoneyAmount);
			receiver.addMoney(giveMoneyAmount);
		}
	}
	n++;
	}
    	for(int q=0;q<size;q++){
		out.println(persons[q].getName()+" "+persons[q].getResult());
	}			
        out.close();
  	}

	public static Person findPerson(String potentialName, Person[] persons){
		for(int k=0;k<persons.length;k++){
		if(persons[k].getName().equals(potentialName)) {
		//System.out.println("Found: "+potentialName);
		return persons[k];
	}
	}
	return null;
	}
}

class Person{
	private String name;
	private int account;
	private int initAccount;
	
	public Person(String name){
		this.name = name;
		account=0;
		initAccount=0;
	}

	public String getName(){ return name; }
	public String toString(){ return name; }
	public void setInitAccount(int amount) {initAccount = amount;}
	public void addMoney(int amount) {account+=amount;}
	public void giveMoney(int amount) {account-=amount;}
	public int getResult() {return account-initAccount;}
} 
