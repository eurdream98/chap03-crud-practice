package com.ohgiraffers.section03.delete;

import java.io.FileInputStream;
import java.io.FileReader;
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
        Properties prop = new Properties();
        int result = 0;

        Scanner sc = new Scanner(System.in);
        System.out.print("삭제하고 싶은 부서의 아이디를 입력해주세요 : ");
        String deptId = sc.nextLine();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/connection-info.xml"));
            String query = prop.getProperty("delete");
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,deptId);
            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(con);
        }
        if(result>0){
            System.out.println("삭제가 정상적으로 완료되었습니다 *^^*");
        }else{
            System.out.println("삭제에 실패하였습니다.");
        }
    }

}
