package web;


import java.util.ArrayList;
import java.sql.*;


//�Խñ� ��� �����͸� ǥ���ϴ� �ڹٺ� Ŭ����
public class BBSList {
	
	private ArrayList<Integer> number = new ArrayList<Integer>();
	private ArrayList<String> title = new ArrayList<String>();
	private ArrayList<String> userName = new ArrayList<String>();
	private ArrayList<Date> wDate = new ArrayList<Date>();
	private ArrayList<Integer> hit = new ArrayList<Integer>();
	
	private boolean lastPage = false;
	private boolean firstPage = true;
	
    public BBSList() {
    }
    public void setNumber(int index, Integer number) {
         this.number.add(index, number);
    }
    public void setTitle(int index, String title) {
         this.title.add(index, title);
    }
    public void setUserName(int index, String userName) {
         this.userName.add(index, userName);
    }
    public void setwDate(int index, Date wDate) {
         this.wDate.add(index, wDate);
    }
    public void sethit(int index, Integer hit) {
         this.hit.add(index, hit);
    }
    public void setLastPage(boolean lastPage) {
         this.lastPage = lastPage;
    }
    
    public void setFirstPage(boolean firstPage) {
    	this.firstPage = firstPage;
    }
    
    
    public Integer[] getNumber() {
        return number.toArray(new Integer[number.size()]);
   }
   public String[] getTitle() {
        return title.toArray(new String[title.size()]);
   }
   public String[] getUserName() {
        return userName.toArray(new String[userName.size()]);
  }
   public Date[] getwDate() {
        return wDate.toArray(new Date[wDate.size()]);
   }
   public Integer[] gethit() {
        return hit.toArray(new Integer[hit.size()]);
   }
   public boolean isLastPage() {
        return lastPage;
   }
   
   public boolean isFirstPage() {
	   return firstPage;
   }
   
   public int getListSize() {   // �Խñ��� ���� �����ϴ� �޼���
        return number.size();
   }

}


