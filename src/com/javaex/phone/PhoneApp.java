package com.javaex.phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class PhoneApp {

	public static void main(String[] args) {
		int menu, num;
		String name, hp, company,Search;
		boolean a = true;
		List<PhoneVo> pVo = null;
		PhoneDao phoneDao = new PhoneDao();
		Scanner sc=new Scanner(System.in);
		System.out.println("*********************************");
		System.out.println("*        전화번호 관리 프로그램             *");
		System.out.println("*********************************");
		System.out.println(" ");
		while(a) {	
			System.out.println("1.리스트|2.등록|3.수정|4.삭제|5.검색|6.종료");
			System.out.println("---------------------------------");
			System.out.print(">메뉴번호: ");
			menu=sc.nextInt();
			switch (menu) {
			case 1:
				System.out.println("<1.리스트>");
				pVo= phoneDao.getpersonList();
				for (int i = 0; i < pVo.size(); i++) {
					PhoneVo vo = pVo.get(i);
					System.out.println(vo.getPersonid()+","+vo.getName()+","+vo.getHp()+","+vo.getCompany());
				}
				break;
			case 2:
				System.out.println("<2.등록>");
				System.out.print(">이름:");
				sc.nextLine(); 
				name = sc.nextLine();
				System.out.print(">휴대전화:");
				hp = sc.nextLine();
				System.out.print(">회사전화:");
				company = sc.nextLine();
				
				PhoneVo person = new PhoneVo(name, hp, company);
				phoneDao.personInsert(person);
				
				System.out.println("[등록되었습니다.]");
				break;
			case 3:
				System.out.println("<3.수정>");
				System.out.print(">번호:");
				num = sc.nextInt();
				System.out.print(">이름:");
				sc.nextLine(); 
				name = sc.nextLine();
				System.out.print(">휴대전화:");
				hp = sc.nextLine();
				System.out.print(">회사전화:");
				company = sc.nextLine();
				
				phoneDao.personUpdate(num,name, hp, company);
				break;
			case 4:
				System.out.println("<4.삭제>");
				System.out.print(">번호:");
				num = sc.nextInt();
				phoneDao.persondelete(num);
				break;
				
			case 5:
				sc.nextLine();
				System.out.print("검색어 > ");
				Search = sc.nextLine();
				
				pVo = phoneDao.phoneSearch(Search);
				
				for(int i=0; i<pVo.size();i++) {
					System.out.println(pVo.get(i).getPersonid()+ ".   " +pVo.get(i).getName()+ "    " +pVo.get(i).getHp()+ "   "+pVo.get(i).getCompany());
				}
			case 6:
				System.out.println("******************************************");
				System.out.println("*               감사합니다               *");
				System.out.println("******************************************");
				a = false;
				break;
				
			default :
				System.out.println("[다시 입력해 주세요.]");
				break;
		}
	}

	}

}
