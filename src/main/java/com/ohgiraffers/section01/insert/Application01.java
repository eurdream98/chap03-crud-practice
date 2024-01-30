package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application01 {


    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();

        Scanner sc = new Scanner(System.in);
        System.out.print("추가할 부서아이디를 입력해주세요 : ");
        String deptId = sc.nextLine();
        System.out.print("추가할 부서이름을 입력해주세요 : ");
        String deptTitle = sc.nextLine();
        System.out.print("추가할 국가 아이디를 입력해주세요 : ");
        String locationId = sc.nextLine();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/connection-info.xml"));
            String query = prop.getProperty("insert");
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,deptId);
            pstmt.setString(2,deptTitle);
            pstmt.setString(3,locationId);

            result = pstmt.executeUpdate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(pstmt);
            close(con);
        }
        if(result>0){
            System.out.println("부서 추가가 성공적으로 완료 되었습니다.*^^*");
        }else{
            System.out.println("부서 추가에 실패하였습니다.. ㅠㅡㅠ");
        }
    }

}
